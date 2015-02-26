package tests.bpskel.shared;

import java.util.Random;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;

public class RandomizeTask extends AbstractTask {

	IDataContainer data;
	private int max;
	public RandomizeTask(int max){
		data = new UniversalContainer(1);
		super.setDescription("Randomize!");
		this.max = max;
	}
	
	public RandomizeTask(){
		data = new UniversalContainer(1);
		super.setDescription("Randomize!");
		this.max = 10;
	}
	
	@Override
	public void setInputData(IDataContainer in) {
		this.data = in;
	}

	@Override
	public void execute() {
		Random r  = new Random();
		
		int rand = r.nextInt(max);
		super.setDescription("Randomize!(" + (rand) + ")");
		data.setData(rand);
	}

	@Override
	public IDataContainer getResultData() {
		return data;
	}

}
