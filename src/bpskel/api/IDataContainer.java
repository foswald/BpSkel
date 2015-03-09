package bpskel.api;

/**
 * The IDataContainer is used to facilitate datahandling within BpSkel.
 * Any task working with data should use this DataContainer to reference conent used over different tasks.
 * @author foswald
 *
 */
public interface IDataContainer {
	
	/**
	 * 
	 * @return the data contained in this IDataContainer as Object
	 * @throws NullPointerException
	 */
	Object getData() throws NullPointerException;
	
	/**
	 * Return the Data after casting it to <code>typeT</code>, fails on casting error.
	 * @param typeT - the type of the Object to return
	 * @return enclosed object
	 */
	<T>T getData(Class<T> typeT);
	
	/**
	 * Set the content of the DataContainer
	 * @param param the content
	 * @param typeT type type of the content
	 */
	void setData(Object param, Class<?> typeT);
	
	/**
	 * Set the data without setting the type.
	 * @param param the content of the object
	 */
	void setData(Object param);
	
	/**
	 * Retrieve the type of the content.
	 * @return
	 */
	Class<?> getDataType();
}
