package bpskel.api;

import bpskel.impl.core.IFlowObject;
import bpskel.impl.gateway.IGateway;

public interface IGatewaySplit extends IGateway{

	public abstract IFlowObject getSuccessor2();

	public abstract void setSuccessor2(IFlowObject succ2);

	public abstract void resetSuccessor2();

}