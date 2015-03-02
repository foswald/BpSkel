package bpskel.engine.skeleton.impl.pattern;

import bpskel.bpg.impl.core.IDataMerge;
import bpskel.bpg.impl.core.IDataSplit;
import bpskel.bpg.impl.core.IDataSplitConditional;
import bpskel.bpg.impl.core.IFlowObject;

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
