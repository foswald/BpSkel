package bpskel.impl.gateway;

import bpskel.api.IGatewayJoin;
import bpskel.impl.core.AbstractFlowObject;
import bpskel.impl.core.IFlowObject;

public abstract class GatewayJoin extends AbstractFlowObject implements IGatewayJoin{
	
	private IFlowObject pred2;

	/**
	 * @see bpskel.api.IGatewayJoin#getPredecessor2()
	 */
	@Override
	public IFlowObject getPredecessor2() {
		return pred2;
	}

	/**
	 * @see bpskel.api.IGatewayJoin#setPredecessor2(bpskel.impl.core.IFlowObject)
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
