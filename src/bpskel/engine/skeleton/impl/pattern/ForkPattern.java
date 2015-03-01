package bpskel.engine.skeleton.impl.pattern;

import bpskel.bpg.impl.core.IFlowObject;
import bpskel.bpg.impl.gateway.GatewayAndSplit;

public class ForkPattern extends AbstractSplitPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		boolean valid = false;
		if(start instanceof GatewayAndSplit){
			valid = super.matchSplitPattern((GatewayAndSplit)start);
		}
		return valid;
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.FORK;
	}

}
