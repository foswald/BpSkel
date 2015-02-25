package unihh.vsis.bpskel.bpmn.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.GatewayJoin;
import unihh.vsis.bpskel.bpmn.core.GatewaySplit;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IGateway;
import unihh.vsis.bpskel.bpmn.core.StartElement;

public class BusinessProcess {
	
	private Set<IFlowObject> flowObjects;
	
	private IFlowObject start;
	private IFlowObject end;
	
	public BusinessProcess() {
		flowObjects = new HashSet<IFlowObject>();
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

	/** Reconnects an element to a new destination.
	 * @note this works in both directions!
	 * @param sourceElement
	 * @param oldElement
	 * @param newElement
	 */
	public void reconnect(IFlowObject sourceElement, IFlowObject oldElement, IFlowObject newElement){
		// no gateway means simply replace oldElement with newElement
		if(sourceElement.getPredecessor()!=null && sourceElement.getPredecessor().equals(oldElement)){
			sourceElement.setPredecessor(newElement);
		}
		else if(sourceElement.getSuccessor()!=null && sourceElement.getSuccessor().equals(oldElement)){
			sourceElement.setSuccessor(newElement);
		}
		else if(sourceElement instanceof GatewaySplit && 
				((GatewaySplit) sourceElement).getSuccessor2().equals(oldElement)){
			// try second element
			((GatewaySplit) sourceElement).setSuccessor2(oldElement);
		}
		else if(sourceElement instanceof GatewayJoin && 
				((GatewayJoin) sourceElement).getPredecessor2().equals(oldElement)){
			// try second element
			((GatewayJoin) sourceElement).setPredecessor2(oldElement);
		}
		else{
			throw new Error("Old Element not referenced by sourceElement");
		}
		
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
	
	public Set<IFlowObject> getFlowObjects(){
		return this.flowObjects;
	}
	
	public IFlowObject getStart(){
		return start;
	}

	public IFlowObject getEnd() {
		return end;
	}

}
