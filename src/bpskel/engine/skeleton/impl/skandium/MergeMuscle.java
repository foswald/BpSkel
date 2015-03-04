package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import cl.niclabs.skandium.muscles.Merge;

class MergeMuscle implements Merge<IDataContainer, IDataContainer> {

	private IDataMerge dataMerge;
	public MergeMuscle(IDataMerge merge){
		this.dataMerge = merge;
	}
	
	@Override
	public IDataContainer merge(IDataContainer[] param) throws Exception {
		return this.dataMerge.mergeData(param);
	}

}
