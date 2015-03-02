package xxx.bpskel.old;

import bpskel.api.IDataContainer;

public class DataJoiner implements IDataContainer {

	@SuppressWarnings("unused")
	private IDataContainer data1;
	@SuppressWarnings("unused")
	private IDataContainer data2;
	
	public static IDataContainer aggregate(IDataContainer data1, IDataContainer data2){
		return new DataJoiner(data1, data2);
	}

	public DataJoiner(IDataContainer data1, IDataContainer data2) {
		this.data1 = data1;
		this.data2 = data2;

	}
	
	@Override
	public Object getData() {
		return this;
	}

	@Override
	public void setData(Object param) {
	}

}
