package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.ICondition;
import bpskel.api.IDataContainer;
import cl.niclabs.skandium.muscles.Condition;

class ConditionMuscle implements Condition<IDataContainer> {
	
	private ICondition bpgCondition;
	public ConditionMuscle(ICondition cond){
		this.bpgCondition = cond;
	}

	@Override
	public boolean condition(IDataContainer param) throws Exception {
		return this.bpgCondition.evaluate();
	}

}
