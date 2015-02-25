package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
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
	public IFlowObject getEndElement(IFlowObject start)
			throws PatternMismatchException {
		return start;
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.SEQ;
	}

}
