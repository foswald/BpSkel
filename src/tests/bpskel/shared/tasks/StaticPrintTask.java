package tests.bpskel.shared.tasks;

import tests.bpskel.shared.UniversalContainer;
import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractSimpleTask;

public class StaticPrintTask extends AbstractSimpleTask {

	String thisString = null;
	
	IDataContainer thisData;
	
	public StaticPrintTask(String s){
		thisString = s;
		this.setDescription(s);
	}
	
	@Override
	public void setInputData(IDataContainer in) {
		thisData = in;
	}

	@Override
	public IDataContainer getResultData() {
		return new UniversalContainer(thisString.length());
	}

	@Override
	public void execute() {
		System.out.println(thisString);
	}
}
