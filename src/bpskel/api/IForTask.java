package bpskel.api;


public interface IForTask extends ITask {

	/**
	 * @return the number of iterations this task should be performed
	 */
	public int getNumIterations();
	
	/**
	 * Sets the number of iterations this task should be performed
	 * @param numIterations
	 */
	public void setNumIterations(int numIterations);
}
