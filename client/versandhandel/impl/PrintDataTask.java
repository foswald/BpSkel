package impl;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractSimpleTask;

public class PrintDataTask extends AbstractSimpleTask {

	@Override
	public void execute() throws ExecutionException {
		@SuppressWarnings("unchecked")
		ArrayList<ClientData> list = this.data.getData(ArrayList.class);
		
		for(ClientData c:list){
			System.out.println(c.toString());
		}
	}

}
