package tests.bpskel.shared;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;

public class WhileTruePrintTask extends AbstractTask {

	String thisString = null;
	
	IDataContainer thisData;
	
	public WhileTruePrintTask(){
		thisString = "While do. Repetition: ";
		this.setDescription(thisString);
	}
	
	@Override
	public void setInputData(IDataContainer in) {
		thisData = in;
	}

	@Override
	public IDataContainer getResultData() {
		return thisData;
	}

	@Override
	public void execute() {


		try{
			System.out.println(thisString + (int)thisData.getData());
			thisData.setData((int)thisData.getData() + 1);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
