package unihh.vsis.bpskel.executor.skandium;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;

public class SkeletonWrapper implements ISkeleton {
	
	private Object skeleton;
	
	public SkeletonWrapper(Object skelRef){
		this.skeleton = skelRef;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getSkeletonRef() {
		return (T) skeleton;
	}

}
