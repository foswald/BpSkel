package unihh.vsis.bpskel.bpmn.core;


public abstract class AbstractSplitConditional extends AbstractSplitFlowObject implements ICondition{
	protected ICondition condition;
	
	public AbstractSplitConditional(ICondition condition){
		super();
		this.condition = condition;
	}
	
	@Override
	public boolean evaluate() {
		return this.condition.evaluate();
	}
}
