package tasks;

import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractInlineTask;
import bpskel.api.IDataContainer;

public class StopTimer extends AbstractInlineTask {

	private StartTimer startTimer;
	
	private long dur;
	
	public StopTimer(StartTimer startTimer){
		this.setDescription("StopTimer");
		this.startTimer = startTimer;
	}

	@Override
	public IDataContainer executeInline(IDataContainer param) throws ExecutionException {
		long dur = System.currentTimeMillis() - startTimer.getStart();
		setDur(dur);
		System.out.println(String.format("Duration: %s [ms]", getDur()));
		return param;
	}

	public long getDur() {
		return dur;
	}

	public void setDur(long dur) {
		this.dur = dur;
	}

}
