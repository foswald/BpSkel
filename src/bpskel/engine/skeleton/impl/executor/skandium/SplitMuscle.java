package bpskel.engine.skeleton.impl.executor.skandium;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.IDataSplit;
import cl.niclabs.skandium.muscles.Split;

public class SplitMuscle implements Split<IDataContainer, IDataContainer> {

	private IDataSplit split;
	
	public SplitMuscle(IDataSplit split){
		this.split = split;
	}
	
	@Override
	public IDataContainer[] split(IDataContainer param) throws Exception {
		return split.splitData(param);
	}

}
