package bpskel.bpg.conversion.pattern;

import bpskel.api.AbstractForTask;
import bpskel.api.AbstractTask;
import bpskel.bpg.elements.core.IFlowObject;

public class SeqPattern implements IPattern{

	@Override
	public boolean matchPattern(IFlowObject start) {
		if((start instanceof AbstractTask) && !(start instanceof AbstractForTask) ){
			return true;
		}
		return false;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start){
		return start;
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.SEQ;
	}

}
