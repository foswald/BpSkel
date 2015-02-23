package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public interface IPattern<T> {
	boolean matchPattern(IFlowObject start);
	Component<T> foldToComponent(IFlowObject start);
	T createSkeleton(Component<T> start);
}
