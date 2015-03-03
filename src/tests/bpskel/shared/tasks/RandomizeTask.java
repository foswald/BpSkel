package tests.bpskel.shared.tasks;

import java.util.Random;

import tests.bpskel.shared.UniversalContainer;
import bpskel.api.AbstractSimpleTask;

public class RandomizeTask extends AbstractSimpleTask {


	private int max;
	public RandomizeTask(int max){
		super.setDataHandle(new UniversalContainer(1));
		super.setDescription("Randomize!");
		this.max = max;
	}
	
	public RandomizeTask(){
		super.setDataHandle(new UniversalContainer(1));
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
