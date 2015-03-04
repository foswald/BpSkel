package tests.bpskel.shared.tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;
import bpskel.api.IDataContainer;

public class DataPrintTask extends AbstractTask {
	
	public DataPrintTask(){
		
	}
	
	public DataPrintTask(boolean inline){
		super(inline);
	}

	@Override
	public void execute() throws ExecutionException {
		System.out.println(this.getDataHandle().getData());
	}

	@Override
	public IDataContainer executeInline(IDataContainer param){
		System.out.println(param.getData());
		return param;
	}
}
