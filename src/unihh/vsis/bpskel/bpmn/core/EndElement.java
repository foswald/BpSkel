package unihh.vsis.bpskel.bpmn.core;

import unihh.vsis.bpskel.bpmn.api.IDataContainer;

/**
 * The Endelement is the only element which has no succeeding FlowObject.
 * @author foswald
 *
 */
public class EndElement extends AbstractFlowObject {

	public EndElement(){
		this.setDescription("End Element");
	}
	
	@Override
	public FlowObjectContainer getOutgoingFlowObjects(){
		return null;
	}
}
