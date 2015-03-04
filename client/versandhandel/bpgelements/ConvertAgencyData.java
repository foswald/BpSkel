package bpgelements;

import impl.ClientData;
import bpskel.api.AbstractTask;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class ConvertAgencyData extends AbstractTask {
	
	public ConvertAgencyData(){
		super(true);
	}

	@Override
	public IDataContainer executeInline(IDataContainer param){
		String[] list = param.getData(String[].class);
		
		return new DataContainer(new ClientData(list));
	}

}
