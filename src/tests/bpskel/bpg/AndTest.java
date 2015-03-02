package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.TestProcessFactory;
import tests.bpskel.shared.tasks.StaticPrintTask;
import bpskel.api.BPGFactory;
import bpskel.api.BusinessProcessGraph;
import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.api.ITask;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.skandium.SkandiumConnector;

public class AndTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro2 = new BusinessProcessGraph();
		ITask t1 = new StaticPrintTask("FirstAndTask");
		ITask t2 = new StaticPrintTask("SecondTask");
		ITask t3 = new StaticPrintTask("EndTask");
		
		// Create And Gateway
		IGatewaySplit splitAnd1 = BPGFactory.createGatewayAndSplit();
		IGatewayJoin joinAnd1 = BPGFactory.createGatewayAndJoin();
		
		// Add connectors
		pro2.connect(pro2.getStart(), splitAnd1);
		pro2.connectFromSplit(splitAnd1, t1, t2);	
		pro2.connectToJoin(t1,t2, joinAnd1);
		pro2.connect(joinAnd1, t3);
		pro2.connect(t3, pro2.getEnd());
		
		BPGFactory.executeProcess(pro2);
		

		BusinessProcessGraph pro = TestProcessFactory.generateProcess1();
		BPGFactory.executeProcess(pro);
	}

}
