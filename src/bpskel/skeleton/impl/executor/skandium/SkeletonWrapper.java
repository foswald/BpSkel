package bpskel.skeleton.impl.executor.skandium;

import bpskel.skeleton.api.ISkeleton;

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
