package tests.bpskel.bpg;

import bpskel.bpg.api.IDataContainer;

public class DataContainer implements IDataContainer {

	Object data;
	public DataContainer(String s){
		this.data = s;
	}
	
	public DataContainer(int i){
		this.data = i;
	}
	
	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public void setData(Object param) {
		data = param;
	}

}
