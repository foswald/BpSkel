package bpskel.engine.skeleton.impl.executor.skandium;

import bpskel.bpg.api.ICondition;
import bpskel.bpg.api.IDataContainer;
import cl.niclabs.skandium.muscles.Condition;

public abstract class BpCondition<P extends IDataContainer> implements Condition<IDataContainer>{

	private ICondition cond;
	public BpCondition(ICondition cond){
		this.cond = cond;
	}

	@Override
	public boolean condition(IDataContainer cond) throws Exception {
		return this.cond.evaluate();
	}

}
