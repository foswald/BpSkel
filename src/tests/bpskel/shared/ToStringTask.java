package tests.bpskel.shared;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;

public class ToStringTask extends AbstractTask {

	String thisString = null;
	
	IDataContainer thisData;
	
	public ToStringTask(String s){
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
