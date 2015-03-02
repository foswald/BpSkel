package bpskel.engine.skeleton.impl.skandium;

import bpskel.api.IDataContainer;

public class ProxyDataContainer implements IDataContainer {

	Object data;
	@Override
	public Object getData() {
		System.out.println("ProxyDataContainer.getData: " + data.toString());
		return data;
	}

	@Override
	public void setData(Object param) {
		System.out.println("ProxyDataContainer.setData: " + param.toString());
		data = param;
	}

}
