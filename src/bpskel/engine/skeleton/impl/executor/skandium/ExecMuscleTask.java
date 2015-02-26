package bpskel.engine.skeleton.impl.executor.skandium;

import bpskel.api.IDataContainer;
import bpskel.api.ITask;
import cl.niclabs.skandium.muscles.Execute;

public class ExecMuscleTask implements Execute<IDataContainer, IDataContainer> {

	ITask task;
	
	public ExecMuscleTask(ITask in){
		this.task = in;
	}
	
	@Override
	public IDataContainer execute(IDataContainer param) throws Exception {
		task.execute();
		return task.getResultData();
	}
}
