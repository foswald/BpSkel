package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public interface IPattern {
	boolean matchPattern(IFlowObject start);
	Component foldToComponent(IFlowObject start);
	ISkeleton createSkeleton(Component start);
}
