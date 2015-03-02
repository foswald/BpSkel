package bpskel.api;


public interface ICondition{
	
	/** Evaluate the condition to true or false
	 * 
	 * @return
	 */
	boolean evaluate();
	
	/**
	 * String representation of the condition
	 * @return
	 */
	String getDescription();

}
