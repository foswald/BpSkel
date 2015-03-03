package bpskel.api;

import java.util.concurrent.ExecutionException;

import bpskel.bpg.elements.core.IFlowObject;

public interface ITask extends IFlowObject{

	/**Returns a data container describing any type of result data from previous tasks or Gateways (i.e. FlowObjects)
	 * These will be evaluated by IConditional Objects such as SplitGateways.
	 * Any FlowObject has either ResultData of preceding FlowObjects (such as Gateways) or produces them (such as Tasks)
	 * 
	 * @note the data might be invalid if altered by execute
	 * @return the current data handle of the task
	 */
	IDataContainer getDataHandle();
	
	void setDataHandle(IDataContainer dataHandle);
		
	
	/** Executes any task possibly working on inputData
	 * 
	 * @throws ExecutionException
	 */
	void execute() throws ExecutionException;
	
}
