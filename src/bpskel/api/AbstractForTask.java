/**
 * 
 */
package bpskel.api;



/**
 * @author foswald
 *
 */
public abstract class AbstractForTask extends AbstractTask implements IForTask {


	private int numIterations;
	protected int numCurrentItartion;
	
	public AbstractForTask(int numIterations){
		this.numIterations = numIterations;
		this.numCurrentItartion = 0;
	}
	
	/**
	 * @see bpskel.api.IForTask#getNumIterations()
	 */
	@Override
	public int getNumIterations() {
		return this.numIterations;
	}

	/**
	 * @see bpskel.api.IForTask#setNumIterations(int)
	 */
	@Override
	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}


}
