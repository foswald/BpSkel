package bpskel.api;

import bpskel.bpg.elements.core.FlowObject;

/**
 * This class has package visibility and should not be directly implemented outside this scope
 * @author foswald
 *
 */
abstract class AbstractTask extends FlowObject implements ITask{

	protected IDataContainer data;
	
	public AbstractTask(){
		this.data = new DataContainer(null);
		this.setDescription("Task");
	}
		
	@Override
	public IDataContainer getDataHandle() {
		return this.data;
	}
	
	@Override
	public void setDataHandle(IDataContainer dataHandle) {
		this.data = dataHandle;
	}
}
