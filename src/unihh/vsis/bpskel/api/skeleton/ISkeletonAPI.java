package unihh.vsis.bpskel.api.skeleton;

import unihh.vsis.bpskel.blockconverter.pattern.PatternType;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

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
}
