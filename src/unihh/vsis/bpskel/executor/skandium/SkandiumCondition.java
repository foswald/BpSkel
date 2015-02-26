package unihh.vsis.bpskel.executor.skandium;

import bpskel.bpg.api.ICondition;
import bpskel.bpg.api.IDataContainer;
import cl.niclabs.skandium.muscles.Condition;

public class SkandiumCondition implements Condition<IDataContainer> {
	
	private ICondition bpgCondition;
	public SkandiumCondition(ICondition cond){
		this.bpgCondition = cond;
	}

	@Override
	public boolean condition(IDataContainer param) throws Exception {
		return this.bpgCondition.evaluate();
	}

}
