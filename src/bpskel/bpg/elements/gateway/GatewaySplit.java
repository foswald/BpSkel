package bpskel.bpg.elements.gateway;

import bpskel.api.IGatewaySplit;
import bpskel.bpg.elements.core.FlowObject;
import bpskel.bpg.elements.core.IFlowObject;


/**
 * An AbstractSplitFlowObject class has exactly one preceding FlowObject and two (restricted) succeeding FlowObjects.
 * @author foswald
 *
 */
public abstract class GatewaySplit extends FlowObject implements IGatewaySplit{
	
	private IFlowObject succ2;

	/**
	 * @see bpskel.api.IGatewaySplit#getSuccessor2()
	 */
	@Override
	public IFlowObject getSuccessor2() {
		if(succ2 == null){
			throw new NullPointerException("Queried successor is not set");
		}
		return succ2;
	}

	/**
	 * @see bpskel.api.IGatewaySplit#setSuccessor2(bpskel.bpg.elements.core.IFlowObject)
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
