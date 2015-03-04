package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;
import bpskel.api.ITask;
import cl.niclabs.skandium.muscles.Execute;

class ExecMuscleTask implements Execute<IDataContainer, IDataContainer> {

	private ITask task;

	/**
	 * @param in the task to process
	 */
	public ExecMuscleTask(ITask in){
		this.task = in;
	}

	@Override
	public IDataContainer execute(IDataContainer param) throws Exception {

		if(task.doExecuteInline()){
			return task.executeInline(param);
		}
		else {
			task.execute();
			return task.getDataHandle();
		}
	}
}
