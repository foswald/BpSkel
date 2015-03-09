package bpskel.api;

import java.util.concurrent.ExecutionException;

import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.splitmerge.IDataHandle;

public interface ITask extends IFlowObject, IDataHandle{

	/** Executes any task possibly working on inputData from <code>IDataHandle</code>.
	 * 
	 * @throws ExecutionException
	 */
	void execute() throws ExecutionException;
	
	
	/** Executes a task working on <code>param</code> as input data. 
	 * Returns the result after execution to the succeeding task.
	 * @param param the data to process
	 * @return the processed data
	 * @throws ExecutionException
	 */
	IDataContainer executeInline(IDataContainer param) throws ExecutionException;
	
	/** 
	 * If true, <code>executeInline</code> will be used instead of <code>execute</code> 
	 */
	boolean doExecuteInline();

	/**
	 * Set to true, to use the <code>executeInline</code> method, false otherwise.
	 * @param b
	 */
	void setExecuteInle(boolean b);
}
