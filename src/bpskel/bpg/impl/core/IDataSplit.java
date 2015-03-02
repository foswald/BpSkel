package bpskel.bpg.impl.core;

import bpskel.bpg.api.IDataContainer;

public interface IDataSplit extends IFlowObject{

	IDataContainer[] splitData(IDataContainer data);
}
