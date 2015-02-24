package unihh.vsis.bpskel.tests.bpmn;

import org.junit.Test;

import unihh.vsis.bpskel.api.skeleton.SkeletonProcessEngine;
import unihh.vsis.bpskel.bpmn.api.BPMNFactory;
import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.core.IGateway;

public class SkeletonBPMNTest {

	@Test
	public void testBusinessProcess() {
		BPMNFactory.initialize(new SkeletonProcessEngine());
		
		ITask t1 = new ToStringTask("FirstTask");
		ITask t2 = new ToStringTask("SecondTask");	
		
		ITask t3 = new ToStringTask("ThirdOrTask");
		ITask t4 = new ToStringTask("FourthOrTask");	
		
		ITask tr = new RandomizeTask();
		
		// create XorGateway and split condition for gateway
		IGateway splitXor1 = BPMNFactory.createGatewayXorSplit(" < ", tr.getResultData(), new DataContainer(5));
		IGateway joinXor1 = BPMNFactory.createGatewayXorJoin();
		
		// Create And Gateway
		IGateway splitAnd1 = BPMNFactory.createGatewayAndSplit();
		IGateway joinAnd1 = BPMNFactory.createGatewayAndJoin();
		
		// create BuisnessProcess
		BusinessProcess pro = new BusinessProcess();
		
		// add elements to process
		pro.addTask(tr);		
		pro.addTask(t1);
		pro.addTask(t2);
		pro.addTask(t3);
		pro.addTask(t4);
		pro.addGateway(splitAnd1);
		pro.addGateway(joinAnd1);
		pro.addGateway(splitXor1);
		pro.addGateway(joinXor1);
		
		// Add connectors
		pro.connect(pro.getStart(), tr);
		pro.connect(tr, splitAnd1);
		pro.connectFromSplit(splitAnd1, splitXor1, t1);
		pro.connectFromSplit(splitXor1, t4, t3);
		pro.connectToJoin(t3, t4, joinXor1);		
		pro.connect(t1, t2);
		pro.connectToJoin(t2, joinXor1, joinAnd1);		
		pro.connect(joinAnd1, pro.getEnd());
		
		BPMNFactory.getProcessEngine().execute(pro);
	}

}
