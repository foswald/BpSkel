package tasks;

import impl.CSVReader;
import impl.ClientData;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import bpskel.api.AbstractTask;

public class ReadClientData extends AbstractTask {


	private ArrayList<String> files;
	
	public ReadClientData(String csvFile){
		this.files = new ArrayList<String>();
		this.files.add(csvFile);
	}
	
	public ReadClientData(ArrayList<String> csvFiles){
		this.files = csvFiles;
	}
	
	@Override
	public void execute() throws ExecutionException {
		
		ArrayList<ClientData> data = new ArrayList<>();
		
		for(String f:this.files){
			for(String[] entry:CSVReader.readCSV(f, ";", true)){

				data.add(new ClientData(entry));
			}
		}

		this.getDataHandle().setData(data);
		System.out.println("Kundendaten eingelesen");
	}

}
