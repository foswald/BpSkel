package unihh.vsis.bpskel.api.skeleton;

import unihh.vsis.bpskel.blockconverter.PatternMatcher;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IProcessEngine;
import unihh.vsis.bpskel.executor.skandium.SkandiumConnector;

public class SkeletonProcessEngine implements IProcessEngine{

	
	public SkeletonProcessEngine(){
		
	}
	@Override
	public void execute(BusinessProcess pro) {
		run(pro.getStart());
	}

	private void run(IFlowObject f) {
		PatternMatcher m = new PatternMatcher();
		//m.matchPattern(f);
		
	}
	@Override
	public boolean initialize() {
		// Setup Skandium engine here
		return false;
	}
	
}
