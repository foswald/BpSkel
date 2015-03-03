package tests.bpskel.bpg.map;

import java.util.ArrayList;

import bpskel.api.AbstractDataSplit;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class SplitStringList extends AbstractDataSplit{

	@Override
	public IDataContainer[] splitData(IDataContainer data) {
		ArrayList<String> list = (ArrayList<String>) data.getData();
		
		IDataContainer[] splitData = new IDataContainer[list.size()];
		for(int i=0; i < list.size(); i++){
			splitData[i] = new DataContainer(list.get(i));
		}
		
		return splitData;
	}

}
