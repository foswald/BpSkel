package bpskel.engine.skeleton.impl.executor.skandium;

import java.util.concurrent.Future;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.core.IDataMerge;
import bpskel.bpg.impl.core.IDataSplit;
import bpskel.bpg.impl.core.IFlowObject;
import bpskel.bpg.impl.core.IForTask;
import bpskel.bpg.impl.core.StartElement;
import bpskel.bpg.impl.gateway.GatewayJoin;
import bpskel.bpg.impl.gateway.GatewaySplit;
import bpskel.bpg.impl.gateway.GatewayXorSplit;
import bpskel.engine.skeleton.api.ISkeleton;
import bpskel.engine.skeleton.api.ISkeletonAPI;
import bpskel.engine.skeleton.impl.pattern.PatternType;
import bpskel.engine.skeleton.impl.pattern.ProxyTask;
import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.muscles.Condition;
import cl.niclabs.skandium.muscles.Execute;
import cl.niclabs.skandium.muscles.Merge;
import cl.niclabs.skandium.muscles.Split;
import cl.niclabs.skandium.skeletons.For;
import cl.niclabs.skandium.skeletons.Fork;
import cl.niclabs.skandium.skeletons.If;
import cl.niclabs.skandium.skeletons.Map;
import cl.niclabs.skandium.skeletons.Pipe;
import cl.niclabs.skandium.skeletons.Seq;
import cl.niclabs.skandium.skeletons.Skeleton;
import cl.niclabs.skandium.skeletons.While;

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
		System.out.println("SkelRef queried: " + task.getSkeletonReference().getSkeletonRef());
		return task.getSkeletonReference().getSkeletonRef();
	}


	@Override
	public ISkeleton createSeqSkeleton(IFlowObject startingNode) {
		ExecMuscleTask ex = new ExecMuscleTask((ITask)startingNode);
		
		
		// TODO: Move to other location? This is more of a hack
		// If seq node is in split chain, we have to wire the data, so check for IDataSplit
		IFlowObject pred = startingNode.getPredecessor();
		while(!(pred instanceof StartElement || pred instanceof GatewaySplit)){
			if(pred instanceof IDataSplit){
				System.out.println("Found Datasplit, enable skandium data wiring");
				ex.enableWireData();
				break;
			}
			pred = pred.getPredecessor();
		}		
		
		
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
		ProxyTask task = (ProxyTask) startingNode.getSuccessor();
		GatewayXorSplit split = (GatewayXorSplit)task.getSuccessor();
		Condition<IDataContainer> cond = new ConditionMuscle(split.getCondition());
		Skeleton<IDataContainer, IDataContainer> skel = this.getSkeletonFromProxy(task);
		
		ISkeleton s = new SkeletonWrapper(new While<IDataContainer>(skel, cond));
		return s;
	}

	@Override
	public ISkeleton createForSkeleton(IFlowObject startingNode) {
		IForTask forTask = (IForTask)startingNode;
		Execute<IDataContainer, IDataContainer> ex = new ExecMuscleTask((ITask)startingNode);
		ISkeleton s = new SkeletonWrapper(new For<>(ex, forTask.getNumIterations()));
		return s;
	}


	@Override
	public ISkeleton createIfSkeleton(IFlowObject startingNode) {
		GatewayXorSplit split = (GatewayXorSplit)startingNode;
		Condition<IDataContainer> cond = new ConditionMuscle(split.getCondition());
		
		Skeleton<IDataContainer, IDataContainer> case1 = this.getSkeletonFromProxy(split.getSuccessor());
		Skeleton<IDataContainer, IDataContainer> case2 = this.getSkeletonFromProxy(split.getSuccessor2());
		ISkeleton s = new SkeletonWrapper(new If<IDataContainer, IDataContainer>(cond, case1, case2));
		return s;
	}


	@Override
	public ISkeleton createMapSkeleton(IFlowObject startingNode) {
		Split<IDataContainer,IDataContainer> splitMuscle = new SplitMuscle((IDataSplit) startingNode);
		Skeleton<IDataContainer, IDataContainer> skel = this.getSkeletonFromProxy(startingNode.getSuccessor());
		Merge<IDataContainer, IDataContainer> mergeMuscle = new MergeMuscle((IDataMerge) startingNode.getSuccessor().getSuccessor());
		ISkeleton s = new SkeletonWrapper(new Map<IDataContainer, IDataContainer>(splitMuscle, skel, mergeMuscle));
		return s;
	}


	@Override
	public ISkeleton createForkSkeleton(IFlowObject startingNode) {
		GatewaySplit split = (GatewaySplit) startingNode;
		
		IDataContainer data1 = new ProxyDataContainer();
		IDataContainer data2 = new ProxyDataContainer();		
		Split<IDataContainer,IDataContainer> splitMuscle = new SplitMuscleFork(data1, data2);

		Skeleton<IDataContainer, IDataContainer> skel1 = this.getSkeletonFromProxy(split.getSuccessor());
		Skeleton<IDataContainer, IDataContainer> skel2 = this.getSkeletonFromProxy(split.getSuccessor2());
		
		@SuppressWarnings("unchecked")
		Skeleton<IDataContainer, IDataContainer>[] skel = new Skeleton[2];
		skel[0] = skel1;
		skel[1] = skel2;
		
		Merge<IDataContainer, IDataContainer> mergeMuscle = new MergeMuscleFork();
		
		ISkeleton s = new SkeletonWrapper(new Fork<IDataContainer, IDataContainer>(splitMuscle, skel, mergeMuscle));
		return s;
	}


	@Override
	public ISkeleton createDCSkeleton(IFlowObject startingNode) {
		// TODO Auto-generated method stub
		throw new Error();
	}

	@Override
	public void execute(ISkeleton skeleton) {
		Skeleton<IDataContainer, IDataContainer> skel = skeleton.getSkeletonRef();

		Skandium skandium = new Skandium(8);

		long init = System.currentTimeMillis();
		Future<IDataContainer> future = skel.input(new ProxyDataContainer());
		
		IDataContainer out;
		try {
			out = future.get();
			System.out.println((System.currentTimeMillis() - init)+"[ms].");

			skandium.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
