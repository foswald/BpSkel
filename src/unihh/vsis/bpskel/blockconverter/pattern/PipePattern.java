package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

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
	public ISkeleton createSkeleton(Component start) {
		return null;
	}

	@Override
	public Component foldToComponent(IFlowObject start) {
		if(matchPattern(start)){
			return new Component(this, start, start.getOutgoingFlowObjects().first);
		}
		else{
			throw new Error("Pattern beginning with start node does not match!");
		}
	}

}
