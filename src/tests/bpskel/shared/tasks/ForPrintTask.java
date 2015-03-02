package tests.bpskel.shared.tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractForTask;

public class ForPrintTask extends AbstractForTask {
	
	

	public ForPrintTask(int numIterations) {
		super(numIterations);
		super.setDescription("For Task!");
	}

	@Override
	public void execute() throws ExecutionException {
		System.out.println("Repeat this: " + ++this.numCurrentItartion);
	}

}