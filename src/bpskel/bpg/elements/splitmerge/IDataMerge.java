package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.core.IFlowObject;

public interface IDataMerge extends IFlowObject, IDataHandle{

	IDataContainer mergeData(IDataContainer[] data);
}
