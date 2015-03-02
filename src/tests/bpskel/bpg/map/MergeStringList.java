package tests.bpskel.bpg.map;

import java.util.ArrayList;

import tests.bpskel.shared.DataContainer;
import bpskel.api.AbstractDataMerge;
import bpskel.api.IDataContainer;

public class MergeStringList extends AbstractDataMerge {

	@Override
	public IDataContainer mergeData(IDataContainer[] data) {
		
		ArrayList<String> list = new ArrayList<String>(data.length);
		
		for(IDataContainer d:data){
			list.add((String) d.getData());
		}
		
		return new DataContainer<ArrayList<String>>(list);
		
	}

}
