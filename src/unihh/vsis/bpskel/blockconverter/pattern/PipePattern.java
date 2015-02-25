package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public class PipePattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof ProxyTask 
				&& start.getSuccessor() instanceof ProxyTask){
			return true;
		}
		return false;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start){
		if(!matchPattern(start)) {
			return null;
		}
		else {
			return start.getSuccessor();
		}
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.PIPE;
	}
}
