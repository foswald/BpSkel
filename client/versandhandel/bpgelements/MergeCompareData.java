package bpgelements;

import java.util.ArrayList;

import bpskel.api.AbstractDataMerge;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class MergeCompareData extends AbstractDataMerge {

	@Override
	public IDataContainer mergeData(IDataContainer[] data) {
		
		ArrayList<IDataContainer> temp = new ArrayList<IDataContainer>();
		// flatten data if nested arrays
		for(IDataContainer d:data){
			if(d.getData() instanceof IDataContainer[]){
				IDataContainer[] nestedArray = d.getData(IDataContainer[].class);
				for(IDataContainer dn:nestedArray){
					temp.add(dn);	
				}
			}
			else{
				temp.add(d);
			}
		}
		
		IDataContainer[] ret = temp.toArray(new IDataContainer[temp.size()]);

		// save
		this.getDataHandle().setData(ret);
		return new DataContainer(ret);
	}

}
