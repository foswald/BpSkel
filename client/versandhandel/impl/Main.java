package impl;

import java.io.File;
import java.util.ArrayList;

import bpgelements.ConvertAgencyData;
import bpgelements.MockupTask;
import bpgelements.ReadAgencyData;
import bpgelements.SplitAgencyData;
import bpgelements.WaitTask;
import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.api.ITask;
import bpskel.bpg.elements.splitmerge.IDataMerge;
import bpskel.bpg.elements.splitmerge.IDataSplit;

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
		ITask warten = new WaitTask(3);
		
		IGatewaySplit split2 = BPGFactory.createGatewayAndSplit();
		ITask kundenAdressen = new ReadClientData(csvFiles);		
		
		IDataSplit datasplit = new SplitAgencyData();
		ITask adressDatenKonvertieren = new ConvertAgencyData();
		IDataMerge datamerge = new MergeConvertedData();
		
		
		IGatewayJoin join2 = BPGFactory.createGatewayAndJoin();
		ITask printData = new PrintDataTask();		
		
		// wire data
		adressdatenÜbermitteln.overwriteDatahandle(haushalteErmitteln.getDataHandle());
		datasplit.overwriteDatahandle(adressdatenÜbermitteln.getDataHandle());
		printData.overwriteDatahandle(datamerge.getDataHandle());
		
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
		
		bpg.connect(join2, printData);
		
		bpg.connect(printData, bpg.getEnd());
		
		BPGFactory.executeProcess(bpg);		
	}

}
