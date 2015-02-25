package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorSplit;

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
