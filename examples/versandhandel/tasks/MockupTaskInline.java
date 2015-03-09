package tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractInlineTask;
import bpskel.api.IDataContainer;

public class MockupTaskInline extends AbstractInlineTask {

	private String string;
	
	public MockupTaskInline(String description){
		this.setDescription(description);
		this.string = description;
	}
	
	@Override
	public IDataContainer executeInline(IDataContainer param) throws ExecutionException {
		System.out.println(this.string);
		return param;
	}

}
