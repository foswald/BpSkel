package tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;
import bpskel.api.IDataContainer;

public class PrintDataTask extends AbstractTask {

	@Override
	public void execute() throws ExecutionException {
		IDataContainer[] list = this.getDataHandle().getData(IDataContainer[].class);

		System.out.println(String.format("%s treffer gefunden.", list.length));
	}

}
