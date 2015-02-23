package unihh.vsis.bpskel.bpmn.impl.gateway;

import unihh.vsis.bpskel.bpmn.core.AbstractFlowObject;
import unihh.vsis.bpskel.bpmn.core.IGateway;

public class GatewayAndJoin extends AbstractFlowObject implements IGateway{

	/**
	 * Aggregate results from both incoming branches by nesting IDataContainers.
	 */
//	@Override
//	public IDataContainer getResultData() {
//		return DataJoiner.aggregate(
//				this.getIncomingFlowObjects().first.getResultData(),
//				this.getIncomingFlowObjects().second.getResultData());
//	}	

	@Override
	public boolean isFlowSplit() {
		return false;
	}

	@Override
	public boolean isFlowJoin() {
		return true;
	}


}
