package tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractInlineTask;
import bpskel.api.IDataContainer;

public class StartTimer extends AbstractInlineTask {

	private long start;
	
	public StartTimer(){
		this.setDescription("StartTimer");
	}
	
	@Override
	public void execute() throws ExecutionException {		
		// save current timestamp in out data
		setStart(System.currentTimeMillis());
	}

	@Override
	public IDataContainer executeInline(IDataContainer param) throws ExecutionException {
		setStart(System.currentTimeMillis());
		return param;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}
}
