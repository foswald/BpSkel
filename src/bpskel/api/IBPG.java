package bpskel.api;

import bpskel.bpg.elements.core.IFlowObject;

/**
 * Defines connection methods for a BPG diagramm
 * @author foswald
 */
public interface IBPG {

	/**
	 * Connects two BPMN elements. It might override existing previous connections.
	 * @param source Gateway or Task
	 * @param sink Gateway or Task
	 */
	public void connect(IFlowObject source, IFlowObject sink);

	/** Reconnects an element to a new destination.
	 * @note this works in both directions!
	 * @param sourceElement
	 * @param oldElement
	 * @param newElement
	 */
	public void reconnect(IFlowObject sourceElement, IFlowObject oldElement,
			IFlowObject newElement);

	/**
	 * Connect two sources to a GatewayJoin
	 * @param source1
	 * @param source2
	 * @param join
	 */
	public void connectToJoin(IFlowObject source1, IFlowObject source2,
			IGatewayJoin join);

	/**
	 * Connect two FlowObjects from a GatewaySplit
	 * @param source
	 * @param branch1
	 * @param branch2
	 */
	public void connectFromSplit(IGatewaySplit source, IFlowObject branch1,
			IFlowObject branch2);

	/**
	 * Creates a while lopp inside the BPG.
	 * @param source The source object leading to the loop.
	 * @param sink The object after the while loop
	 * @param cond The condition for the while loop
	 * @param firstItem The first item inside the while loop
	 */
	public void insertIntoWhileLoop(IFlowObject source, IFlowObject sink,
			ICondition cond, IFlowObject firstItem, IFlowObject lastItem);

	/**
	 * Creates a while lopp inside the BPG.
	 * @param source The source object leading to the loop.
	 * @param sink The object after the while loop
	 * @param cond The condition for the while loop
	 * @param content The first item inside the while loop
	 */
	public void insertIntoWhileLoop(IFlowObject source, IFlowObject sink,
			ICondition cond, IFlowObject content);

	/**
	 * The start event of this BPG
	 */
	public IFlowObject getStart();

	/**
	 * The Endevent of this BPG
	 */
	public IFlowObject getEnd();

}