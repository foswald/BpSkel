package tests.bpskel.bpg;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;

public class ToStringWhile extends AbstractTask {

	String thisString = null;
	
	IDataContainer thisData;
	
	public ToStringWhile(String s){
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


		try{
			System.out.println(thisString + "Repetition: " + (int)thisData.getData());
			thisData.setData((int)thisData.getData() + 1);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
