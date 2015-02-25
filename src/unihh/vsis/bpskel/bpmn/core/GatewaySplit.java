package unihh.vsis.bpskel.bpmn.core;


/**
 * An AbstractSplitFlowObject class has exactly one preceding FlowObject and two (restricted) succeeding FlowObjects.
 * @author foswald
 *
 */
public abstract class GatewaySplit extends AbstractFlowObject implements IGateway{
	
	private IFlowObject succ2;

	public IFlowObject getSuccessor2() {
		return succ2;
	}

	public void setSuccessor2(IFlowObject succ2) {
		this.succ2 = succ2;
	}
	
	public void resetSuccessor2() {
		this.succ2 = null;
	}
	
}
