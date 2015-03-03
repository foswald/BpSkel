package bpskel.api;

import bpskel.bpg.elements.core.FlowObject;
import bpskel.bpg.elements.splitmerge.IDataMerge;

public abstract class AbstractDataMerge extends FlowObject implements IDataMerge{
	
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
