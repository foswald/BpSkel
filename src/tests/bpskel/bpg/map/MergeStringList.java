package tests.bpskel.bpg.map;

import java.util.ArrayList;

import bpskel.api.AbstractDataMerge;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class MergeStringList extends AbstractDataMerge {

	@Override
	public IDataContainer mergeData(IDataContainer[] data) {
		
		ArrayList<String> list = new ArrayList<String>(data.length);
		
		for(IDataContainer d:data){
			list.add((String) d.getData());
		}
		
		return new DataContainer(list);
		
	}

}
