package bpskel.bpg.conversion.pattern;

import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplit;
import bpskel.bpg.elements.splitmerge.IDataSplitConditional;

public class MapPattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof IDataSplit && !(start instanceof IDataSplitConditional)){
			if(start.getSuccessor() instanceof ProxyTask){
				return (start.getSuccessor().getSuccessor() instanceof IDataMerge);
			}
		}
		return false;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start) {
		if(matchPattern(start)){
			return start.getSuccessor().getSuccessor();
		}
		return null;
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.MAP;
	}

}
