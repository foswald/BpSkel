package impl.tasks;

import impl.CSVReader;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractSimpleTask;
import bpskel.api.DataContainer;

public class ReadCSVTask extends AbstractSimpleTask {

	private String[] files;
	
	public ReadCSVTask(String csvFile){
		this.files = new String[1];
		this.files[0] = csvFile;
	}
	
	public ReadCSVTask(String[] csvFiles){
		this.files = csvFiles;
	}
	
	@Override
	public void execute() throws ExecutionException {
		
		ArrayList<String[]> csvData = new ArrayList<>();
		
		for(String f:this.files){
			csvData.addAll(CSVReader.readCSV(f, ";"));
		}
		
		this.setInputData(new DataContainer(csvData));
	}

}
