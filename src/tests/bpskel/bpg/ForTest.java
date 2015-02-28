package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.tasks.ForPrintTask;
import tests.bpskel.shared.tasks.ToStringTask;
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
		
		IForTask t = new ForPrintTask(12300);
		ITask t2 = new ToStringTask("Finished!");
		pro.connect(pro.getStart(), t);
		pro.connect(t, t2);
		pro.connect(t2, pro.getEnd());
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
