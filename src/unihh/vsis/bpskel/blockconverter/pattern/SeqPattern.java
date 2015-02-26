package unihh.vsis.bpskel.blockconverter.pattern;

import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.core.IFlowObject;
import unihh.vsis.bpskel.exceptions.PatternMismatchException;

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
