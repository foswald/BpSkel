package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.exceptions.PatternMismatchException;

public class PipePattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof ProxyTask 
				&& start.getOutgoingFlowObjects().first instanceof ProxyTask){
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
	public PatternType getPatternType() {
		return PatternType.PIPE;
	}
}
