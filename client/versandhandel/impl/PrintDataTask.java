package impl;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;

public class PrintDataTask extends AbstractTask {

	@Override
	public void execute() throws ExecutionException {
		@SuppressWarnings("unchecked")
		ArrayList<ClientData> list = this.getDataHandle().getData(ArrayList.class);
		
		for(ClientData c:list){
			System.out.println(c.toString());
		}
		System.out.println("Finished!");
	}

}
