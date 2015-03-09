package bpskel.bpg.conversion;

import bpskel.api.IBPG;
import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.bpg.conversion.pattern.IPattern;
import bpskel.bpg.conversion.pattern.PatternMatcher;
import bpskel.bpg.conversion.pattern.ProxyTask;
import bpskel.bpg.elements.core.EndElement;
import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.gateway.GatewayJoin;
import bpskel.bpg.elements.gateway.GatewaySplit;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplit;
import bpskel.engine.skeleton.api.ISkeleton;
import bpskel.engine.skeleton.api.ISkeletonAPI;

public class BlockstructureConverter {
	
	ISkeletonAPI skeletonApi;
	PatternMatcher patternMatcher;
	
	public BlockstructureConverter(ISkeletonAPI skeletonApi){

		this.skeletonApi = skeletonApi;
		this.patternMatcher = new PatternMatcher();
	}
	
	public ProxyTask convert(IBPG pro) {

		// do until whole bpg has been transformed
		while(!isReduced(pro) || !(pro.getStart().getSuccessor() instanceof ProxyTask)){
			createSkeletonStructure(pro, pro.getStart().getSuccessor(), false);
		}
				
		ProxyTask root = (ProxyTask) pro.getStart().getSuccessor();
		
		return root;
	}
	
	private void createSkeletonStructure(IBPG bpg, IFlowObject currentNode, boolean isBranch) {
		
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
			else if(currentNode instanceof IDataSplit || currentNode instanceof IDataMerge){
				currentNode = currentNode.getSuccessor();
			}
			// ProxyTask but next is no proxy, proceed
			else if(currentNode instanceof ProxyTask){
				// already processed
				currentNode = currentNode.getSuccessor();
			}
		} while(!(currentNode instanceof EndElement));
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
		
	
	private boolean isReduced(IBPG pro){
		return pro.getStart().getSuccessor().getSuccessor().equals(pro.getEnd());
	}
	
}
