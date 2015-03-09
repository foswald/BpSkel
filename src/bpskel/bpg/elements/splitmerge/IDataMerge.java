package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.core.IFlowObject;

/**
 * An IDataMerge uses its <code>mergeData(IDataContainer[] data)</code> to merge the 
 * array of <code>data</code> into a single resulting <code>IDataContainer</code>
 * @author foswald
 * @see bpskel.bpg.elements.splitmerge.IDataSplit
 *
 */
public interface IDataMerge extends IFlowObject, IDataHandle{

	/**
	 * Merges the elements <code>data</code> into one single <code>IDataContainer</code>
	 * @param data the data to merge
	 * @return the merged data
	 */
	IDataContainer mergeData(IDataContainer[] data);
}
