package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;

public interface IDataHandle {
	/**
	 * If no data wiring is active, set the data ref manually here.
	 * @param dataHandle
	 */
	void setDataHandle(IDataContainer dataHandle);
	
	/**Returns a data container describing any type of result data from previous tasks or Gateways (i.e. FlowObjects)
	 * These will be evaluated by IConditional Objects such as SplitGateways.
	 * Any FlowObject has either ResultData of preceding FlowObjects (such as Gateways) or produces them (such as Tasks)
	 * 
	 * @note the data might be invalid if altered by execute
	 * @return the current data handle of the task
	 */
	IDataContainer getDataHandle();

	boolean isDataHandleMutable();
	
	void makeDataHandleMutable(boolean b);

	void overwriteDatahandle(IDataContainer dataHandle);
}
