package unihh.vsis.bpskel.bpmn.core;

public interface IGateway extends IFlowObject {
	
	boolean isFlowSplit();
	
	boolean isFlowJoin();
}
