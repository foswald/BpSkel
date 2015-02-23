package unihh.vsis.bpskel.bpmn.core;


/**
 * The AbstractFlowObject can have any number of preceding and succeeding FlowObjects
 * @author foswald
 *
 */
public abstract class AbstractFlowObject implements IFlowObject {

	private FlowObjectContainer incomingFlowObjects;
	private FlowObjectContainer outgoingFlowObjects;
	
	private String description;
	
	public AbstractFlowObject(){
		incomingFlowObjects = new FlowObjectContainer();
		outgoingFlowObjects = new FlowObjectContainer();
	}
	
	@Override
	public void setDescription(String s){
		this.description = s;
	}
	
	@Override
	public String getDescription(){
		return this.description;
	}

	@Override
	public void resetIncomingFlowObjects(){
		incomingFlowObjects = new FlowObjectContainer();
	}

	@Override
	public void resetOutgoingFlowObjects(){
		outgoingFlowObjects = new FlowObjectContainer();
	}

	@Override
	public void addIncomingFlowObject(IFlowObject flowObject){
		if(incomingFlowObjects.first == null){
			incomingFlowObjects.first = flowObject;
		}
		else if(incomingFlowObjects.second == null){
			incomingFlowObjects.second = flowObject;
		}
		else {
			throw new Error("All connections occupied. Reset first!");
		}
	}
	
	@Override
	public void addOutgoingFlowObject(IFlowObject flowObject){
		if(outgoingFlowObjects.first == null){
			outgoingFlowObjects.first = flowObject;
		}
		else if(outgoingFlowObjects.second == null){
			outgoingFlowObjects.second = flowObject;
		}
		else {
			throw new Error("All connections occupied. Reset first!");
		}
	}
	
	@Override
	public FlowObjectContainer getIncomingFlowObjects() {
		return incomingFlowObjects;
	}

	@Override
	public FlowObjectContainer getOutgoingFlowObjects() {
		return outgoingFlowObjects;
	}


}
