package unihh.vsis.bpskel.bpmn.core;


public class StartElement extends AbstractFlowObject {

	public StartElement(){
		this.setDescription("Start Element");
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
