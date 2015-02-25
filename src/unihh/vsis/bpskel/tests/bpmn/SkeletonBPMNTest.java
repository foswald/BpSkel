package unihh.vsis.bpskel.tests.bpmn;

import org.junit.Test;

import unihh.vsis.bpskel.api.skeleton.SkeletonProcessEngine;
import unihh.vsis.bpskel.bpmn.api.BPMNFactory;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.tests.shared.TestProcessFactory;

public class SkeletonBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPMNFactory.initialize(new SkeletonProcessEngine());
		
		BusinessProcess pro = TestProcessFactory.generateProcess1();
		
		BPMNFactory.getProcessEngine().execute(pro);
	}

}
