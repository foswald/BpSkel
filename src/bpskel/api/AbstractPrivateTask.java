package bpskel.api;

/**
 * This class has package visibility and should not be directly implemented outside this scope.
 * @author foswald
 *
 */
abstract class AbstractPrivateTask extends AbstractDataHandle implements ITask{

	private boolean executeInline;
	
	public AbstractPrivateTask(){
		this.executeInline = false;
	}
	
	public AbstractPrivateTask(boolean executeInline){
		this.executeInline = executeInline;
	}
	
	@Override
	public boolean doExecuteInline(){
		return this.executeInline;
	}
	
	@Override
	public void setExecuteInle(boolean executeInline){
		this.executeInline = executeInline;
	}
}
