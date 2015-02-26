package tests.bpskel.bpg;

import org.junit.Test;

import bpskel.bpg.api.BPMNFactory;
import bpskel.bpg.api.BusinessProcess;
import bpskel.skeleton.api.SkeletonProcessEngine;
import tests.bpskel.shared.TestProcessFactory;

public class SkeletonBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPMNFactory.initialize(new SkeletonProcessEngine());
		
		BusinessProcess pro = TestProcessFactory.generatePipeXorPipeBPG();
		
		BPMNFactory.getProcessEngine().execute(pro);
	}

}
