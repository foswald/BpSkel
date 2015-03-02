package tests.bpskel.bpg.map;

import java.util.ArrayList;

import tests.bpskel.shared.DataContainer;
import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractFlowObject;
import bpskel.bpg.impl.core.IDataSplit;

public class SplitStringList extends AbstractFlowObject implements IDataSplit{

	@Override
	public IDataContainer[] splitData(IDataContainer data) {
		ArrayList<String> list = (ArrayList<String>) data.getData();
		
		IDataContainer[] splitData = new IDataContainer[list.size()];
		for(int i=0; i < list.size(); i++){
			splitData[i] = new DataContainer<String>(list.get(i));
		}
		
		return splitData;
	}

}
