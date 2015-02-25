package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayAndSplit;

public class ForkPattern extends AbstractSplitPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		boolean valid = false;
		if(start instanceof GatewayAndSplit){
			valid = super.matchSplitPattern(start);
		}
		return valid;
	}

	@Override
	public PatternType getPatternType() {
		// TODO Auto-generated method stub
		return PatternType.FORK;
	}

}
