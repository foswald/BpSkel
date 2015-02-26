package bpskel.skeleton.api;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.IFlowObject;
import bpskel.skeleton.impl.pattern.PatternType;

public interface ISkeletonAPI {
	
	ISkeleton createSkeleton(PatternType skeletonType, IFlowObject startingNode);
	
	ISkeleton createSeqSkeleton(IFlowObject startingNode);
	ISkeleton createPipeSkeleton(IFlowObject startingNode);
	ISkeleton createWhileSkeleton(IFlowObject startingNode);
	ISkeleton createForSkeleton(IFlowObject startingNode);
	ISkeleton createIfSkeleton(IFlowObject startingNode);
	ISkeleton createMapSkeleton(IFlowObject startingNode);
	ISkeleton createForkSkeleton(IFlowObject startingNode);
	ISkeleton createDCSkeleton(IFlowObject startingNode);

	void execute(ISkeleton iSkeleton, IDataContainer initialInput);
}
