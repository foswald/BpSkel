package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.core.AbstractFlowObject;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

/**
 * This class is used during transformation of the BPG.
 * It represents a transformed Pattern by holding the skeleton and connecting nodes
 * @author foswald
 *
 */
public class ProxyTask extends AbstractFlowObject{
	
	ISkeleton skelRef;
	
	private IFlowObject entryNode;
	private IFlowObject exitNode;
	
	public ProxyTask(ISkeleton skelRef, IFlowObject pred, IFlowObject succ, IFlowObject entryNode, IFlowObject exitNode){
		super.setDescription("ProxyTask");
		super.setPredecessor(pred);
		super.setSuccessor(succ);
		
		this.skelRef = skelRef;
		this.setEntryNode(entryNode);
		this.setExitNode(exitNode);		
	}

	public IFlowObject getEntryNode() {
		return entryNode;
	}

	public void setEntryNode(IFlowObject entryNode) {
		this.entryNode = entryNode;
	}

	public IFlowObject getExitNode() {
		return exitNode;
	}

	public void setExitNode(IFlowObject exitNode) {
		this.exitNode = exitNode;
	}
}
