package bpgelements;

import impl.ClientData;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractSimpleTask;
import bpskel.api.DataContainer;

public class ConvertAgencyData extends AbstractSimpleTask {

	@Override
	public void execute() throws ExecutionException {
		
		String[] list = this.getDataHandle().getData(String[].class);
		
		this.setDataHandle(new DataContainer(new ClientData(list)));
	}

}
