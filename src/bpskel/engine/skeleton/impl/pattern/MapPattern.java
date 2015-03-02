package bpskel.engine.skeleton.impl.pattern;

import bpskel.bpg.impl.core.IDataMerge;
import bpskel.bpg.impl.core.IDataSplit;
import bpskel.bpg.impl.core.IFlowObject;

public class MapPattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof IDataSplit){
			if(start.getSuccessor() instanceof ProxyTask){
				if(start.getSuccessor().getSuccessor() instanceof IDataMerge){
					return true;
				}
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
