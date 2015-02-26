package bpskel.bpg.impl.core;


public class StartElement extends AbstractFlowObject {

	public StartElement(){
		this.setDescription("Start Element");
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
