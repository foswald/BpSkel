package bpskel.api;

import java.util.concurrent.ExecutionException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public abstract class AbstractTask extends AbstractDataHandle implements ITask{

	private boolean executeInline;
	
	public AbstractTask(){
		this.executeInline = false;
	}
	
	public AbstractTask(boolean executeInline){
		this.executeInline = executeInline;
	}
	
	@Override
	public void execute() throws ExecutionException {
		throw new NotImplementedException();
	}
	
	@Override
	public  IDataContainer executeInline(IDataContainer param) throws ExecutionException {
		throw new NotImplementedException();
	}
	
	@Override
	public boolean doExecuteInline(){
		return this.executeInline;
	}
	
	@Override
	public void setExecuteInle(boolean executeInline){
		this.executeInline = executeInline;
	}
}
