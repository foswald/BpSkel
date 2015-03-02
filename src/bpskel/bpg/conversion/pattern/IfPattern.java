package bpskel.bpg.conversion.pattern;

import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.gateway.GatewayXorSplit;

public class IfPattern extends AbstractSplitPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		boolean valid = false;
		if(start instanceof GatewayXorSplit){
			valid = super.matchSplitPattern((GatewayXorSplit)start);

		}
		return valid;
	}

	@Override
	public PatternType getPatternType() {
		// TODO Auto-generated method stub
		return PatternType.IF;
	}

}
