package unihh.vsis.bpskel.bpmn.impl.gateway;

import unihh.vsis.bpskel.bpmn.core.GatewaySplit;

public class GatewayAndSplit extends GatewaySplit {


	/** To check if Gateway was visited before (for branching)*/
	private boolean branched = false;
	
	public GatewayAndSplit(){
		super();
	}

	public boolean didBranch() {
		return branched;
	}

	public void setBranched() {
		this.branched = true;
	}
	
}
