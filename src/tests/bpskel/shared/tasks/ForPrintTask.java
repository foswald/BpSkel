package tests.bpskel.shared.tasks;

import java.util.concurrent.ExecutionException;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractForTask;

public class ForPrintTask extends AbstractForTask {
	
	

	public ForPrintTask(int numIterations) {
		super(numIterations);
		super.setDescription("For Task!");
	}

	@Override
	public void setInputData(IDataContainer in) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws ExecutionException {
		// TODO Auto-generated method stub

	}

	@Override
	public IDataContainer getResultData() {
		// TODO Auto-generated method stub
		return null;
	}

}
