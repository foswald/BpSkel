package tests.bpskel.bpg;

import org.junit.Test;

import tests.bpskel.bpg.map.MergeStringList;
import tests.bpskel.bpg.map.SplitStringList;
import tests.bpskel.shared.tasks.DataPrintTask;
import tests.bpskel.shared.tasks.GenerateStringList;
import tests.bpskel.shared.tasks.StaticPrintTask;
import bpskel.api.BPGFactory;
import bpskel.api.BusinessProcessGraph;
import bpskel.api.ITask;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplit;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;

public class MapTest {

	@Test
	public void test() {
		BPGFactory.initialize(new SkeletonProcessEngine());
		
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
		
		
		
		BPGFactory.executeProcess(pro);
	}

}
