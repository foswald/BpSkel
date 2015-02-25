package unihh.vsis.bpskel.tests.skel;

import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import unihh.vsis.bpskel.bpmn.core.ICondition;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorSplit;
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
