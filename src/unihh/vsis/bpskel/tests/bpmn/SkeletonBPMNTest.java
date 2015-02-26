package unihh.vsis.bpskel.tests.bpmn;

import org.junit.Test;

import bpskel.bpg.api.BPMNFactory;
import bpskel.bpg.api.BusinessProcess;
import unihh.vsis.bpskel.api.skeleton.SkeletonProcessEngine;
import unihh.vsis.bpskel.tests.shared.TestProcessFactory;

public class SkeletonBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPMNFactory.initialize(new SkeletonProcessEngine());
		
		BusinessProcess pro = TestProcessFactory.generatePipeXorPipeBPG();
		
		BPMNFactory.getProcessEngine().execute(pro);
	}

}
