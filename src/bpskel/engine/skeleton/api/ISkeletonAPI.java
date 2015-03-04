package bpskel.engine.skeleton.api;

import bpskel.bpg.conversion.pattern.PatternType;
import bpskel.bpg.elements.core.IFlowObject;

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

	int execute(ISkeleton skeletonReference);
	
	void setNumThreads(int numThreads);
}
