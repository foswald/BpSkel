package bpskel.api;

import java.util.concurrent.ExecutionException;

import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.splitmerge.IDataHandle;

public interface ITask extends IFlowObject, IDataHandle{

	/** Executes any task possibly working on inputData
	 * 
	 * @throws ExecutionException
	 */
	void execute() throws ExecutionException;
	
	IDataContainer executeInline(IDataContainer param) throws ExecutionException;
	
	boolean doExecuteInline();
	
	void setExecuteInle(boolean b);
}
