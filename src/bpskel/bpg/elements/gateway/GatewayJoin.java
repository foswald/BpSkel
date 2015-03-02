package bpskel.bpg.elements.gateway;

import bpskel.api.IGatewayJoin;
import bpskel.bpg.elements.core.FlowObject;
import bpskel.bpg.elements.core.IFlowObject;

public abstract class GatewayJoin extends FlowObject implements IGatewayJoin{
	
	private IFlowObject pred2;

	/**
	 * @see bpskel.api.IGatewayJoin#getPredecessor2()
	 */
	@Override
	public IFlowObject getPredecessor2() {
		return pred2;
	}

	/**
	 * @see bpskel.api.IGatewayJoin#setPredecessor2(bpskel.bpg.elements.core.IFlowObject)
	 */
	@Override
	public void setPredecessor2(IFlowObject pred2) {
		this.pred2 = pred2;
	}
	
	/**
	 * @see bpskel.api.IGatewayJoin#resetPredecessor2()
	 */
	@Override
	public void resetPredecessor2() {
		this.pred2 = null;
	}

	@Override
	public boolean isEmpty(){
		return super.isEmpty() && pred2==null;
	}
}
