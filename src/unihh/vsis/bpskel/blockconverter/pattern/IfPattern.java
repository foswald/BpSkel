package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.blockconverter.ProxyTask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IGateway;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorSplit;

public class IfPattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof GatewayXorSplit){
			IFlowObject first = start.getOutgoingFlowObjects().first;
			IFlowObject second = start.getOutgoingFlowObjects().second;
			// we must have two succeeding objects, both of them must be a component to make folding possible
			
			// either one might be the direct join (in case we only have one task in an XOR), 
			// at least one must be a Component 
			boolean firstEmpty = true, secondEmpty = true;
			if((first instanceof IGateway && ((IGateway)first).isFlowJoin())){
				firstEmpty = false;
			}
			// first branch has no component, so the other one must have
			if((second instanceof IGateway && ((IGateway)second).isFlowJoin())) {
				secondEmpty = false;
			}
			if(firstEmpty && secondEmpty) {
				// invalid
				return false;
			}
			else if(!firstEmpty && secondEmpty) {
				return first instanceof ProxyTask;
			}
			else if(firstEmpty && !secondEmpty) {
				return second instanceof ProxyTask;
			}
			else if(!firstEmpty && !secondEmpty) {
				return (second instanceof ProxyTask) && (first instanceof ProxyTask);
			}
			
		}
		return false;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SkeletonType getPatternType() {
		// TODO Auto-generated method stub
		return SkeletonType.IF;
	}

}
