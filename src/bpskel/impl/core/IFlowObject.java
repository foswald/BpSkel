package bpskel.impl.core;



/**
 * This is the basic Interface for all FlowObjects.<br>
 * FlowObjects are Activities(Tasks), Events and Gateways.
 * 
 * A FlowObject has exactly one predecessor and one successor.
 *  
 * @author foswald
 *
 */
public interface IFlowObject extends IBPGElement{
	
	/**
	 * Returns one or more FlowObjects preceding this FlowObject.
	 * @return
	 */
	IFlowObject getPredecessor();
	
	/**
	 * Returns one or more FlowObjects succeeding this FlowObject.
	 * @return
	 */
	IFlowObject getSuccessor();
	
	/**
	 * Reset the list of incoming FlowObjects
	 */
	void resetPredecessor();
	
	/**
	 * Reset the list of outgoing FlowObjects
	 */
	void resetSuccessor();
	
	/**
	 * Add an incomingFlowObject
	 * @param flowObject
	 */
	void setPredecessor(IFlowObject flowObject);
	
	/**
	 * Add an outgoingFlowObject
	 * @param flowObject
	 */
	void setSuccessor(IFlowObject flowObject);
	
	String getDescription();
	
	void setDescription(String s);

	/**
	 * @return <code>true</code> if the FlowObject has no preceding and succeeding nodes.
	 */
	boolean isEmpty();
}
