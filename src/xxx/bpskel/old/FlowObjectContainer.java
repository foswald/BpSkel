package xxx.bpskel.old;

import bpskel.bpg.elements.core.IFlowObject;


/**
 * Contains one or multiple FlowObjects.
 * @author foswald
 *
 */
public class FlowObjectContainer {

	public IFlowObject first;
	public IFlowObject second;
	
	public FlowObjectContainer(){
	}
	
	public FlowObjectContainer(IFlowObject first){
		this.first = first;
	}
	
	public FlowObjectContainer(IFlowObject first, IFlowObject second){
		this.first = first;
		this.second = second;
	}
}
