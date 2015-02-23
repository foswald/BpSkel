package unihh.vsis.bpskel.tests.shared;

import unihh.vsis.bpskel.bpmn.api.IDataContainer;

public class IntegerDatacontainer implements IDataContainer {

	private int integer;
	public IntegerDatacontainer(int i) {
		this.integer = i;
	}

	@Override
	public Integer getData() {
		return this.integer;
	}

	@Override
	public void setData(Object param) {
		this.integer = (Integer)param;
	}

}
