package tests.bpskel.bpg;

import org.junit.Test;

import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.bpg.impl.executor.simple.SimpleProcessEngine;
import tests.bpskel.shared.TestProcessFactory;

public class SimpleBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SimpleProcessEngine());
		
		//BusinessProcess pro = TestProcessFactory.generateProcess1();

		BusinessProcessGraph pro = TestProcessFactory.generatePipeXorPipeBPG();

		BPGFactory.getProcessEngine().execute(pro);
	}

}
