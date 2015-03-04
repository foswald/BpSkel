package bpskel.api;

import java.util.concurrent.ExecutionException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class AbstractInlineTask extends AbstractPrivateTask{
	
	public AbstractInlineTask(){
		this.setExecuteInle(true);
	}
	
	
	@Override
	public void execute() throws ExecutionException {
		throw new NotImplementedException();
	}
}
