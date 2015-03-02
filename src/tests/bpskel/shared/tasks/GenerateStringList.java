package tests.bpskel.shared.tasks;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import tests.bpskel.shared.DataContainer;
import bpskel.api.AbstractSimpleTask;

public class GenerateStringList extends AbstractSimpleTask {

	private int length;
	public GenerateStringList(int listLength){
		super.setDescription("GenerateStringList");
		this.length = listLength;
	}
	@Override
	public void execute() throws ExecutionException {
		
		System.out.println("Generating string list...");
		ArrayList<String> list = new ArrayList<>();
		
		for(int i=0; i<this.length; i++){
			list.add("String entry: " + i);
		}
		
		this.data = new DataContainer<ArrayList<String>>(list);
	}

}
