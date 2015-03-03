package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;
import cl.niclabs.skandium.muscles.Merge;

public class MergeMuscleFork implements Merge<IDataContainer, IDataContainer>{

	@Override
	public IDataContainer merge(IDataContainer[] r) throws Exception {
				
		return new DataContainer(r);		
	}
}