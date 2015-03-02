package tests.bpskel.shared;

import bpskel.api.IDataContainer;

public class DataContainer<T> implements IDataContainer{

	T data;
	
	public DataContainer(T data){
		this.data = data;
	}
	
	@Override
	public Object getData() throws NullPointerException {
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setData(Object param) {
		this.data = (T)param;
	}

}
