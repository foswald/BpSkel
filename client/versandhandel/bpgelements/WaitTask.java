package bpgelements;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import bpskel.api.AbstractTask;

public class WaitTask extends AbstractTask {
	
	private int dur;
	public WaitTask(int dur){
		this.dur = dur;
	}

	@Override
	public void execute() throws ExecutionException {
		
		for(int seconds = this.dur; seconds > 0 ;seconds--){
			System.out.print("Waiting " + seconds + "...");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
