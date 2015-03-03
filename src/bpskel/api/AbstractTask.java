package bpskel.api;

import bpskel.bpg.elements.core.FlowObject;

/**
 * This class has package visibility and should not be directly implemented outside this scope
 * @author foswald
 *
 */
abstract class AbstractTask extends FlowObject implements ITask{

	private IDataContainer dataHandle;
	
	public AbstractTask(){
		this.dataHandle = new DataContainer(null);
		this.setDescription("Task");
	}
		
	@Override
	public IDataContainer getDataHandle() {
		return this.dataHandle;
	}
	
	@Override
	public void setDataHandle(IDataContainer dataHandle) {
		this.dataHandle = dataHandle;
	}
}
