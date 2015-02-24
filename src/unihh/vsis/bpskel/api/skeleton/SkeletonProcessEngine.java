package unihh.vsis.bpskel.api.skeleton;

import unihh.vsis.bpskel.blockconverter.ProxyTask;
import unihh.vsis.bpskel.blockconverter.pattern.IPattern;
import unihh.vsis.bpskel.blockconverter.pattern.PatternMatcher;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.core.GatewaySplit;
import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.GatewayJoin;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IProcessEngine;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayAndJoin;
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
		PatternMatcher m = new PatternMatcher();
		// assume valid BPG
		boolean isValidBPG = true;
		
		IFlowObject currentNode = pro.getStart();
		// do until whole bpg has been transformed
		while(pro.getFlowObjects().size() > 3 && isValidBPG){
			createSkeletonStructure(pro, pro.getStart());
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
					ProxyTask t = new ProxyTask(s, currentNode.getIncomingFlowObjects().first, lastPatternNode.getOutgoingFlowObjects().first);
					this.insertProxy(bpg, t);
					
					// continue with next node
					currentNode = t.getOutgoingFlowObjects().first;
				} catch (PatternMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// invalid pattern, so either nesting or invalid bpg. We assume the former.
			else{
				// check for a split and call recursively for each open branch
				if(currentNode instanceof GatewaySplit){
					IFlowObject branch1 = currentNode.getOutgoingFlowObjects().first;
					IFlowObject branch2 = currentNode.getOutgoingFlowObjects().second;
					if(!this.isValidBranch(branch1)){
						this.createSkeletonStructure(bpg, branch1);
					}
					if(!this.isValidBranch(branch2)){
						this.createSkeletonStructure(bpg, branch2);
					}
				}
			}
				
		}			
			// replace in BusinessProcess	
	}
	
	/**
	 * A branch is valid (thus ready for reduction) if the successor of node is a direct join.
	 * It is assumed, that the predecessor of node is a split.
	 * @param node
	 * @return true if the branch is valid
	 */
	private boolean isValidBranch(IFlowObject node){
		if((node instanceof ProxyTask) && 
				(node.getOutgoingFlowObjects().first instanceof GatewayJoin) ) {
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
		for(IFlowObject f:bpg.getFlowObjects()){
			if(f.equals(proxyTask.getIncomingFlowObjects().first) && !replacedPred){
				f.resetOutgoingFlowObjects();
				f.addOutgoingFlowObject(proxyTask);
				
			}
			else if(f.equals(proxyTask.getOutgoingFlowObjects().first) && !replacedSucc){
				f.resetIncomingFlowObjects();
				f.addIncomingFlowObject(proxyTask);
			}
		}
		
		return replacedPred && replacedSucc;
		
	}

	@Override
	public boolean initialize() {
		// Setup Skandium engine here
		return false;
	}
	
}
