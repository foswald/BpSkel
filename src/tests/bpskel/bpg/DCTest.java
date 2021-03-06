package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.bpg.dc.MergeStringListData;
import tests.bpskel.bpg.dc.SplitStringListConditional;
import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.GenerateStringList;
import tests.bpskel.shared.tasks.StaticPrintTask;
import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.api.ITask;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplit;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;

public class DCTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine());
		
		IBPG pro = BPGFactory.createBPG();
		
		ITask generateData = new GenerateStringList(400);
		IDataSplit split = new SplitStringListConditional(10);
		IDataMerge merge = new MergeStringListData();
		ITask printData = new DataPrintTask(true);
		ITask end = new StaticPrintTask("End");
		
		pro.connect(pro.getStart(), generateData);
		pro.connect(generateData, split);
		pro.connect(split, printData);
		pro.connect(printData, merge);
		pro.connect(merge, end);
		pro.connect(end, pro.getEnd());
		
		
		
		BPGFactory.executeProcess(pro);
	}

}
