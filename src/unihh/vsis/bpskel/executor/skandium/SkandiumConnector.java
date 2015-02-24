package unihh.vsis.bpskel.executor.skandium;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.api.skeleton.ISkeletonAPI;
import unihh.vsis.bpskel.blockconverter.pattern.SkeletonType;
import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import cl.niclabs.skandium.muscles.Execute;
import cl.niclabs.skandium.skeletons.Pipe;
import cl.niclabs.skandium.skeletons.Seq;

public class SkandiumConnector implements ISkeletonAPI {

	@Override
	public ISkeleton createSkeleton(SkeletonType skeletonType, IFlowObject startingNode) {
		switch(skeletonType){
			case SEQ: createSeqSkeleton(startingNode);
		default:
			break;
				
		}
		
		return null;
	}


	@Override
	public ISkeleton createSeqSkeleton(IFlowObject startingNode) {
		Execute<IDataContainer, IDataContainer> ex = new ExecMuscleTask((ITask)startingNode);
		Seq<IDataContainer, IDataContainer> seq = new Seq<IDataContainer, IDataContainer>(ex);
		ISkeleton s = new SkandiumSkeleton(seq);
		return s;
	}

	
	@Override
	public ISkeleton createPipeSkeleton(IFlowObject startingNode) {
		Execute<IDataContainer, IDataContainer> ex1 = new ExecMuscleTask((ITask)startingNode);
		Execute<IDataContainer, IDataContainer> ex2 = new ExecMuscleTask((ITask)startingNode.getOutgoingFlowObjects().first);
		ISkeleton s = new SkandiumSkeleton(new Pipe<IDataContainer,IDataContainer>(ex1, ex2));
		return s;
	}

	@Override
	public ISkeleton createWhileSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISkeleton createForSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISkeleton createIfSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISkeleton createMapSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISkeleton createForkSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ISkeleton createDCSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		return null;
	}


}
