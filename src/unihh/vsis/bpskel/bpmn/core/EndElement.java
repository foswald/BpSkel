package unihh.vsis.bpskel.bpmn.core;


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
	public IFlowObject getSuccessor(){
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
