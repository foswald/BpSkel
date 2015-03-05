package impl;

import java.util.concurrent.TimeUnit;

public class Utils {
	public void doStuff(){
		// generate some load here
		int micros = 5;
	    long sleepTime = micros*100000L; // convert to nanoseconds
	    long startTime = System.nanoTime();
	    while ((System.nanoTime() - startTime) < sleepTime) {}
	    
	}
	
	public void doWait(){
		try {
			TimeUnit.MICROSECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
