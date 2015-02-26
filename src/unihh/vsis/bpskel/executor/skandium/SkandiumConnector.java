package unihh.vsis.bpskel.executor.skandium;

import java.util.concurrent.Future;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.core.IFlowObject;
import bpskel.bpg.impl.gateway.GatewayXorSplit;
import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.api.skeleton.ISkeletonAPI;
import unihh.vsis.bpskel.blockconverter.pattern.PatternType;
import unihh.vsis.bpskel.blockconverter.pattern.ProxyTask;
import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.muscles.Condition;
import cl.niclabs.skandium.muscles.Execute;
import cl.niclabs.skandium.skeletons.If;
import cl.niclabs.skandium.skeletons.Pipe;
import cl.niclabs.skandium.skeletons.Seq;
import cl.niclabs.skandium.skeletons.Skeleton;

public class SkandiumConnector implements ISkeletonAPI {

	@Override
	public ISkeleton createSkeleton(PatternType skeletonType, IFlowObject startingNode) {
		ISkeleton skel;
		switch(skeletonType){
			case SEQ: 	skel = createSeqSkeleton(startingNode); 	break;
			case PIPE: 	skel = createPipeSkeleton(startingNode);	break;
			case IF: 	skel = createIfSkeleton(startingNode);		break;
			case WHILE: skel = createWhileSkeleton(startingNode);	break;
			case FOR: 	skel = createForSkeleton(startingNode);		break;
			case MAP: 	skel = createMapSkeleton(startingNode);		break;
			case FORK: 	skel = createForkSkeleton(startingNode);	break;
			case DC: 	skel = createDCSkeleton(startingNode);		break;
			
			default:
				skel = null;
				
		}
		
		return skel;
	}
	
	/**
	 * This helper method retrieves the skeleton reference of a ProxyTask and casts it to a Skandium Skeleton.
	 * @param node
	 * @return
	 */
	private Skeleton<IDataContainer, IDataContainer> getSkeletonFromProxy(IFlowObject node){
		ProxyTask task = ((ProxyTask)node);
		return task.getSkeletonReference().getSkeletonRef();
	}


	@Override
	public ISkeleton createSeqSkeleton(IFlowObject startingNode) {
		Execute<IDataContainer, IDataContainer> ex = new ExecMuscleTask((ITask)startingNode);
		Seq<IDataContainer, IDataContainer> seq = new Seq<IDataContainer, IDataContainer>(ex);
		ISkeleton s = new SkeletonWrapper(seq);
		return s;
	}

	
	@Override
	public ISkeleton createPipeSkeleton(IFlowObject startingNode) {
		Skeleton<IDataContainer, IDataContainer> ex1 = this.getSkeletonFromProxy(startingNode);
		Skeleton<IDataContainer, IDataContainer> ex2 = this.getSkeletonFromProxy(startingNode.getSuccessor());
		ISkeleton s = new SkeletonWrapper(new Pipe<IDataContainer,IDataContainer>(ex1, ex2));
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
		GatewayXorSplit split = (GatewayXorSplit)startingNode;
		Condition<IDataContainer> cond = new SkandiumCondition(split.getCondition());
		
		Skeleton<IDataContainer, IDataContainer> case1 = this.getSkeletonFromProxy(split.getSuccessor());
		Skeleton<IDataContainer, IDataContainer> case2 = this.getSkeletonFromProxy(split.getSuccessor2());
		ISkeleton s = new SkeletonWrapper(new If<IDataContainer, IDataContainer>(cond, case1, case2));
		return s;
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

	@Override
	public void execute(ISkeleton skeleton, IDataContainer initialInput) {
		Skeleton<IDataContainer, IDataContainer> skel = skeleton.getSkeletonRef();

		Skandium skandium = new Skandium(8);

		Future<IDataContainer> future = skel.input(initialInput);
		
		long init = System.currentTimeMillis();
		IDataContainer out;
		try {
			out = future.get();
			System.out.println((System.currentTimeMillis() - init)+"[ms]: "+(Integer)out.getData());

			skandium.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
