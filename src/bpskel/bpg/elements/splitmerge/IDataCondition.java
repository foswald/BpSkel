package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;

/**
 * This datacondition is used for evaluating computed data within the processing stream as opposed to routing in GatewaySplits.
 * @author ferdinand
 *
 */
public interface IDataCondition {
	
	boolean evaluate(IDataContainer param);

}
