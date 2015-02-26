package tests.bpskel.shared;

import bpskel.bpg.api.IDataContainer;

public class UniversalContainer implements IDataContainer {

	Object data;
	public UniversalContainer(String s){
		this.data = s;
	}
	
	public UniversalContainer(int i){
		this.data = i;
	}
	
	@Override
	public Object getData() {
		return data;
	}

	@Override
	public void setData(Object param) {
		data = param;
	}

}
