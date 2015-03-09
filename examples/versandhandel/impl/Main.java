package impl;

import java.io.File;
import java.util.ArrayList;

import tasks.CompareData;
import tasks.ConvertAgencyData;
import tasks.MockupTask;
import tasks.MockupTaskInline;
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

public class Main {

	public static void main(String[] args) {
		
		// Laden der testdaten aus ordner
		File folder = new File("C:\\Users\\ferdinand\\workspace\\Master\\BpSkel\\examples\\resources\\");
		File[] listOfFiles = folder.listFiles();

		ArrayList<String> csvFiles = new ArrayList<>();
	    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".csv")) {
		    	  csvFiles.add(listOfFiles[i].getAbsolutePath());
		      }
	    }
	    
	    // Initialisiere BPGFactory mit default ProcessingEngine (Skandium)
		BPGFactory.initialize(BPGFactory.getDefaultProcessEngine());
		
		// BPG aufsetzen
		IBPG bpg = BPGFactory.createBPG();
		
		// Tasks definieren und verbinden
		ITask zielGruppe = new MockupTask("Zielgruppe definieren");
		bpg.connect(bpg.getStart(), zielGruppe);

		// Fork1
		IGatewaySplit split1 = BPGFactory.createGatewayAndSplit();
		bpg.connect(zielGruppe, split1);		
		ITask haushalteErmitteln = new ReadAgencyData(csvFiles);
		ITask prospekteAnfertigen = new MockupTask("Prospekte anfertigen");		
		bpg.connectFromSplit(split1, haushalteErmitteln, prospekteAnfertigen);		
		IGatewayJoin join1 = BPGFactory.createGatewayAndJoin();
		bpg.connectToJoin(haushalteErmitteln, prospekteAnfertigen, join1);

		// Pipe
		ITask prospekteVerschicken = new MockupTask("Prospekte verschicken");
		ITask adressdatenÜbermitteln = new MockupTask("Adressdaten übermitteln");
		ITask warten = new WaitTask(3);
		bpg.connect(join1, prospekteVerschicken);
		bpg.connect(prospekteVerschicken, adressdatenÜbermitteln);
		bpg.connect(adressdatenÜbermitteln, warten);
		
		// Fork2
		IGatewaySplit split2 = BPGFactory.createGatewayAndSplit();
		bpg.connect(warten, split2);
		// Fork2.a
		ITask kundenAdressen = new ReadClientData(csvFiles);		
		
		// MAP (Fork2.b)
		IDataSplit datasplit = new SplitAgencyData();
		ITask adressDatenKonvertieren = new ConvertAgencyData();
		
		IDataMerge datamerge = new MergeConvertedData();			
		bpg.connectFromSplit(split2, kundenAdressen, datasplit);		
		bpg.connect(datasplit, adressDatenKonvertieren);
		bpg.connect(adressDatenKonvertieren, datamerge);	
		ITask agenturdatenKonvertiert = new MockupTaskInline("Agenturdaten konvertiert");
		bpg.connect(datamerge, agenturdatenKonvertiert);	
		IGatewayJoin join2 = BPGFactory.createGatewayAndJoin();
		bpg.connectToJoin(kundenAdressen, agenturdatenKonvertiert, join2);			
		
		// D&C 
		IDataSplit datasplit2 = new SplitCompareData();
		bpg.connect(join2, datasplit2);
		ITask compareData = new CompareData();
		bpg.connect(datasplit2, compareData);
		IDataMerge datamerge2 = new MergeCompareData();
		bpg.connect(compareData, datamerge2);
		
		// print results
		ITask printData = new PrintDataTask();		
		bpg.connect(datamerge2, printData);
		// Finish
		bpg.connect(printData, bpg.getEnd());
		
		// Datenrouting aufsetzen
		adressdatenÜbermitteln.overwriteDatahandle(haushalteErmitteln.getDataHandle());
		datasplit.overwriteDatahandle(adressdatenÜbermitteln.getDataHandle());
		printData.overwriteDatahandle(datamerge2.getDataHandle());
				
		// dry run
		BPGFactory.getDefaultProcessEngine().setNumThreads(1);
		//BPGFactory.executeProcess(bpg);	
		int numThreads = 8;
		int[] times = new int[8];
		for(int i =0; i < numThreads; i++){
			System.out.println("");
			System.out.println("########################################");
			System.out.println(String.format("Starting run with %s threads", i));
			BPGFactory.getDefaultProcessEngine().setNumThreads(1+i);
			times[i] = BPGFactory.executeProcess(bpg);
			System.out.println(String.format("Finished: %s [ms]", times[i]));
		}
		for(int i =0; i < numThreads; i++){
			System.out.println(String.format("Threads: %s / Time: %s [ms]", 1+i,times[i]));
		}
			
		System.exit(0);
	}

}
