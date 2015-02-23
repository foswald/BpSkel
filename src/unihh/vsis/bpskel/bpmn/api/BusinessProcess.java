package unihh.vsis.bpskel.bpmn.api;

import java.util.ArrayList;
import java.util.List;

import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IGateway;
import unihh.vsis.bpskel.bpmn.core.StartElement;

public class BusinessProcess {
	
	private List<IFlowObject> flowObjects;
	
	private IFlowObject start;
	private IFlowObject end;
	
	public BusinessProcess() {
		flowObjects = new ArrayList<>();
		start = new StartElement();
		end = new EndElement();
	}
	
	/**
	 * Connects two BPMN elements. It might override existing previous connections.
	 * @param e1 Gateway or Task
	 * @param e2 Gateway or Task
	 */
	public void connect(IFlowObject source, IFlowObject sink){
		source.addOutgoingFlowObject(sink);
		sink.addIncomingFlowObject(source);
	}
	
	public void connectToJoin(IFlowObject source1, IFlowObject source2, IFlowObject join){
		source1.addOutgoingFlowObject(join);
		source2.addOutgoingFlowObject(join);
		join.addIncomingFlowObject(source1);
		join.addIncomingFlowObject(source2);
	}
	
	public void connectFromSplit(IFlowObject source, IFlowObject branch1, IFlowObject branch2){
		source.addOutgoingFlowObject(branch1);
		source.addOutgoingFlowObject(branch2);
		branch1.addIncomingFlowObject(source);
		branch2.addIncomingFlowObject(source);
	}
	
	public void addTask(ITask t){
		this.flowObjects.add(t);
	}
	
	public void addGateway(IGateway f){
		this.flowObjects.add(f);
	}
	
	public List<IFlowObject> getFlowObjects(){
		return this.flowObjects;
	}
	
	public IFlowObject getStart(){
		return start;
	}

	public IFlowObject getEnd() {
		return end;
	}

}
