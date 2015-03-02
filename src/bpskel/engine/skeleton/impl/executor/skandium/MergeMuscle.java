package bpskel.engine.skeleton.impl.executor.skandium;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.IDataMerge;
import cl.niclabs.skandium.muscles.Merge;

public class MergeMuscle implements Merge<IDataContainer, IDataContainer> {

	private IDataMerge dataMerge;
	public MergeMuscle(IDataMerge merge){
		this.dataMerge = merge;
	}
	
	@Override
	public IDataContainer merge(IDataContainer[] param) throws Exception {
		return this.dataMerge.mergeData(param);
	}

}
