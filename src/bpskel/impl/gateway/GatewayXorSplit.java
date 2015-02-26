package bpskel.impl.gateway;

import bpskel.api.ICondition;
import bpskel.impl.core.IFlowObject;

/**
 * Describes Gateways which have to decide for which path to proceed.
 * @author foswald
 *
 */
public class GatewayXorSplit  extends GatewaySplit implements ICondition{
	
	protected ICondition condition;
	
	public GatewayXorSplit(ICondition condition){

		this.condition = condition;
		super.setDescription(condition.getDescription());
	}
	
	@Override
	public boolean evaluate() {
		return this.condition.evaluate();
	}
	
	/**
	 * Evaluates the condition and depending of the result returns the first or the second FlowObject: <br>
	 * 
	 * @return If the condition evaluates to True, the first object will be returned. <br>
	 * If the condition evaluates to False, the second object will be returned.
	 * 
	 */
	public IFlowObject getNextValidFlowObject(){
		if(condition.evaluate()){
			return super.getSuccessor();
		}
		else{
			return super.getSuccessor2();
		}
	}

	public ICondition getCondition() {
		return this.condition;
	}

}
