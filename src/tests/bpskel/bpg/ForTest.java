package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.TestProcessFactory;
import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.executor.skandium.SkandiumConnector;

public class ForTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
