package bpskel.engine.skeleton.impl.pattern;

import bpskel.impl.core.IFlowObject;
import bpskel.impl.gateway.GatewayJoin;
import bpskel.impl.gateway.GatewaySplit;

public abstract class AbstractSplitPattern implements IPattern {

	public boolean matchSplitPattern(GatewaySplit start) {
		boolean valid = false;
		IFlowObject first = start.getSuccessor();
		IFlowObject second = start.getSuccessor2();
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
			valid &= first.getSuccessor().equals(second);
		}
		else if(firstEmpty && !secondEmpty) {
			valid = second instanceof ProxyTask;
			valid &= second.getSuccessor().equals(first);
		}
		else if(!firstEmpty && !secondEmpty) {
			valid = (second instanceof ProxyTask) && (first instanceof ProxyTask);
			valid &= second.getSuccessor().equals(first.getSuccessor());
		}
		return valid;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start){
		if(start instanceof GatewaySplit){
			GatewaySplit split = (GatewaySplit)start;
		
			if(matchSplitPattern(split)){
				IFlowObject first = split.getSuccessor();
				IFlowObject second = split.getSuccessor2();
				
				if(first instanceof GatewayJoin){
					return first;
				}
				else if(second instanceof GatewayJoin){
					return second;
				}
				else {
					return first.getSuccessor();
				}
			}
		}
		return null;
	}

}
