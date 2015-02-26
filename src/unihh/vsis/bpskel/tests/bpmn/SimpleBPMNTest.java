package unihh.vsis.bpskel.tests.bpmn;

import org.junit.Test;

import unihh.vsis.bpskel.bpmn.api.BPMNFactory;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.core.SimpleProcessEngine;
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
