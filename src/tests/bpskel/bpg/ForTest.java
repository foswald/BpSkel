package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.ForPrintTask;
import tests.bpskel.shared.tasks.StaticPrintTask;
import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.api.IForTask;
import bpskel.api.ITask;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;

public class ForTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine());
		
		IBPG pro = BPGFactory.createBPG();
		
		IForTask forTask = new ForPrintTask(12300);
		ITask t1 = new StaticPrintTask("Finished!");
		ITask t2 = new DataPrintTask();
		t2.setInputData(forTask.getResultData());
		
		pro.connect(pro.getStart(), forTask);
		pro.connect(forTask, t1);
		pro.connect(t1, pro.getEnd());
		
		BPGFactory.executeProcess(pro);
	}

}
