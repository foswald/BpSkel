package unihh.vsis.bpskel.executor.skandium;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.api.skeleton.ISkeletonAPI;
import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import cl.niclabs.skandium.muscles.Execute;
import cl.niclabs.skandium.skeletons.Pipe;

public class SkandiumConnector implements ISkeletonAPI {

	@Override
	public ISkeleton createPipeSkeleton(IFlowObject start) {
		Execute<IDataContainer, IDataContainer> ex1 = new ExecMuscleTask((ITask)start);
		Execute<IDataContainer, IDataContainer> ex2 = new ExecMuscleTask((ITask)start.getOutgoingFlowObjects().first);
		ISkeleton s = new SkandiumSkeleton(new Pipe<IDataContainer,IDataContainer>(ex1, ex2));
		return s;
	}

	@Override
	public ISkeleton createWhileSkeleton(IFlowObject start) {
		// TODO Auto-generated method stub
		return null;
	}

}
