package unihh.vsis.bpskel.executor.skandium;

import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import unihh.vsis.bpskel.bpmn.core.ICondition;
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
