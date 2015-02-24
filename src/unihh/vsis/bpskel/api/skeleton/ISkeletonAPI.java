package unihh.vsis.bpskel.api.skeleton;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public interface ISkeletonAPI {
	
	ISkeleton createPipeSkeleton(IFlowObject startingNode);
	ISkeleton createWhileSkeleton(IFlowObject startingNode);
	
}
