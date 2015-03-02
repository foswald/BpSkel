package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.shared.MergeStringList;
import tests.bpskel.shared.SplitStringList;
import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.GenerateStringList;
import tests.bpskel.shared.tasks.StaticPrintTask;
import bpskel.bpg.api.BPGFactory;
import bpskel.bpg.api.BusinessProcessGraph;
import bpskel.bpg.api.ITask;
import bpskel.bpg.impl.core.IDataMerge;
import bpskel.bpg.impl.core.IDataSplit;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;
import bpskel.engine.skeleton.impl.executor.skandium.SkandiumConnector;

public class MapTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine(SkandiumConnector.class));
		
		BusinessProcessGraph pro = new BusinessProcessGraph();
		
		
		ITask generateData = new GenerateStringList(100);
		IDataSplit split = new SplitStringList();
		IDataMerge merge = new MergeStringList();
		ITask printData = new DataPrintTask();
		ITask end = new StaticPrintTask("End");
		
		pro.connect(pro.getStart(), generateData);
		pro.connect(generateData, split);
		pro.connect(split, printData);
		pro.connect(printData, merge);
		pro.connect(merge, end);
		pro.connect(end, pro.getEnd());
		
		
		
		BPGFactory.getProcessEngine().execute(pro);
	}

}
