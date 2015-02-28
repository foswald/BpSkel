package bpskel.bpg.impl.core;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.api.ITask;

public abstract class AbstractTask extends AbstractFlowObject implements ITask{

	protected IDataContainer data;
	
	@Override
	public void setInputData(IDataContainer in) {
		this.data = in;
	}

	@Override
	public IDataContainer getResultData() {
		return this.data;
	}

}
