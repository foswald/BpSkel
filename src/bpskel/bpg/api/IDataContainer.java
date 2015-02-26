package bpskel.bpg.api;

public interface IDataContainer {
	
	Object getData() throws NullPointerException;
	
	void setData(Object param);
}
