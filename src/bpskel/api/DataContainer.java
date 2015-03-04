package bpskel.api;



public class DataContainer implements IDataContainer{
	private Object data;
	private Class<?> typeT;
	
	public DataContainer(Object data){
		this.data = data;
		if(data!=null){
			this.typeT = data.getClass();
		}
	}

	@Override
	public Object getData() throws NullPointerException {
		return this.data;
	}
	
	@Override
	public <T> T getData(Class<T> typeT) {
		if(typeT != this.typeT){
			String t1 = typeT== null ? "null" : typeT.getName();
			String t2 = this.typeT== null ? "null" : this.typeT.getName();
			
			String msg = String.format("Types do not match! (requested: %s, contained: %s) - Object: %s", t1, t2, this.toString());
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
