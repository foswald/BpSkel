package bpskel.api;

import bpskel.bpg.elements.core.FlowObject;
import bpskel.bpg.elements.splitmerge.IDataSplit;

public abstract class AbstractDataSplit extends FlowObject implements IDataSplit {
	
	IDataContainer dataHandle;
	
	@Override
	public void setDataHandle(IDataContainer dataHandle){
		this.dataHandle = dataHandle;
	}
	
	@Override
	public IDataContainer getDataHandle(){
		return this.dataHandle;
	}
}
