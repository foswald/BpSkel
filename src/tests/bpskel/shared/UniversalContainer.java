package tests.bpskel.shared;

import bpskel.api.IDataContainer;

public class UniversalContainer implements IDataContainer {

	Object data;
	Class<?> typeT;
	
	public UniversalContainer(String s){
		this.data = s;
		this.typeT = String.class;
	}
	
	public UniversalContainer(int i){
		this.data = i;
		this.typeT = Integer.class;
	}
	
	@Override
	public Object getData() {
		return data;
	}

	@Override
	public <T> T getData(Class<T> typeT) {
		return typeT.cast(data);
	}
	
	@Override
	public void setData(Object param, Class<?> typeT) {
		data = param;
		this.typeT = typeT;
	}
	
	@Override
	public void setData(Object param) {
		this.data = param;
		if(data!=null){
			this.typeT = data.getClass();
		}
	}

	@Override
	public Class<?> getDataType() {
		return this.typeT;
	}


}
