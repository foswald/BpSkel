package tests.bpskel.bpg.simple;

import org.junit.Test;

import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.engine.simple.SimpleProcessEngine;
import tests.bpskel.shared.TestProcessFactory;

public class SimpleBPGTest {

	@Test
	public void testBusinessProcess() {
		BPGFactory.initialize(new SimpleProcessEngine());
		
		//BusinessProcess pro = TestProcessFactory.generateProcess1();

		IBPG pro = TestProcessFactory.generatePipeXorPipeBPG();

		BPGFactory.executeProcess(pro);
	}

}
