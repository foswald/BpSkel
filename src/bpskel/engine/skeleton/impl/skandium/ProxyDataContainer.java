package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;

public class ProxyDataContainer implements IDataContainer {

	Object data;
	Class<?> typeT;
	
	@Override
	public Object getData() {
		System.out.println("ProxyDataContainer.getData: " + data.toString());
		return data;
	}

	@Override
	public void setData(Object param, Class<?> typeT) {
		System.out.println("ProxyDataContainer.setData: " + param.toString());
		this.typeT = typeT;
		data = param;
	}
	
	@Override
	public void setData(Object param) {
		System.out.println("ProxyDataContainer.setData: " + param.toString());
		this.data = param;
		if(data!=null){
			this.typeT = data.getClass();
		}
	}

	@Override
	public Class<?> getDataType() {
		return this.typeT;
	}

	@Override
	public <T> T getData(Class<T> typeT) {
		return typeT.cast(data);
	}

}
