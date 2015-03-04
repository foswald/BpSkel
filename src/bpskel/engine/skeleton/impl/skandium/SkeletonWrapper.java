package bpskel.engine.skeleton.impl.skandium;

import bpskel.engine.skeleton.api.ISkeleton;

class SkeletonWrapper implements ISkeleton {
	
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
