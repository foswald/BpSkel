package impl.tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractSimpleTask;

public class MockupTask extends AbstractSimpleTask {

	private String string;
	
	public MockupTask(String description){
		this.string = description;
	}
	
	@Override
	public void execute() throws ExecutionException {
		System.out.println(this.string);
	}

}
