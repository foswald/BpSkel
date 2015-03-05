package impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	
	/**
	 * Parses each line of a psv file into a String Array and puts it into the returning ArrayList.
	 * @param csvFile the path to the csv file
	 * @return the ArrayList containing rows as entries
	 */
	public static ArrayList<String[]> readCSV(String csvFile, String seperator, boolean skipFirstLine){
		ArrayList<String[]> retList = new ArrayList<>();
		BufferedReader br = null;
	 
		try {

			br = new BufferedReader(new FileReader(csvFile));

			String line = ""; 
			if(skipFirstLine){//first line = header
				br.readLine();
				
			}			
			
			while ((line = br.readLine()) != null) {	 
			        // use comma as separator
				retList.add(line.split(seperator));			
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		return retList;
	  
	}

}
