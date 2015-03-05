package tasks;

import impl.ClientData;
import impl.Utils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractInlineTask;
import bpskel.api.DataContainer;
import bpskel.api.IDataContainer;

/**
 * each match gets pass to merge, otherwise null
 * @author foswald
 *
 */
public class CompareData extends AbstractInlineTask {

	@Override
	@SuppressWarnings("unchecked")
	public IDataContainer executeInline(IDataContainer param)
			throws ExecutionException {

		// data holds two lists, first containing agency data, second client data
		IDataContainer[] dcs = param.getData(IDataContainer[].class);
		
		ArrayList<ClientData> agencyData = dcs[0].getData(ArrayList.class);
		ArrayList<ClientData> clientData = dcs[1].getData(ArrayList.class);
		
		// should only have one entry
		ClientData d = agencyData.get(0);
		for(ClientData c:clientData){
			if(d.equals(c)){
				return new DataContainer(d);
			}
		}
		new Utils().doStuff();
		return new DataContainer(null);
	}
	
	

}
