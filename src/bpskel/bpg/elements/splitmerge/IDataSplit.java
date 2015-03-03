package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.core.IFlowObject;

public interface IDataSplit extends IFlowObject, IDataHandle{

	IDataContainer[] splitData(IDataContainer data);
}
