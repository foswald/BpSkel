package bpskel.bpg.impl.core;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.api.ITask;

/**
 * This class has package visibility and should not be directly implemented outside this scope
 * @author foswald
 *
 */
abstract class AbstractTask extends AbstractFlowObject implements ITask{

	protected IDataContainer data;
		
	@Override
	public void setInputData(IDataContainer in) {
		this.data = in;
	}
	
	@Override
	public IDataContainer getInputData() {
		return this.data;
	}

	@Override
	public IDataContainer getResultData() {
		return this.data;
	}

}
