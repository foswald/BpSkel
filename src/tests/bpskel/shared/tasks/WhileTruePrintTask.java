package tests.bpskel.shared.tasks;

import bpskel.bpg.impl.core.AbstractTask;

public class WhileTruePrintTask extends AbstractTask {

	String thisString = null;
	
	public WhileTruePrintTask(){
		thisString = "While do. Repetition: ";
		this.setDescription(thisString);
	}

	@Override
	public void execute() {


		try{
			int rep = (int)this.getResultData().getData();
			System.out.println(thisString + rep);
			this.getResultData().setData(++rep);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
