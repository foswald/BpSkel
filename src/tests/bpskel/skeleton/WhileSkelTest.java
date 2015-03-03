package tests.bpskel.skeleton;

import java.util.concurrent.Future;

import org.junit.Test;

import bpskel.api.BPGFactory;
import bpskel.api.IDataContainer;
import tests.bpskel.shared.UniversalContainer;
import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.muscles.Condition;
import cl.niclabs.skandium.skeletons.Skeleton;
import cl.niclabs.skandium.skeletons.While;

public class WhileSkelTest {

	@Test
	public void test() {
		int THREADS  = Runtime.getRuntime().availableProcessors();
		int SIZE     = (int) Math.pow(2, 20);
		

		WhileExecute<IDataContainer,IDataContainer> exec= new WhileExecute<>(new UniversalContainer(1));
		
		Condition<IDataContainer> cond = new WhileCond(BPGFactory.createCondition(exec.getDataHandle(), "<", new UniversalContainer(20)));
		
		Skeleton<IDataContainer, IDataContainer> f = new While<IDataContainer>(exec, cond);


		System.out.println("Computing Mergesort threads="+THREADS+" size="+ SIZE + ".");
		
		Skandium skandium = new Skandium(8);

		long init = System.currentTimeMillis();
		Future<IDataContainer> future = f.input(exec.getDataHandle());
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
