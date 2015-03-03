package impl;

import bpskel.api.BPGFactory;
import bpskel.api.IBPG;

public class BPGProcess {

	public static IBPG createProcess(){
		IBPG pro = BPGFactory.createBPG();
		
		return pro;
	}
}
