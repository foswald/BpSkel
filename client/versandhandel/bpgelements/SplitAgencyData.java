package bpgelements;

import java.util.ArrayList;

import bpskel.api.AbstractDataSplit;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class SplitAgencyData extends AbstractDataSplit {
	
	public SplitAgencyData(){
		this.setDescription("SplitAgencyData");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IDataContainer[] splitData(IDataContainer d) {
		// The data is an ArrayList containing String[] as entries
		ArrayList<String[]> list = new ArrayList<String[]>();
		list = this.getDataHandle().getData(list.getClass());		
		
		// split into numSplit packages
		IDataContainer[] dc = new DataContainer[list.size()];
		
		for(int i = dc.length-1; i >= 0; i--){
			dc[i] = new DataContainer(list.remove(i));
		}
		
		return dc;
	}

}
