package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.ForPrintTask;
import tests.bpskel.shared.tasks.StaticPrintTask;
import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.core.IForTask;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.executor.skandium.SkandiumConnector;

public class ForTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
		IForTask forTask = new ForPrintTask(12300);
		ITask t1 = new StaticPrintTask("Finished!");
		ITask t2 = new DataPrintTask();
		t2.setInputData(forTask.getResultData());
		
		pro.connect(pro.getStart(), forTask);
		pro.connect(forTask, t1);
		pro.connect(t1, pro.getEnd());
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
