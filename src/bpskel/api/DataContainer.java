package bpskel.api;


public class DataContainer implements IDataContainer{
	private Object data;
	
	public DataContainer(Object data){
		this.data = data;
	}

	@Override
	public Object getData() throws NullPointerException {
		return this.data;
	}

	@Override
	public void setData(Object param) {
		this.data = param;
	}
}
