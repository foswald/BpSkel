package unihh.vsis.bpskel.bpmn.core;


/**
 * An AbstractSplitFlowObject class has exactly one preceding FlowObject and two (restricted) succeeding FlowObjects.
 * @author foswald
 *
 */
public abstract class AbstractSplitFlowObject extends AbstractFlowObject implements IGateway{
	
	public AbstractSplitFlowObject(){
		super();
	}
	
	
	public void setIncomingFlowObject(IFlowObject flowObject){
		resetIncomingFlowObjects();
		setIncomingFlowObject(flowObject);
	}

	@Override
	public boolean isFlowSplit(){
		return true;
	}
	
	@Override
	public boolean isFlowJoin(){
		return false;
	}

}
