package unihh.vsis.bpskel.bpmn.core;

import unihh.vsis.bpskel.bpmn.api.ITask;

public abstract class AbstractTask extends AbstractFlowObject implements ITask{

	
	/**
	 * Get the only incoming FlowObject of this Task
	 * @return
	 */
	public IFlowObject getIncomingFlowObject(){
		return this.getIncomingFlowObjects().first;
	}
	
	/**
	 * Get the only outgoing FlowObject of this Task
	 * @return
	 */
	public IFlowObject getOutgoingFlowObject(){
		return this.getOutgoingFlowObjects().first;
	}
	
	/**
	 * Set the only incoming FlowObject of this Task
	 * @return
	 */
	@Override
	public void addIncomingFlowObject(IFlowObject flowObject){
		this.resetIncomingFlowObjects();
		super.addIncomingFlowObject(flowObject);
	}
	
	/**
	 * Set the only outgoing FlowObject of this Task
	 * @return
	 */
	@Override
	public void addOutgoingFlowObject(IFlowObject flowObject){
		this.resetOutgoingFlowObjects();
		super.addOutgoingFlowObject(flowObject);
	}

}
