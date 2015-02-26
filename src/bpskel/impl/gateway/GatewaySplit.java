package bpskel.impl.gateway;

import bpskel.api.IGatewaySplit;
import bpskel.impl.core.AbstractFlowObject;
import bpskel.impl.core.IFlowObject;


/**
 * An AbstractSplitFlowObject class has exactly one preceding FlowObject and two (restricted) succeeding FlowObjects.
 * @author foswald
 *
 */
public abstract class GatewaySplit extends AbstractFlowObject implements IGatewaySplit{
	
	private IFlowObject succ2;

	/**
	 * @see bpskel.api.IGatewaySplit#getSuccessor2()
	 */
	@Override
	public IFlowObject getSuccessor2() {
		return succ2;
	}

	/**
	 * @see bpskel.api.IGatewaySplit#setSuccessor2(bpskel.impl.core.IFlowObject)
	 */
	@Override
	public void setSuccessor2(IFlowObject succ2) {
		this.succ2 = succ2;
	}
	
	/**
	 * @see bpskel.api.IGatewaySplit#resetSuccessor2()
	 */
	@Override
	public void resetSuccessor2() {
		this.succ2 = null;
	}
	
	/**
	 * @see bpskel.api.IGatewaySplit#resetSuccessor2()
	 */
	@Override
	public boolean isEmpty(){
		return super.isEmpty() && succ2==null;
	}
	
}
