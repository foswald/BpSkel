package bpskel.api;

import bpskel.bpg.elements.core.FlowObject;
import bpskel.bpg.elements.splitmerge.IDataHandle;

/**
 * This class has package visibility and should not be directly implemented outside this scope
 * @author foswald
 *
 */
abstract class AbstractDataHandle extends FlowObject implements IDataHandle{

	private IDataContainer dataHandle;
	
	private boolean dataHandleMutable;
	
	public AbstractDataHandle(){		
		this.setDescription("Task");
		
		// use default instance of dataHandle
		this.dataHandle = new DataContainer(null);
		makeDataHandleMutable(false);
	}
		
	@Override
	public IDataContainer getDataHandle() {
		return this.dataHandle;
	}
	
	@Override
	public void setDataHandle(IDataContainer dataHandle) {
		if(!this.isDataHandleMutable()) {
			throw new Error("Trying to set immutable data handle");
		}
		this.dataHandle = dataHandle;
		makeDataHandleMutable(false);
	}
	
	@Override
	public void overwriteDatahandle(IDataContainer dataHandle){
		dataHandleMutable = true;
		setDataHandle(dataHandle);
	}
	
	@Override
	public boolean isDataHandleMutable(){
		return dataHandleMutable;
	}
	
	@Override
	public void makeDataHandleMutable(boolean b){
		this.dataHandleMutable = b;
	}
}
