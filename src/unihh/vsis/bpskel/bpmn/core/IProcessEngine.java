package unihh.vsis.bpskel.bpmn.core;

import unihh.vsis.bpskel.bpmn.api.BusinessProcess;

public interface IProcessEngine {
	boolean initialize();
	void execute(BusinessProcess pro);
}
