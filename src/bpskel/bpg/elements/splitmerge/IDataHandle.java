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

	/**
	 * If the handle is mutable, it might be overwritten using setDataHandle
	 * @return true, if the dataHandle is mutable.
	 */
	boolean isDataHandleMutable();
	
	/**
	 * Make the datahandle of this mutable to change it.
	 * @param b the mutable status of the datahandle 
	 */
	void makeDataHandleMutable(boolean b);

	/**
	 * Explicitly overwrites the datacontainer of the datahandle. This is the same as calling <br>
	 * <code>setDataHandleMutbale(true) </code><br>
	 * <code>setDataHandle(newHandle) </code>
	 * @param dataHandle the new container of this datahandle
	 */
	void overwriteDatahandle(IDataContainer dataHandle);
}
