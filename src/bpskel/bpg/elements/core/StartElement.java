package bpskel.bpg.elements.core;



public class StartElement extends FlowObject {

	public StartElement(){
		this.setDescription("Start Element");
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
