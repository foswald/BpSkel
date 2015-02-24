package unihh.vsis.bpskel.bpmn.core;


/**
 * An AbstractSplitFlowObject class has exactly one preceding FlowObject and two (restricted) succeeding FlowObjects.
 * @author foswald
 *
 */
public abstract class GatewaySplit extends AbstractFlowObject implements IGateway{
	
	public GatewaySplit(){
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
