package bpgelements;

import impl.ClientData;

import java.util.ArrayList;

import bpskel.api.AbstractDataSplitConditional;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class SplitCompareData extends AbstractDataSplitConditional {

	
	
	public SplitCompareData(){
		
	}
	
	@Override
	public IDataContainer[] splitData(IDataContainer data) {
		// data holds two list, first containing agency data, second client data
		IDataContainer[] dcs = data.getData(IDataContainer[].class);
		
		ArrayList<ClientData> agencyData = extractData(dcs[0]);
		IDataContainer clientData = dcs[1];
		
		IDataContainer split1 = new DataContainer(new ArrayList<ClientData>(agencyData.subList(0, agencyData.size()/2)));
		IDataContainer split2 = new DataContainer(new ArrayList<ClientData>(agencyData.subList(agencyData.size()/2, agencyData.size())));
		
		IDataContainer[] arr1 = {split1, clientData};
		IDataContainer[] arr2 = {split2, clientData};
		IDataContainer ret1 = new DataContainer(arr1);
		IDataContainer ret2 = new DataContainer(arr2);
		
		IDataContainer[] ret = {ret1, ret2};
		 
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	ArrayList<ClientData> extractData(IDataContainer data){
		return data.getData(ArrayList.class);
	}
	

	@Override
	public boolean evaluate(IDataContainer param) {
		IDataContainer[] dcs = param.getData(IDataContainer[].class);
		
		ArrayList<ClientData> agencyData = extractData(dcs[0]);	
		
		boolean ret = false;
		ret = agencyData.size() > 1;
		
		return ret;
	}

}
