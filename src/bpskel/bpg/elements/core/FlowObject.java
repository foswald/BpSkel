package bpskel.bpg.elements.core;



/**
 * This abstract class represents a FlowObject inside a BPG which is basically every element except connectors.
 * @author foswald
 *
 */
public abstract class FlowObject implements IFlowObject {

	private IFlowObject pred;
	private IFlowObject succ;
	
	private String description;
	
	@Override
	public void setDescription(String s){
		this.description = s;
	}
	
	@Override
	public String getDescription(){
		return this.description;
	}

	@Override
	public void resetPredecessor(){
		pred = null;
	}

	@Override
	public void resetSuccessor(){
		succ = null;
	}

	@Override
	public void setPredecessor(IFlowObject flowObject){
		pred = flowObject;
	}
	
	@Override
	public void setSuccessor(IFlowObject flowObject){
		succ = flowObject;
	}
	
	@Override
	public IFlowObject getPredecessor() {
		return pred;
	}

	@Override
	public IFlowObject getSuccessor() {
		return succ;
	}
	
	@Override 
	public boolean isEmpty(){
		return succ == null && pred == null;
	}


}