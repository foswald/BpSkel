package unihh.vsis.bpskel.bpmn.core;


public abstract class AbstractGatewaySplitConditional extends GatewaySplit implements ICondition{
	protected ICondition condition;
	
	public AbstractGatewaySplitConditional(ICondition condition){
		super();
		this.condition = condition;
	}
	
	@Override
	public boolean evaluate() {
		return this.condition.evaluate();
	}
}
