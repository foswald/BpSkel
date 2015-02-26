package bpskel.engine.skeleton.impl.pattern;

import bpskel.api.ITask;
import bpskel.impl.core.IFlowObject;

public class SeqPattern implements IPattern{

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof ITask) {
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
