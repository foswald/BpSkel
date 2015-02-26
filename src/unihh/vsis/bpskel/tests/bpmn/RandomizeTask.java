package unihh.vsis.bpskel.tests.bpmn;

import java.util.Random;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;

public class RandomizeTask extends AbstractTask {

	IDataContainer data;
	public RandomizeTask(){
		data = new DataContainer(1);
		super.setDescription("Randomize!");
		
	}
	
	@Override
	public void setInputData(IDataContainer in) {
		this.data = in;
	}

	@Override
	public void execute() {
		Random r  = new Random();
		
		data.setData(r.nextInt(10));
	}

	@Override
	public IDataContainer getResultData() {
		return data;
	}

}
