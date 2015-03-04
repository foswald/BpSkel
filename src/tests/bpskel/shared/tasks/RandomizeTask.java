package tests.bpskel.shared.tasks;

import java.util.Random;

import tests.bpskel.shared.UniversalContainer;
import bpskel.api.AbstractTask;

public class RandomizeTask extends AbstractTask {


	private int max;
	public RandomizeTask(int max){
		super.getDataHandle().setData(new UniversalContainer(1));
		super.setDescription("Randomize!");
		this.max = max;
	}
	
	public RandomizeTask(){
		super.getDataHandle().setData(new UniversalContainer(1));
		super.setDescription("Randomize!");
		this.max = 10;
	}

	@Override
	public void execute() {
		Random r  = new Random();
		
		int rand = r.nextInt(max);
		super.setDescription("Randomize!(" + (rand) + ")");
		super.getDataHandle().setData(rand, Integer.class);
	}

}
