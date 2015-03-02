package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;
import cl.niclabs.skandium.muscles.Split;

/**
 * Fork always splits to 2 (max divisions for bpg)
 * @author ferdinand
 *
 */
class SplitMuscleFork implements Split<IDataContainer, IDataContainer>{

	private IDataContainer data1;
	private IDataContainer data2;
	
	public SplitMuscleFork(IDataContainer data1, IDataContainer data2){
		this.data1 = data1;
		this.data2 = data2;
	}
	
	@Override
	public IDataContainer[] split(IDataContainer param) throws Exception {
		IDataContainer[] dc = {this.data1, this.data2};
		return dc;
	}

}
