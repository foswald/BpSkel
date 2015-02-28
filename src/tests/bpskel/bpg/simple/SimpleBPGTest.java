package tests.bpskel.bpg.simple;

import org.junit.Test;

import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.engine.simple.SimpleProcessEngine;
import tests.bpskel.shared.TestProcessFactory;

public class SimpleBPGTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SimpleProcessEngine());
		
		//BusinessProcess pro = TestProcessFactory.generateProcess1();

		BusinessProcessGraph pro = TestProcessFactory.generatePipeXorPipeBPG();

		BPGFactory.getProcessEngine().execute(pro);
	}

}
