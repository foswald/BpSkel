package unihh.vsis.bpskel.bpmn.core;

public abstract class GatewayJoin extends AbstractFlowObject implements IGateway{
	@Override
	public boolean isFlowSplit() {
		return false;
	}

	@Override
	public boolean isFlowJoin() {
		return true;
	}


}
