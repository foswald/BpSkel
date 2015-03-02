package tests.bpskel.bpg.dc;

import java.util.ArrayList;
import java.util.List;

import tests.bpskel.shared.DataContainer;
import bpskel.api.AbstractDataMerge;
import bpskel.api.IDataContainer;

public class MergeStringListData extends AbstractDataMerge {

	@Override
	public IDataContainer mergeData(IDataContainer[] data) {
		
		ArrayList<String> list = new ArrayList<String>(data.length);
		
		for(IDataContainer d:data){
			List<String> l = (List<String>) d.getData();
			list.addAll(l);
		}
		
		return new DataContainer<ArrayList<String>>(list);
		
	}

}
