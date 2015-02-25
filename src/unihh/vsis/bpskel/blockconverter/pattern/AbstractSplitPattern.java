package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.GatewayJoin;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public abstract class AbstractSplitPattern implements IPattern {

	public boolean matchSplitPattern(IFlowObject start) {
		boolean valid = false;
		IFlowObject first = start.getOutgoingFlowObjects().first;
		IFlowObject second = start.getOutgoingFlowObjects().second;
		// we must have two succeeding objects, both of them must be a component to make folding possible
		
		// either one might be the direct join (in case we only have one task in an XOR), 
		// at least one must be a Component 
		boolean firstEmpty = first instanceof GatewayJoin;
		boolean secondEmpty = second instanceof GatewayJoin;

		if(firstEmpty && secondEmpty) {
			// invalid
			valid = false;
		}
		else if(!firstEmpty && secondEmpty) {
			valid = first instanceof ProxyTask;
			valid &= first.getOutgoingFlowObjects().first.equals(second);
		}
		else if(firstEmpty && !secondEmpty) {
			valid = second instanceof ProxyTask;
			valid &= second.getOutgoingFlowObjects().first.equals(first);
		}
		else if(!firstEmpty && !secondEmpty) {
			valid = (second instanceof ProxyTask) && (first instanceof ProxyTask);
			valid &= second.getOutgoingFlowObjects().first.equals(first.getOutgoingFlowObjects().first);
		}
		return valid;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start){
		if(matchSplitPattern(start)){
			IFlowObject first = start.getOutgoingFlowObjects().first;
			IFlowObject second = start.getOutgoingFlowObjects().second;
			
			if(first instanceof GatewayJoin){
				return first;
			}
			else if(second instanceof GatewayJoin){
				return second;
			}
			else {
				return first.getOutgoingFlowObjects().first;
			}
		}
		return null;
	}

}
