package unihh.vsis.bpskel.bpmn.api;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public interface ITask extends IFlowObject{

	void setInputData(IDataContainer in);
	void execute();
	/**
	 * Returns a data container describing any type of result data from previous tasks or Gateways (i.e. FlowObjects)
	 * These will be evaluated by IConditional Objects such as SplitGateways.
	 * Any FlowObject has either ResultData of preceding FlowObjects (such as Gateways) or produces them (such as Tasks)
	 * @return
	 */
	IDataContainer getResultData();		
}
