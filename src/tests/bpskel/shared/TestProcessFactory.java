package tests.bpskel.shared;

import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.RandomizeTask;
import tests.bpskel.shared.tasks.StaticPrintTask;
import tests.bpskel.shared.tasks.WhileTruePrintTask;
import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.api.ICondition;
import bpskel.api.IDataContainer;
import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.api.ITask;

public class TestProcessFactory {

	public static IBPG generateProcess1(){
		ITask t1 = new StaticPrintTask("FirstAndTask");
		ITask t2 = new StaticPrintTask("SecondTask");	
		
		ITask t3 = new StaticPrintTask("ThirdOrTask");
		ITask t4 = new StaticPrintTask("FourthOrTask");
		
		ITask t5 = new StaticPrintTask("End");	
		
		ITask tr = new RandomizeTask();
		
		// create XorGateway and split condition for gateway
		IGatewaySplit splitXor1 = BPGFactory.createGatewayXorSplit(" < ", tr.getDataHandle(), new UniversalContainer(5));
		IGatewayJoin joinXor1 = BPGFactory.createGatewayXorJoin();
		
		// Create And Gateway
		IGatewaySplit splitAnd1 = BPGFactory.createGatewayAndSplit();
		IGatewayJoin joinAnd1 = BPGFactory.createGatewayAndJoin();
		
		// create BuisnessProcess
		IBPG pro = BPGFactory.createBPG();
		
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
	
	public static IBPG generatePipeXorPipeBPG(){
		ITask t1 = new RandomizeTask();
		ITask t2 = new StaticPrintTask("Task2.1-Xor");			
		ITask t3 = new StaticPrintTask("Task2.2-Xor");
		ITask t4 = new StaticPrintTask("Task3-Pipe");
		
		IGatewaySplit splitXor1 = BPGFactory.createGatewayXorSplit(" < ", t1.getDataHandle(), new UniversalContainer(5));
		IGatewayJoin joinXor1 = BPGFactory.createGatewayXorJoin();
		
		// create BuisnessProcess
		IBPG pro = BPGFactory.createBPG();
		
		// Add connectors
		pro.connect(pro.getStart(), t1);
		pro.connect(t1, splitXor1);
		pro.connectFromSplit(splitXor1, t2, t3);
		pro.connectToJoin(t2, t3, joinXor1);
		pro.connect(joinXor1, t4);
		pro.connect(t4, pro.getEnd());
		
		return pro;
	
	}
	
	public static IBPG generatePipeWhilePipeBPG(){
		ITask tr = new RandomizeTask(10000);
		
		ITask whileTask = new WhileTruePrintTask();
		IDataContainer start = new UniversalContainer(10);
		whileTask.setDataHandle(start);
		
		ITask inLoop1 = new StaticPrintTask("Repetition: ");
		ITask inLoop2 = new DataPrintTask();
		
		// inLoop2 recieves data from t2
		inLoop2.setDataHandle(whileTask.getDataHandle());
		
		ITask t3 = new StaticPrintTask("EndTask");
		
		

		ICondition cond = BPGFactory.createCondition(whileTask.getDataHandle(), " < ", tr.getDataHandle());
		
		// create BuisnessProcess
		IBPG pro = BPGFactory.createBPG();
		
		// Add connectors
		pro.connect(pro.getStart(), tr);
		pro.connect(whileTask, inLoop1);
		pro.connect(inLoop1, inLoop2);
		
		pro.insertIntoWhileLoop(tr, t3, cond, whileTask, inLoop2);
		pro.connect(t3, pro.getEnd());
		
		return pro;
	}
}
