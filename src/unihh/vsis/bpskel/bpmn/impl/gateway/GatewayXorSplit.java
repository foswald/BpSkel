package unihh.vsis.bpskel.bpmn.impl.gateway;

import unihh.vsis.bpskel.bpmn.core.AbstractSplitConditional;
import unihh.vsis.bpskel.bpmn.core.FlowObjectContainer;
import unihh.vsis.bpskel.bpmn.core.ICondition;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

/**
 * Describes Gateways which have to decide for which path to proceed.
 * @author foswald
 *
 */
public class GatewayXorSplit extends AbstractSplitConditional {
	
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
	private IFlowObject getNextValidFlowObject(){
		if(condition.evaluate()){
			return super.getOutgoingFlowObjects().first;
		}
		else{
			return super.getOutgoingFlowObjects().second;
		}
	}
	
	/**
	 * Only returns the valid flow object.
	 */
	@Override
	public FlowObjectContainer getOutgoingFlowObjects(){
		return new FlowObjectContainer(this.getNextValidFlowObject());
	}
}
