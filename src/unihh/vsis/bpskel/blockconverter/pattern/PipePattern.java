package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.exceptions.PatternMismatchException;

public class PipePattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof ITask 
				&& start.getOutgoingFlowObjects().first instanceof ITask){
			return true;
		}
		return false;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start) throws PatternMismatchException {
		if(!matchPattern(start)) {
			throw new PatternMismatchException();
		}
		else {
			return start.getOutgoingFlowObjects().first;
		}
	}

	@Override
	public SkeletonType getPatternType() {
		return SkeletonType.PIPE;
	}
}
