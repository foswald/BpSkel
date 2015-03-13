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
import tasks.StartTimer;
import tasks.StopTimer;
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
		
		File folder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/../testdata/");
		if(args.length == 1){
			folder = new File(args[0]);
		}
		
		if(!folder.exists()){
			System.out.println(String.format("Folder: %s does not exist! Please specify a valid path to the testdata!", folder.toPath()));
			System.exit(1);
		}
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
		StartTimer startMapTimer = new StartTimer();
		IDataSplit datasplit = new SplitAgencyData();
		ITask adressDatenKonvertieren = new ConvertAgencyData();
		
		IDataMerge datamerge = new MergeConvertedData();
		StopTimer stopMapTimer = new StopTimer(startMapTimer);
		
		bpg.connectFromSplit(split2, kundenAdressen, startMapTimer);
		bpg.connect(startMapTimer, datasplit);
		bpg.connect(datasplit, adressDatenKonvertieren);
		bpg.connect(adressDatenKonvertieren, datamerge);	
		ITask agenturdatenKonvertiert = new MockupTaskInline("Agenturdaten konvertiert");		
		bpg.connect(datamerge, stopMapTimer);	
		bpg.connect(stopMapTimer, agenturdatenKonvertiert);			
		IGatewayJoin join2 = BPGFactory.createGatewayAndJoin();
		bpg.connectToJoin(kundenAdressen, agenturdatenKonvertiert, join2);			
		
		// D&C 
		StartTimer startDCTimer = new StartTimer();
		IDataSplit datasplit2 = new SplitCompareData();
		bpg.connect(join2, startDCTimer);
		bpg.connect(startDCTimer, datasplit2);
		ITask compareData = new CompareData();
		bpg.connect(datasplit2, compareData);
		IDataMerge datamerge2 = new MergeCompareData();
		bpg.connect(compareData, datamerge2);
		StopTimer stopDCTimer = new StopTimer(startDCTimer);
		bpg.connect(datamerge2, stopDCTimer);
		
		// print results
		ITask printData = new PrintDataTask();		
		bpg.connect(stopDCTimer, printData);
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
		long[][] times = new long[8][3];
		for(int i =0; i < numThreads; i++){
			System.out.println("");
			System.out.println("########################################");
			System.out.println(String.format("Starting run with %s threads", i));
			BPGFactory.getDefaultProcessEngine().setNumThreads(1+i);
			times[i][0] = BPGFactory.executeProcess(bpg);
			times[i][1]= stopMapTimer.getDur();
			times[i][2]= stopDCTimer.getDur();
			System.out.println(String.format("Finished: TotalDur: %s [ms], Map: %s [ms], D&C: %s [ms]", times[i][0], stopMapTimer.getDur(), stopDCTimer.getDur()));
		}
		System.out.println("Threads, totaldur, map, dc");
		for(int i =0; i < numThreads; i++){
			System.out.println(String.format("%s, %s, %s, %s", 1+i,times[i][0], times[i][1], times[i][2]));
		}
		System.out.println("");
		System.out.println("########################################");
		System.out.println("Summary: ");
		for(int i =0; i < numThreads; i++){
			System.out.println(String.format("Threads: %s / TotalDur: %s [ms], Map: %s [ms], D&C: %s [ms]", 1+i,times[i][0], times[i][1], times[i][2]));
		}
			
		System.exit(0);
	}

}
