package bpskel.bpg.impl.core;

import bpskel.bpg.api.IDataContainer;

public interface IDataMerge extends IFlowObject {

	IDataContainer mergeData(IDataContainer[] data);
}
