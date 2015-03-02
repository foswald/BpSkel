package bpskel.api;

import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.gateway.IGateway;

public interface IGatewayJoin extends IGateway {

	public abstract IFlowObject getPredecessor2();

	public abstract void setPredecessor2(IFlowObject pred2);

	public abstract void resetPredecessor2();

}