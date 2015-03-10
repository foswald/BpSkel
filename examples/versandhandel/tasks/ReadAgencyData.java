package tasks;

import impl.CSVReader;
import impl.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;

public class ReadAgencyData extends AbstractTask {

	private ArrayList<String> files;
	
	public ReadAgencyData(String csvFile){
		this.files = new ArrayList<String>();
		this.files.add(csvFile);
	}
	
	public ReadAgencyData(ArrayList<String> csvFiles){
		this.files = csvFiles;
	}
	
	@Override
	public void execute() throws ExecutionException {
		
		List<String[]> csvData = new ArrayList<>();
		
		for(String f:this.files){
			csvData.addAll(CSVReader.readCSV(f, ";", true));
		}
		csvData = Utils.removeSome(csvData, 60);
		this.getDataHandle().setData(csvData, csvData.getClass());
	}

}
