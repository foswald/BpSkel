package impl;

import impl.tasks.MockupTask;
import impl.tasks.ReadCSVTask;
import impl.tasks.WaitTask;

import java.io.File;
import java.util.ArrayList;

import bpskel.api.BPGFactory;
import bpskel.api.IBPG;
import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.api.ITask;

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
		ITask haushalteErmitteln = new ReadCSVTask(csvFiles.toArray(new String[csvFiles.size()]));
		ITask prospekteAnfertigen = new MockupTask("Prospekte anfertigen");
		ITask prospekteVerschicken = new MockupTask("Prospekte verschicken");
		ITask adressdatenÜbermitteln = new MockupTask("Adressdaten übermitteln");
		ITask warten = new WaitTask(3);
		
		IGatewaySplit split1 = BPGFactory.createGatewayAndSplit();
		IGatewayJoin join1 = BPGFactory.createGatewayAndJoin();
		
		IBPG bpg = BPGFactory.createBPG();
		
		bpg.connect(bpg.getStart(), zielGruppe);
		bpg.connect(zielGruppe, split1);
		bpg.connectFromSplit(split1, haushalteErmitteln, prospekteAnfertigen);
		bpg.connectToJoin(haushalteErmitteln, prospekteAnfertigen, join1);
		bpg.connect(join1, prospekteVerschicken);
		bpg.connect(prospekteVerschicken, adressdatenÜbermitteln);
		bpg.connect(adressdatenÜbermitteln, warten);
		
		bpg.connect(warten, bpg.getEnd());
		
		BPGFactory.executeProcess(bpg);
		
	}

}
