package tests.bpskel.skeleton;

import bpskel.api.ICondition;
import bpskel.api.IDataContainer;
import bpskel.bpg.elements.gateway.GatewayXorSplit;
import cl.niclabs.skandium.muscles.Condition;

public class WhileCond extends GatewayXorSplit implements Condition<IDataContainer> {

	
	public WhileCond(ICondition condition) {
		super(condition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition(IDataContainer param) throws Exception {
		// we don't need param object here, as ref is bound on creation on this.condition
		return this.condition.evaluate();
	}

}
