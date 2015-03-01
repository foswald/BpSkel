package bpskel.engine.skeleton.impl.executor.skandium;

import bpskel.bpg.api.ICondition;
import bpskel.bpg.api.IDataContainer;
import cl.niclabs.skandium.muscles.Condition;

public class ConditionMuscle implements Condition<IDataContainer> {
	
	private ICondition bpgCondition;
	public ConditionMuscle(ICondition cond){
		this.bpgCondition = cond;
	}

	@Override
	public boolean condition(IDataContainer param) throws Exception {
		return this.bpgCondition.evaluate();
	}

}
