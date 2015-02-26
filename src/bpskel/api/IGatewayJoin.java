package bpskel.api;

import bpskel.impl.core.IFlowObject;
import bpskel.impl.gateway.IGateway;

public interface IGatewayJoin extends IGateway {

	public abstract IFlowObject getPredecessor2();

	public abstract void setPredecessor2(IFlowObject pred2);

	public abstract void resetPredecessor2();

}