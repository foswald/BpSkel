package impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {
	public void doStuff(){
		// generate some load here
		int micros = 1;
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
	
	public static <T> List<T> removeSome(List<T> list, int percent){
		int max = (int) ((percent/100.0d)*list.size());
		Collections.shuffle(list);
		for(int i = list.size()-1; i >= max; i--){
			list.remove(i);
		}
		return list;
	}
}
