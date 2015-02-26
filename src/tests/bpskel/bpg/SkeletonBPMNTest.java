package tests.bpskel.bpg;

import org.junit.Test;

import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.skeleton.api.SkeletonProcessEngine;
import bpskel.skeleton.impl.executor.skandium.SkandiumConnector;
import tests.bpskel.shared.TestProcessFactory;

public class SkeletonBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro = TestProcessFactory.generatePipeXorPipeBPG();
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
