package tests.bpskel.shared;

import java.util.Random;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;

public class RandomizeTask extends AbstractTask {

	IDataContainer data;
	public RandomizeTask(){
		data = new UniversalContainer(1);
		super.setDescription("Randomize!");
		
	}
	
	@Override
	public void setInputData(IDataContainer in) {
		this.data = in;
	}

	@Override
	public void execute() {
		Random r  = new Random();
		
		int rand = r.nextInt(10);
		super.setDescription("Randomize!(" + (rand) + ")");
		data.setData(rand);
	}

	@Override
	public IDataContainer getResultData() {
		return data;
	}

}
