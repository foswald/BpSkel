package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.api.skeleton.ExecMuscleTask;
import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import cl.niclabs.skandium.muscles.Execute;
import cl.niclabs.skandium.skeletons.Pipe;
import cl.niclabs.skandium.skeletons.Skeleton;

public class PipePattern<T> implements IPattern<T> {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof ITask 
				&& start.getOutgoingFlowObjects().first instanceof ITask){
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T createSkeleton(Component<T> start) {
		// TODO: This code has to be moved to third party, as here we have the Skandium dependency
		Execute<IDataContainer, IDataContainer> ex1 = new ExecMuscleTask((ITask)start);
		Execute<IDataContainer, IDataContainer> ex2 = new ExecMuscleTask((ITask)start.getOutgoingFlowObjects().first);
		Skeleton<IDataContainer,IDataContainer> s = new Pipe<IDataContainer,IDataContainer>(ex1, ex2);
		return (T)s;
	}

	@Override
	public Component<T> foldToComponent(IFlowObject start) {
		if(matchPattern(start)){
			return new Component<T>(this, start, start.getOutgoingFlowObjects().first);
		}
		else{
			throw new Error("Pattern beginning with start node does not match!");
		}
	}

}
