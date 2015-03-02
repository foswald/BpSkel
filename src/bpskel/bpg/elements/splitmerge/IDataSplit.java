package bpskel.bpg.elements.splitmerge;

import bpskel.api.IDataContainer;
import bpskel.bpg.elements.core.IFlowObject;

public interface IDataSplit extends IFlowObject{

	IDataContainer[] splitData(IDataContainer data);
}
