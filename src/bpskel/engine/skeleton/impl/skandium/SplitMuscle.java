package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.splitmerge.IDataSplit;
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
