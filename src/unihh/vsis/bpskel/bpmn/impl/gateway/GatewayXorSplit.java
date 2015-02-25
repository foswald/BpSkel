package unihh.vsis.bpskel.bpmn.impl.gateway;

import unihh.vsis.bpskel.bpmn.core.AbstractGatewaySplitConditional;
import unihh.vsis.bpskel.bpmn.core.FlowObjectContainer;
import unihh.vsis.bpskel.bpmn.core.ICondition;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

/**
 * Describes Gateways which have to decide for which path to proceed.
 * @author foswald
 *
 */
public class GatewayXorSplit extends AbstractGatewaySplitConditional {
	
	public GatewayXorSplit(ICondition condition){
		super(condition);
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
			return super.getOutgoingFlowObjects().first;
		}
		else{
			return super.getOutgoingFlowObjects().second;
		}
	}

}
