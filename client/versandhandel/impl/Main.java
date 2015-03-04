package impl;

import java.io.File;
import java.util.ArrayList;

import tasks.CompareData;
import tasks.ConvertAgencyData;
import tasks.MockupTask;
import tasks.PrintDataTask;
import tasks.ReadAgencyData;
import tasks.ReadClientData;
import tasks.WaitTask;
import bpgelements.MergeCompareData;
import bpgelements.MergeConvertedData;
import bpgelements.SplitAgencyData;
import bpgelements.SplitCompareData;
import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.api.ITask;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplit;
import bpskel.bpg.elements.splitmerge.IDataSplitConditional;

public class Main {

	public static void main(String[] args) {
		File folder = new File("C:\\Users\\ferdinand\\workspace\\Master\\BpSkel\\client\\resources\\");
		File[] listOfFiles = folder.listFiles();

		ArrayList<String> csvFiles = new ArrayList<>();
	    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".csv")) {
		    	  csvFiles.add(listOfFiles[i].getAbsolutePath());
		      }
	    }
		    		
		BPGFactory.initialize(BPGFactory.getDefaultProcessEngine());
		
		ITask zielGruppe = new MockupTask("Zielgruppe definieren");
		
		IGatewaySplit split1 = BPGFactory.createGatewayAndSplit();
		ITask haushalteErmitteln = new ReadAgencyData(csvFiles);
		ITask prospekteAnfertigen = new MockupTask("Prospekte anfertigen");
		IGatewayJoin join1 = BPGFactory.createGatewayAndJoin();
		
		ITask prospekteVerschicken = new MockupTask("Prospekte verschicken");
		ITask adressdatenÜbermitteln = new MockupTask("Adressdaten übermitteln");
		ITask warten = new WaitTask(0);
		
		IGatewaySplit split2 = BPGFactory.createGatewayAndSplit();
		ITask kundenAdressen = new ReadClientData(csvFiles);		
		
		IDataSplit datasplit = new SplitAgencyData();
		ITask adressDatenKonvertieren = new ConvertAgencyData();
		IDataMerge datamerge = new MergeConvertedData();
		
		
		IGatewayJoin join2 = BPGFactory.createGatewayAndJoin();
		
		
		// Create DC
		IDataSplit datasplit2 = new SplitCompareData();
		ITask compareData = new CompareData();
		IDataMerge datamerge2 = new MergeCompareData();
		
		
		ITask printData = new PrintDataTask();		
		
		// wire data
		adressdatenÜbermitteln.overwriteDatahandle(haushalteErmitteln.getDataHandle());
		datasplit.overwriteDatahandle(adressdatenÜbermitteln.getDataHandle());
		printData.overwriteDatahandle(datamerge2.getDataHandle());
		
		// setup BPG and connect tasks
		IBPG bpg = BPGFactory.createBPG();
		
		bpg.connect(bpg.getStart(), zielGruppe);
		bpg.connect(zielGruppe, split1);
		bpg.connectFromSplit(split1, haushalteErmitteln, prospekteAnfertigen);
		bpg.connectToJoin(haushalteErmitteln, prospekteAnfertigen, join1);
		bpg.connect(join1, prospekteVerschicken);
		bpg.connect(prospekteVerschicken, adressdatenÜbermitteln);
		bpg.connect(adressdatenÜbermitteln, warten);
		
		bpg.connect(warten, split2);
		
		bpg.connectFromSplit(split2, kundenAdressen, datasplit);		
		bpg.connect(datasplit, adressDatenKonvertieren);
		bpg.connect(adressDatenKonvertieren, datamerge);	
		bpg.connectToJoin(kundenAdressen, datamerge, join2);
		
		bpg.connect(join2, datasplit2);
		
		bpg.connect(datasplit2, compareData);
		bpg.connect(compareData, datamerge2);
		bpg.connect(datamerge2, printData);
		
		bpg.connect(printData, bpg.getEnd());
		
		// dry run
		BPGFactory.getDefaultProcessEngine().setNumThreads(1);
		BPGFactory.executeProcess(bpg);	
		int numThreads = 8;
		int[] times = new int[8];
		for(int i =0; i < numThreads; i++){
			BPGFactory.getDefaultProcessEngine().setNumThreads(1+i);
			times[i] = BPGFactory.executeProcess(bpg);	
		}
		for(int i =0; i < numThreads; i++){
			System.out.println(String.format("Threads: %s / Time: %s [ms]", 1+i,times[i]));
		}
			
		System.exit(0);
	}

}
