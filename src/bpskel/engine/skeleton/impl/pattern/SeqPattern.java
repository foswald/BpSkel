package bpskel.engine.skeleton.impl.pattern;

import bpskel.bpg.impl.core.AbstractSimpleTask;
import bpskel.bpg.impl.core.IFlowObject;

public class SeqPattern implements IPattern{

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof AbstractSimpleTask) {
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
