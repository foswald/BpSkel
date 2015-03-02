package bpskel.api;

public interface IDataContainer {
	
	Object getData() throws NullPointerException;
	
	void setData(Object param);
}
