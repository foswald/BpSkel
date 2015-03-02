package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.TestProcessFactory;
import bpskel.api.BPGFactory;
import bpskel.api.BusinessProcessGraph;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;

public class PipeXorTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SkeletonProcessEngine());
		
		BusinessProcessGraph pro = TestProcessFactory.generatePipeXorPipeBPG();
		BPGFactory.executeProcess(pro);
	}

}
