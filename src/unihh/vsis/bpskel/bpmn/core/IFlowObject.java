package unihh.vsis.bpskel.bpmn.core;


/**
 * This is the basic Interface for all FlowObjects.<br>
 * FlowObjects are Activities(Tasks), Events and Gateways.
 * 
 * FlowObjects might have preceding and succeeding flowobjects.
 * These represent Objects which are connected by a SequenceFlow.
 *  
 * @author foswald
 *
 */
public interface IFlowObject extends IBPMNElement{
	
	/**
	 * Returns one or more FlowObjects preceding this FlowObject.
	 * @return
	 */
	FlowObjectContainer getIncomingFlowObjects();
	
	/**
	 * Returns one or more FlowObjects succeeding this FlowObject.
	 * @return
	 */
	FlowObjectContainer getOutgoingFlowObjects();
	
	/**
	 * Reset the list of incoming FlowObjects
	 */
	void resetIncomingFlowObjects();
	
	/**
	 * Reset the list of outgoing FlowObjects
	 */
	void resetOutgoingFlowObjects();
	
	/**
	 * Add an incomingFlowObject
	 * @param flowObject
	 */
	void addIncomingFlowObject(IFlowObject flowObject);
	
	/**
	 * Add an outgoingFlowObject
	 * @param flowObject
	 */
	void addOutgoingFlowObject(IFlowObject flowObject);
	
	String getDescription();
	
	void setDescription(String s);
}
