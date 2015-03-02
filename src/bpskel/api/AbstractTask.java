package bpskel.api;

import bpskel.bpg.elements.core.FlowObject;

/**
 * This class has package visibility and should not be directly implemented outside this scope
 * @author foswald
 *
 */
abstract class AbstractTask extends FlowObject implements ITask{

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
