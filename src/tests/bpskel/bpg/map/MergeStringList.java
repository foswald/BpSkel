package tests.bpskel.bpg.map;

import java.util.ArrayList;

import tests.bpskel.shared.DataContainer;
import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractFlowObject;
import bpskel.bpg.impl.core.IDataMerge;

public class MergeStringList extends AbstractFlowObject implements IDataMerge {

	@Override
	public IDataContainer mergeData(IDataContainer[] data) {
		
		ArrayList<String> list = new ArrayList<String>(data.length);
		
		for(IDataContainer d:data){
			list.add((String) d.getData());
		}
		
		return new DataContainer<ArrayList<String>>(list);
		
	}

}
