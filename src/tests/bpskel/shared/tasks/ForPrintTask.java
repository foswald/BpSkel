package tests.bpskel.shared.tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractForTask;
import bpskel.api.IDataContainer;

public class ForPrintTask extends AbstractForTask {
	
	

	public ForPrintTask(int numIterations) {
		super(numIterations);
		super.setDescription("For Task!");
	}

	@Override
	public void execute() throws ExecutionException {
		System.out.println("Repeat this: " + ++this.numCurrentItartion);
	}

	@Override
	public IDataContainer executeInline(IDataContainer param)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doExecuteInline() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setExecuteInle(boolean b) {
		// TODO Auto-generated method stub
		
	}

}