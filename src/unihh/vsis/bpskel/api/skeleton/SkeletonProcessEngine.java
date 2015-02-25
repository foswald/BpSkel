package unihh.vsis.bpskel.api.skeleton;

import unihh.vsis.bpskel.blockconverter.pattern.IPattern;
import unihh.vsis.bpskel.blockconverter.pattern.PatternMatcher;
import unihh.vsis.bpskel.blockconverter.pattern.PatternType;
import unihh.vsis.bpskel.blockconverter.pattern.ProxyTask;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.GatewayJoin;
import unihh.vsis.bpskel.bpmn.core.GatewaySplit;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IProcessEngine;
import unihh.vsis.bpskel.exceptions.PatternMismatchException;
import unihh.vsis.bpskel.executor.skandium.SkandiumConnector;

public class SkeletonProcessEngine implements IProcessEngine{

	ISkeletonAPI skeletonApi;
	PatternMatcher patternMatcher;
	
	public SkeletonProcessEngine(){
		this.skeletonApi = new SkandiumConnector();
		this.patternMatcher = new PatternMatcher();
		
	}
	@Override
	public void execute(BusinessProcess pro) {
		// assume valid BPG
		boolean isValidBPG = true;
		
		// first prep tasks with proxies
		try {
			taskToProxy(pro);
		} catch (PatternMismatchException e) {
			e.printStackTrace();
		}
		
		// do until whole bpg has been transformed
		while(pro.getFlowObjects().size() > 3 && isValidBPG){
			createSkeletonStructure(pro, pro.getStart().getSuccessor());
		}
			
	}
	
	public void createSkeletonStructure(BusinessProcess bpg, IFlowObject currentNode) {
		
		// run until end of bpg has been reached
		while(!(currentNode instanceof EndElement)) {
			if(this.patternMatcher.isValidPattern(currentNode)){
				IPattern p;
				try {
					p = this.patternMatcher.matchPattern(currentNode);
	
					// context switch to skeleton
					ISkeleton s = this.skeletonApi.createSkeleton(p.getPatternType(), currentNode);
					
					IFlowObject lastPatternNode = p.getEndElement(currentNode);
				
					// create ProxyTask
					IFlowObject prec = currentNode.getPredecessor();
					IFlowObject suc = lastPatternNode.getSuccessor();
					ProxyTask t = new ProxyTask(s, prec, suc);

					// replace in BusinessProcess	
					this.insertProxy(bpg, t);
					
					// continue with next node
					currentNode = t.getSuccessor();
				} catch (PatternMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// invalid pattern, so either nesting or invalid bpg. We assume the former.
			else{
				// check for a split and call recursively for each open branch
				if(currentNode instanceof GatewaySplit){
					IFlowObject branch1 = currentNode.getSuccessor();
					IFlowObject branch2 = currentNode.getSuccessor();
					if(!this.isValidBranch(branch1)){
						this.createSkeletonStructure(bpg, branch1);
					}
					if(!this.isValidBranch(branch2)){
						this.createSkeletonStructure(bpg, branch2);
					}
				}
			}				
		}			
	}
	
	/**
	 * A branch is valid (thus ready for reduction) if the successor of node is a direct join.
	 * It is assumed, that the predecessor of node is a split.
	 * @param node
	 * @return true if the branch is valid
	 */
	private boolean isValidBranch(IFlowObject node){
		if((node instanceof ProxyTask) && 
				(node.getSuccessor() instanceof GatewayJoin) ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Inserts the <code>proxyTask</code> in <code>bpg</code>.
	 * @note The old FlowObjects are not removed from the <code>bpg</code>. Call <code>bpg.clean()</code> to remove them.
	 * @param bpg
	 * @param proxyTask
	 * @return <code>true</code> if the nodes could be successfully replaced
	 */
	private boolean insertProxy(BusinessProcess bpg, ProxyTask proxyTask){
		boolean replacedPred = false;
		boolean replacedSucc = false;
		
		// first handle start and endelement
		if(proxyTask.getPredecessor().equals(bpg.getStart())) {
			bpg.connect(bpg.getStart(), proxyTask, true);
			replacedPred = true;
		}
		if(proxyTask.getSuccessor().equals(bpg.getEnd())) {
			bpg.connect(proxyTask, bpg.getEnd(), true);
			replacedSucc = true;
		}
		
		for(int i=0; i < bpg.getFlowObjects().size() && (!replacedPred || !replacedSucc); i++){
			IFlowObject f = bpg.getFlowObjects().get(i);
			if(!replacedPred && f.equals(proxyTask.getPredecessor())){
				bpg.connect(f, proxyTask, true);
				replacedPred = true;				
			}
			else if(!replacedSucc && f.equals(proxyTask.getSuccessor())){
				bpg.connect(proxyTask, f, true);
				replacedSucc = true;
			}
		}
		
		return replacedPred && replacedSucc;
		
	}
	
	
	/**
	 * Iterates over all elements and replaces plain tasks with proxies.
	 * This is required for further graph transformation, as only ProxyTasks provide pattern recognition.
	 * @throws PatternMismatchException 
	 */
	private void taskToProxy(BusinessProcess bpg) throws PatternMismatchException{
		for(IFlowObject f:bpg.getFlowObjects()){
			if(this.patternMatcher.matchPattern(f).getPatternType() == PatternType.SEQ){
				// context switch to skeleton
				ISkeleton seq = this.skeletonApi.createSkeleton(PatternType.SEQ, f);
							
				// create ProxyTask
				IFlowObject prec = f.getPredecessor();
				IFlowObject suc = f.getSuccessor();
				ProxyTask t = new ProxyTask(seq, prec, suc);
				
				this.insertProxy(bpg, t);
			}
		}
	}

	@Override
	public boolean initialize() {
		// Setup Skandium engine here
		return false;
	}
	
}
