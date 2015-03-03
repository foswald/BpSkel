package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;
import bpskel.api.ITask;
import cl.niclabs.skandium.muscles.Execute;

public class ExecMuscleTask implements Execute<IDataContainer, IDataContainer> {

	ITask task;
	
	private boolean wireData;
	
	/**
	 * @param in the task to process
	 */
	public ExecMuscleTask(ITask in){
		this.task = in;
	}

	/**
	 * Call if the Skeleton Engine should wire the data to the nested skeleton call
	 */
	public void enableWireData(){
		this.wireData = true;
	}
	
	@Override
	public IDataContainer execute(IDataContainer param) throws Exception {
		if(this.wireData){
			task.setDataHandle(param);
		}
		task.execute();
		return task.getDataHandle();
	}
}
