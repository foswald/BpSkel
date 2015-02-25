package unihh.vsis.bpskel.bpmn.api;

import java.util.ArrayList;
import java.util.List;

import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.GatewayJoin;
import unihh.vsis.bpskel.bpmn.core.GatewaySplit;
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
		source.setSuccessor(sink);
		sink.setPredecessor(source);
	}
	
	/**
	 * Connects source with sink and resets previous connections if <code>reset=true</code>.
	 * @param source
	 * @param sink
	 * @param reset
	 */
	public void connect(IFlowObject source, IFlowObject sink, boolean reset){
		if(reset) {
			source.resetSuccessor();
			sink.resetPredecessor();
		}
		this.connect(source, sink);
	}
	
	public void connectToJoin(IFlowObject source1, IFlowObject source2, GatewayJoin join){
		source1.setSuccessor(join);
		source2.setSuccessor(join);
		join.setPredecessor(source1);
		join.setPredecessor2(source2);
	}
	
	public void connectFromSplit(GatewaySplit source, IFlowObject branch1, IFlowObject branch2){
		source.setSuccessor(branch1);
		source.setSuccessor2(branch2);
		branch1.setPredecessor(source);
		branch2.setPredecessor(source);
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
