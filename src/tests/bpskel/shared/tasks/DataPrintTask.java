package tests.bpskel.shared.tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractSimpleTask;

public class DataPrintTask extends AbstractSimpleTask {

	@Override
	public void execute() throws ExecutionException {
		System.out.println(this.getDataHandle().getData());
	}

}
