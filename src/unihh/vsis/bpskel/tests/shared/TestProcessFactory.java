package unihh.vsis.bpskel.tests.shared;

import unihh.vsis.bpskel.tests.bpmn.DataContainer;
import unihh.vsis.bpskel.tests.bpmn.RandomizeTask;
import unihh.vsis.bpskel.tests.bpmn.ToStringTask;
import bpskel.bpg.api.BPMNFactory;
import bpskel.bpg.api.BusinessProcess;
import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.gateway.GatewayJoin;
import bpskel.bpg.impl.gateway.GatewaySplit;

public class TestProcessFactory {

	public static BusinessProcess generateProcess1(){
		ITask t1 = new ToStringTask("FirstTask");
		ITask t2 = new ToStringTask("SecondTask");	
		
		ITask t3 = new ToStringTask("ThirdOrTask");
		ITask t4 = new ToStringTask("FourthOrTask");	
		
		ITask tr = new RandomizeTask();
		
		// create XorGateway and split condition for gateway
		GatewaySplit splitXor1 = BPMNFactory.createGatewayXorSplit(" < ", tr.getResultData(), new DataContainer(5));
		GatewayJoin joinXor1 = BPMNFactory.createGatewayXorJoin();
		
		// Create And Gateway
		GatewaySplit splitAnd1 = BPMNFactory.createGatewayAndSplit();
		GatewayJoin joinAnd1 = BPMNFactory.createGatewayAndJoin();
		
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
		
		return pro;
	}
	
	public static BusinessProcess generatePipeXorPipeBPG(){
		ITask t1 = new RandomizeTask();
		ITask t2 = new ToStringTask("Task2.1-Xor");			
		ITask t3 = new ToStringTask("Task2.2-Xor");
		ITask t4 = new ToStringTask("Task3-Pipe");
		
		GatewaySplit splitXor1 = BPMNFactory.createGatewayXorSplit(" < ", t1.getResultData(), new DataContainer(5));
		GatewayJoin joinXor1 = BPMNFactory.createGatewayXorJoin();
		
		// create BuisnessProcess
		BusinessProcess pro = new BusinessProcess();
		
		// add elements to process	
		pro.addTask(t1);
		pro.addTask(t2);
		pro.addTask(t3);
		pro.addTask(t4);
		pro.addGateway(splitXor1);
		pro.addGateway(joinXor1);
		
		// Add connectors
		pro.connect(pro.getStart(), t1);
		pro.connect(t1, splitXor1);
		pro.connectFromSplit(splitXor1, t2, t3);
		pro.connectToJoin(t2, t3, joinXor1);
		pro.connect(joinXor1, t4);
		pro.connect(t4, pro.getEnd());
		
		return pro;
	
	}
}
