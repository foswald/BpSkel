package impl;

import java.util.ArrayList;

import bpskel.api.AbstractDataMerge;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class MergeConvertedData extends AbstractDataMerge {

	@Override
	public IDataContainer mergeData(IDataContainer[] data) {
		
		ArrayList<ClientData> list = new ArrayList<>();
		for(IDataContainer dc:data){
			list.add(dc.getData(ClientData.class));
		}
		
		// save
		this.getDataHandle().setData(list);
		return new DataContainer(list);
	}

}
