package unihh.vsis.bpskel.executor.skandium;

import unihh.vsis.bpskel.bpmn.api.IDataContainer;
import unihh.vsis.bpskel.bpmn.core.ICondition;
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
