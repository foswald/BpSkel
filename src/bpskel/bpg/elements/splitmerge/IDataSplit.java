package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.core.IFlowObject;

/**
 * An IDataSplit uses its <code>splitData(IDataContainer data)</code> method to split
 * <code>data</code> into a sublist of data for parallel execution.  
 * @author foswald
 * @see bpskel.bpg.elements.splitmerge.IDataMerge
 *
 */
public interface IDataSplit extends IFlowObject, IDataHandle{

	/**
	 * Splits <code>data</code> into an array of data, being possible accessed in parallel for execution.
	 * @param data the data to split
	 * @return subdata for parallel execution
	 */
	IDataContainer[] splitData(IDataContainer data);
}
