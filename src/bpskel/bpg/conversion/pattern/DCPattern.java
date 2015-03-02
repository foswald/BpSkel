package bpskel.bpg.conversion.pattern;

import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplitConditional;

public class DCPattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof IDataSplitConditional){
			if(start.getSuccessor() instanceof ProxyTask){
				return (start.getSuccessor().getSuccessor() instanceof IDataMerge);
			}
		}
		return false;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start) {
		if(this.matchPattern(start)){
			return start.getSuccessor().getSuccessor();
		}
		return null;
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.DC;
	}

}
