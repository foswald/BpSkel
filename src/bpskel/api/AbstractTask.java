package bpskel.api;

import java.util.concurrent.ExecutionException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public abstract class AbstractTask extends AbstractPrivateTask{
	
	public AbstractTask(){
	}
	
	public AbstractTask(boolean executeInline){
		super(executeInline);
	}
	
	@Override
	public  IDataContainer executeInline(IDataContainer param) throws ExecutionException {
		throw new NotImplementedException();
	}
}
