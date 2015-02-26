package tests.bpskel.bpg;

import org.junit.Test;

import bpskel.api.BPGFactory;
import bpskel.api.BusinessProcessGraph;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.executor.skandium.SkandiumConnector;
import tests.bpskel.shared.TestProcessFactory;

public class SkeletonBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro = TestProcessFactory.generatePipeXorPipeBPG();
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
