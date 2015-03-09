/**
 * 
 */
package bpskel.api;



/** The <code>AbstractForTask</code> is being repeated for a specified number of times.
 * @author foswald
 *
 */
public abstract class AbstractForTask extends AbstractTask implements IForTask {


	private int numIterations;
	protected int numCurrentItartion;
	
	/**
	 * Creates AbstractForTask being executed for <code>numIterations</code>
	 * @param numIterations
	 */
	public AbstractForTask(int numIterations){
		this.numIterations = numIterations;
		this.numCurrentItartion = 0;
	}

	@Override
	public int getNumIterations() {
		return this.numIterations;
	}

	@Override
	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}


}
