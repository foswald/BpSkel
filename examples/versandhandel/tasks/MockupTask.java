package tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;

public class MockupTask extends AbstractTask {

	private String string;
	
	public MockupTask(String description){
		this.setDescription(description);
		this.string = description;
	}
	
	@Override
	public void execute() throws ExecutionException {
		System.out.println(this.string);		
	}

}
