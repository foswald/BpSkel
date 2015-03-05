package tasks;

import impl.ClientData;
import impl.Utils;
import bpskel.api.AbstractInlineTask;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

public class ConvertAgencyData extends AbstractInlineTask {
	
	@Override
	public IDataContainer executeInline(IDataContainer param){
		String[] list = param.getData(String[].class);
		
		new Utils().doStuff();
		return new DataContainer(new ClientData(list));
	}

}
