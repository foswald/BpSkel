package bpskel.engine.skeleton.impl.skandium;

import cl.niclabs.skandium.muscles.Merge;
import bpskel.api.IDataContainer;

public class MergeMuscleFork implements Merge<IDataContainer, IDataContainer>{

	@Override
	public IDataContainer merge(IDataContainer[] r) throws Exception {
		System.out.println("Merging this: " + r.toString());
		
		return null;		
	}
}