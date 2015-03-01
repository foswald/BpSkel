package bpskel.engine.skeleton.api;

import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.bpg.api.IGatewayJoin;
import bpskel.bpg.api.IGatewaySplit;
import bpskel.bpg.api.IProcessEngine;
import bpskel.bpg.impl.core.EndElement;
import bpskel.bpg.impl.core.IFlowObject;
import bpskel.bpg.impl.gateway.GatewayJoin;
import bpskel.bpg.impl.gateway.GatewaySplit;
import bpskel.engine.skeleton.impl.pattern.IPattern;
import bpskel.engine.skeleton.impl.pattern.PatternMatcher;
import bpskel.engine.skeleton.impl.pattern.ProxyTask;

public class SkeletonProcessEngine implements IProcessEngine{

	ISkeletonAPI skeletonApi;
	PatternMatcher patternMatcher;
	
	public SkeletonProcessEngine(Class<? extends ISkeletonAPI> skeletonApi){
		try {
			this.skeletonApi = skeletonApi.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.patternMatcher = new PatternMatcher();
		
	}
	@Override
	public void execute(BusinessProcessGraph pro) {

		// do until whole bpg has been transformed
		while(!isReduced(pro) || !(pro.getStart().getSuccessor() instanceof ProxyTask)){
			createSkeletonStructure(pro, pro.getStart().getSuccessor(), false);
		}
		
		ProxyTask root = (ProxyTask) pro.getStart().getSuccessor();
		
		// now start the skeleton application
		this.skeletonApi.execute(root.getSkeletonReference());
			
	}
	
	private void createSkeletonStructure(BusinessProcessGraph bpg, IFlowObject currentNode, boolean isBranch) {
		
		// run until end of bpg has been reached
		do {			
			// check if we have a valid pattern and insert a proxy
			if(this.patternMatcher.isValidPattern(currentNode)){
				IPattern pattern = this.patternMatcher.matchPattern(currentNode);

				// context switch to skeleton
				ISkeleton skel = this.skeletonApi.createSkeleton(pattern.getPatternType(), currentNode);
				
				IFlowObject lastPatternNode = pattern.getEndElement(currentNode);
				IFlowObject succ = lastPatternNode.getSuccessor();
				IFlowObject pred = currentNode.getPredecessor();
				
				// loop detection
				if(succ.equals(currentNode)){
					assert lastPatternNode instanceof IGatewaySplit;
					
					// change successor node
					succ = ((IGatewaySplit)lastPatternNode).getSuccessor2();
				}
			
				// create ProxyTask
				ProxyTask t = new ProxyTask(skel, pred, succ, currentNode, lastPatternNode);

				// replace in BusinessProcess
				boolean inserted = this.insertProxy(t);
				if(!inserted) {
					throw new Error("Insert failed!");
				}
				
				// continue with next node
				currentNode = t.getSuccessor();

			}
			// no valid pattern but split (check branches recursively
			else if(currentNode instanceof GatewaySplit){
					// check for a split and call recursively for each open branch
				GatewaySplit split = (GatewaySplit) currentNode;
				IFlowObject branch1 = split.getSuccessor();
				IFlowObject branch2 = split.getSuccessor2();
				
				// process one branch per run (otherwise you might get data inconsistency in this scope)
				if(!this.isValidBranch(branch1)){
					this.createSkeletonStructure(bpg, branch1, true);
				}
				if(!this.isValidBranch(branch2)){
					this.createSkeletonStructure(bpg, branch2, true);
				}
				break;
			} 
			// probably a while loop
			else if(!isBranch && currentNode instanceof GatewayJoin){
				currentNode = currentNode.getSuccessor();
			}
			// end branching
			else if(isBranch && currentNode instanceof GatewayJoin){
				break;
			}
			// ProxyTask but next is no proxy, proceed
			else if(currentNode instanceof ProxyTask){
				// already processed
				currentNode = currentNode.getSuccessor();
			}
		} while(!(currentNode instanceof EndElement) && !isBranch);
	}
	
	/**
	 * A branch is valid (thus ready for reduction) if the successor of node is a direct join and node is a ProxyTask.
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
	 * @note The old FlowObjects are not removed from the <code>bpg</code>. Call <code>clean()</code> to remove them.
	 * @param bpg
	 * @param proxyTask
	 * @return <code>true</code> if the nodes could be successfully replaced
	 */
	private boolean insertProxy(ProxyTask proxy){

		boolean replacedPred = false;
		boolean replacedSucc = false;
		
		IFlowObject pred = proxy.getPredecessor();
		IFlowObject succ = proxy.getSuccessor();
		
		// check first branch by default
		if(pred.getSuccessor().equals(proxy.getEntryNode())) {
			pred.setSuccessor(proxy);
			replacedPred = true;
		}
		if(succ.getPredecessor().equals(proxy.getExitNode())) {
			succ.setPredecessor(proxy);
			replacedSucc = true;
		}

		// if we have a proxy in a branch, we need to find the correct one
		if(pred instanceof GatewaySplit && !replacedPred){
			IGatewaySplit split = (IGatewaySplit) pred;
			// check for second branch
			if(split.getSuccessor2().equals(proxy.getEntryNode())) {
				split.setSuccessor2(proxy);
				replacedPred = true;
			}
		}
		if(succ instanceof GatewayJoin && !replacedSucc){
			IGatewayJoin join = (IGatewayJoin) succ;
			// check for second incomming branch of join
			if(join.getPredecessor2().equals(proxy.getExitNode())){
				join.setPredecessor2(proxy);
				replacedSucc = true;
			}
		}
		
		return replacedSucc && replacedPred;
	}
		

	@Override
	public boolean initialize() {
		// Setup Skandium engine here
		return false;
	}
	
	private boolean isReduced(BusinessProcessGraph pro){
		return pro.getStart().getSuccessor().getSuccessor().equals(pro.getEnd());
	}
	
}
