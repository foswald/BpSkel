package bpskel.api;

public interface IDataContainer {
	
	Object getData() throws NullPointerException;
	
	<T>T getData(Class<T> typeT);
	
	void setData(Object param, Class<?> typeT);
	
	void setData(Object param);
	
	Class<?> getDataType();
}
