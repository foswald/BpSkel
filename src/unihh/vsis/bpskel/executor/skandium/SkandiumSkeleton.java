package unihh.vsis.bpskel.executor.skandium;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import cl.niclabs.skandium.skeletons.Skeleton;

public class SkandiumSkeleton implements ISkeleton {
	
	Skeleton<IDataContainer, IDataContainer> skelRef;
	
	public SkandiumSkeleton(Skeleton<IDataContainer, IDataContainer> skelRef){
		this.skelRef = skelRef;
	}

	@Override
	public Object getSkeletonRef() {
		return skelRef;
	}

}
