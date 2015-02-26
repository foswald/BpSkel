package bpskel.bpg.api;

import java.util.concurrent.ExecutionException;

import bpskel.bpg.impl.core.IFlowObject;

public interface ITask extends IFlowObject{

	void setInputData(IDataContainer in);
	void execute() throws ExecutionException;
	/**
	 * Returns a data container describing any type of result data from previous tasks or Gateways (i.e. FlowObjects)
	 * These will be evaluated by IConditional Objects such as SplitGateways.
	 * Any FlowObject has either ResultData of preceding FlowObjects (such as Gateways) or produces them (such as Tasks)
	 * @return
	 */
	IDataContainer getResultData();
}
