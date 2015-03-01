package tests.bpskel.skeleton;

import java.util.concurrent.Future;

import org.junit.Test;

import tests.bpskel.shared.UniversalContainer;
import bpskel.bpg.api.IDataContainer;
import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.skeletons.For;
import cl.niclabs.skandium.skeletons.Skeleton;

public class ForSkelTest {

	@Test
	public void test() {
		int THREADS  = Runtime.getRuntime().availableProcessors();
		int SIZE     = (int) Math.pow(2, 20);
		

		WhileExecute<IDataContainer,IDataContainer> exec= new WhileExecute<>(new UniversalContainer(1));
		
		Skeleton<IDataContainer, IDataContainer> f = new For<IDataContainer>(exec, 123);


		System.out.println("Computing Mergesort threads="+THREADS+" size="+ SIZE + ".");
		
		Skandium skandium = new Skandium(8);

		long init = System.currentTimeMillis();
		Future<IDataContainer> future = f.input(exec.getResultData());
		IDataContainer out;
		try {
			out = future.get();
			System.out.println((System.currentTimeMillis() - init)+"[ms]: "+(Integer)out.getData());

			skandium.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
