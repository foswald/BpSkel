package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.TestProcessFactory;
import tests.bpskel.shared.UniversalContainer;
import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.RandomizeTask;
import tests.bpskel.shared.tasks.StaticPrintTask;
import tests.bpskel.shared.tasks.WhileTruePrintTask;
import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.bpg.api.ICondition;
import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.api.ITask;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.executor.skandium.SkandiumConnector;

public class WhileTest1 {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		ITask randomizeOutputData = new RandomizeTask(10000);
		
		ITask whileTask = new WhileTruePrintTask();
		IDataContainer start = new UniversalContainer(10);
		whileTask.setInputData(start);
		
		ITask inLoop1 = new StaticPrintTask("Repetition: ");
		ITask inLoop2 = new DataPrintTask();
		
		// inLoop2 recieves data from t2
		inLoop2.setInputData(whileTask.getResultData());
		
		ITask t3 = new StaticPrintTask("EndTask");
		
		

		ICondition cond = BPGFactory.createCondition(whileTask.getResultData(), " < ", randomizeOutputData.getResultData());
		
		// create BuisnessProcess
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
		// Add connectors
		pro.connect(pro.getStart(), randomizeOutputData);
		pro.connect(whileTask, inLoop1);
		pro.connect(inLoop1, inLoop2);
		
		pro.insertIntoWhileLoop(randomizeOutputData, t3, cond, whileTask, inLoop2);
		pro.connect(t3, pro.getEnd());
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
