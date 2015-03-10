package tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;
import bpskel.api.IDataContainer;

public class PrintDataTask extends AbstractTask {

	@Override
	public void execute() throws ExecutionException {
		IDataContainer[] list = this.getDataHandle().getData(IDataContainer[].class);
		
		int counter = 0;
		// count for actual hits
		for(IDataContainer d:list){
			if(d.getData() != null){
				++counter;
			}
		}

		System.out.println(String.format("%s treffer gefunden.", counter));
	}

}
