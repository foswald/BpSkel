package tests.bpskel.shared.tasks;

import tests.bpskel.shared.UniversalContainer;
import bpskel.api.AbstractTask;
import bpskel.api.IDataContainer;

public class StaticPrintTask extends AbstractTask {

	String thisString = null;
	
	IDataContainer thisData;
	
	public StaticPrintTask(String s){
		thisString = s;
		this.setDescription(s);
	}
	
	@Override
	public void setDataHandle(IDataContainer in) {
		thisData = in;
	}

	@Override
	public IDataContainer getDataHandle() {
		return new UniversalContainer(thisString.length());
	}

	@Override
	public void execute() {
		System.out.println(thisString);
	}
}
