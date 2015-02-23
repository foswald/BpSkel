package unihh.vsis.bpskel.bpmn.impl.gateway;

import unihh.vsis.bpskel.bpmn.core.AbstractFlowObject;
import unihh.vsis.bpskel.bpmn.core.IGateway;

public class GatewayXorJoin extends AbstractFlowObject implements IGateway {
	@Override
	public boolean isFlowSplit() {
		return false;
	}

	@Override
	public boolean isFlowJoin() {
		return true;
	}

}
