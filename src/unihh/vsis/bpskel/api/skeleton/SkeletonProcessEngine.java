package unihh.vsis.bpskel.api.skeleton;

import unihh.vsis.bpskel.blockconverter.PatternMatcher;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IProcessEngine;

public class SkeletonProcessEngine implements IProcessEngine{

	
	public SkeletonProcessEngine(){
		
	}
	@Override
	public void execute(BusinessProcess pro) {
		run(pro.getStart());
	}

	private void run(IFlowObject f) {
		PatternMatcher m = new PatternMatcher().getInstance();
		m.matchPattern(f);
		
	}
	
}
