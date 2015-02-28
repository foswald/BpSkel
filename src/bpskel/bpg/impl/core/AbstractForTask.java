/**
 * 
 */
package bpskel.bpg.impl.core;


/**
 * @author foswald
 *
 */
public abstract class AbstractForTask extends AbstractTask implements IForTask {


	private int numIterations;
	private int numCurrentItartion;
	
	public AbstractForTask(int numIterations){
		this.numIterations = numIterations;
		this.numCurrentItartion = 0;
	}
	
	/**
	 * @see bpskel.bpg.impl.core.IForTask#getNumIterations()
	 */
	@Override
	public int getNumIterations() {
		return this.numIterations;
	}

	/**
	 * @see bpskel.bpg.impl.core.IForTask#setNumIterations(int)
	 */
	@Override
	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}

	/**
	 * @see bpskel.bpg.impl.core.IForTask#getCurrentIteration()
	 */
	@Override
	public int getCurrentIteration() {
		return this.numCurrentItartion;
	}

}
