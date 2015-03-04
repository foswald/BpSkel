package tests.bpskel.shared.tasks;

import bpskel.api.AbstractTask;

public class WhileTruePrintTask extends AbstractTask {

	String thisString = null;
	
	public WhileTruePrintTask(){
		thisString = "While do. Repetition: ";
		this.setDescription(thisString);
	}

	@Override
	public void execute() {
		try{
			int rep = (int)this.getDataHandle().getData();
			System.out.println(thisString + rep);
			this.getDataHandle().setData(++rep, Integer.class);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
