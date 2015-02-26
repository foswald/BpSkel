package unihh.vsis.bpskel.tests.bpmn;

import org.junit.Test;

import bpskel.bpg.api.BPMNFactory;
import bpskel.bpg.api.BusinessProcess;
import bpskel.bpg.impl.SimpleProcessEngine;
import unihh.vsis.bpskel.tests.shared.TestProcessFactory;

public class SimpleBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPMNFactory.initialize(new SimpleProcessEngine());
		
		//BusinessProcess pro = TestProcessFactory.generateProcess1();

		BusinessProcess pro = TestProcessFactory.generatePipeXorPipeBPG();

		BPMNFactory.getProcessEngine().execute(pro);
	}

}
