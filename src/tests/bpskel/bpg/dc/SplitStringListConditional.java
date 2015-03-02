package tests.bpskel.bpg.dc;

import java.util.List;

import tests.bpskel.shared.DataContainer;
import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractFlowObject;
import bpskel.bpg.impl.core.IDataSplitConditional;

public class SplitStringListConditional extends AbstractFlowObject implements IDataSplitConditional{
	
	private int splitSize;
	public SplitStringListConditional(int maxListSize){
		this.splitSize = maxListSize;
	}
	
	@Override
	public IDataContainer[] splitData(IDataContainer data) {
		
		List<String> list = (List<String>) data.getData();
		
		
		IDataContainer[] splitData = new IDataContainer[2];
		splitData[0] = new DataContainer<List<String>>(list.subList(0, list.size()/2));
		splitData[1] = new DataContainer<List<String>>(list.subList(list.size()/2, list.size()));
		
		return splitData;
	}
	

	@Override
	public boolean evaluate(IDataContainer param) {
		List<String> list = (List<String>) param.getData();
		if(list.size() > this.splitSize){
			return true;
		}
		return false;
	}
}
