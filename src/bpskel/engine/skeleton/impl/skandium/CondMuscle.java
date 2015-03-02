package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.splitmerge.IDataCondition;
import cl.niclabs.skandium.muscles.Condition;

public class CondMuscle implements Condition<IDataContainer> {
	
	private IDataCondition cond;
	
	public CondMuscle(IDataCondition cond){
		this.cond = cond;
	}

	@Override
	public boolean condition(IDataContainer param) throws Exception {
		return this.cond.evaluate(param);
	}

}
