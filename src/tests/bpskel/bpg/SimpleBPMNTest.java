package tests.bpskel.bpg;

import org.junit.Test;

import bpskel.api.BPGFactory;
import bpskel.api.BusinessProcessGraph;
import bpskel.engine.simple.SimpleProcessEngine;
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
