package bpskel.api;



public class DataContainer implements IDataContainer{
	private Object data;
	private Class<?> typeT;
	
	public DataContainer(Object data){
		this.data = data;
	}

	@Override
	public Object getData() throws NullPointerException {
		return this.data;
	}
	
	@Override
	public <T> T getData(Class<T> typeT) {
		if(typeT != this.typeT){
			String msg = String.format("Types do not match! (requested: %, expected: %)", typeT.getName(), this.typeT.getName());
			throw new Error(msg);

		}
		return typeT.cast(data);
	}

	@Override
	public void setData(Object param, Class<?> typeT) {
		this.data = param;
		this.typeT = typeT;
	}
	
	@Override
	public Class<?> getDataType() {
		return this.typeT;
	}
}
