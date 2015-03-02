package tests.bpskel.shared;

import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.RandomizeTask;
import tests.bpskel.shared.tasks.StaticPrintTask;
import tests.bpskel.shared.tasks.WhileTruePrintTask;
import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.bpg.api.ICondition;
import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.gateway.GatewayJoin;
import bpskel.bpg.impl.gateway.GatewaySplit;

public class TestProcessFactory {

	public static BusinessProcessGraph generateProcess1(){
		ITask t1 = new StaticPrintTask("FirstAndTask");
		ITask t2 = new StaticPrintTask("SecondTask");	
		
		ITask t3 = new StaticPrintTask("ThirdOrTask");
		ITask t4 = new StaticPrintTask("FourthOrTask");
		
		ITask t5 = new StaticPrintTask("End");	
		
		ITask tr = new RandomizeTask();
		
		// create XorGateway and split condition for gateway
		GatewaySplit splitXor1 = BPGFactory.createGatewayXorSplit(" < ", tr.getResultData(), new UniversalContainer(5));
		GatewayJoin joinXor1 = BPGFactory.createGatewayXorJoin();
		
		// Create And Gateway
		GatewaySplit splitAnd1 = BPGFactory.createGatewayAndSplit();
		GatewayJoin joinAnd1 = BPGFactory.createGatewayAndJoin();
		
		// create BuisnessProcess
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
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
		pro.connect(joinAnd1, t5);
		pro.connect(t5, pro.getEnd());
		
		return pro;
	}
	
	public static BusinessProcessGraph generatePipeXorPipeBPG(){
		ITask t1 = new RandomizeTask();
		ITask t2 = new StaticPrintTask("Task2.1-Xor");			
		ITask t3 = new StaticPrintTask("Task2.2-Xor");
		ITask t4 = new StaticPrintTask("Task3-Pipe");
		
		GatewaySplit splitXor1 = BPGFactory.createGatewayXorSplit(" < ", t1.getResultData(), new UniversalContainer(5));
		GatewayJoin joinXor1 = BPGFactory.createGatewayXorJoin();
		
		// create BuisnessProcess
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
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
	
	public static BusinessProcessGraph generatePipeWhilePipeBPG(){
		ITask tr = new RandomizeTask(10000);
		
		ITask whileTask = new WhileTruePrintTask();
		IDataContainer start = new UniversalContainer(10);
		whileTask.setInputData(start);
		
		ITask inLoop1 = new StaticPrintTask("Repetition: ");
		ITask inLoop2 = new DataPrintTask();
		
		// inLoop2 recieves data from t2
		inLoop2.setInputData(whileTask.getResultData());
		
		ITask t3 = new StaticPrintTask("EndTask");
		
		

		ICondition cond = BPGFactory.createCondition(whileTask.getResultData(), " < ", tr.getResultData());
		
		// create BuisnessProcess
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
		// Add connectors
		pro.connect(pro.getStart(), tr);
		pro.connect(whileTask, inLoop1);
		pro.connect(inLoop1, inLoop2);
		
		pro.insertIntoWhileLoop(tr, t3, cond, whileTask, inLoop2);
		pro.connect(t3, pro.getEnd());
		
		return pro;
	}
}
