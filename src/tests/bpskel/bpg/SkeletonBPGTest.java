package tests.bpskel.bpg;

import org.junit.Test;

import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.executor.skandium.SkandiumConnector;
import tests.bpskel.shared.TestProcessFactory;

public class SkeletonBPGTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro = TestProcessFactory.generatePipeXorPipeBPG();
//		BusinessProcessGraph pro = TestProcessFactory.generatePipeWhilePipeBPG();
		BPGFactory.getProcessEngine().execute(pro);
	}

}
