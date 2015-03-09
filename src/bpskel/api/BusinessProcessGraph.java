package bpskel.api;

import bpskel.bpg.elements.core.EndElement;
import bpskel.bpg.elements.core.IFlowObject;
import bpskel.bpg.elements.core.StartElement;
import bpskel.bpg.elements.gateway.GatewayJoin;
import bpskel.bpg.elements.gateway.GatewaySplit;

/**
 * This class represents a BPG.
 * It only holds direct references to the start and end events. All connect events can be accessed through the
 * traversion of the graph.
 * @author foswald
 * @see bpskel.api.IBPG
 */
public class BusinessProcessGraph implements IBPG {

	private IFlowObject start;
	private IFlowObject end;
	
	/**
	 * Creates a new BPG with unique Start and Endelement.
	 * This ctor is abstract and should only be used by the BPGFactory
	 */
	BusinessProcessGraph() {
		start = new StartElement();
		end = new EndElement();
	}
	
	@Override
	public void connect(IFlowObject source, IFlowObject sink){
		source.setSuccessor(sink);
		sink.setPredecessor(source);
	}

	@Override
	public void reconnect(IFlowObject sourceElement, IFlowObject oldElement, IFlowObject newElement){
		// no gateway means simply replace oldElement with newElement
		if(sourceElement.getPredecessor()!=null && sourceElement.getPredecessor().equals(oldElement)){
			sourceElement.setPredecessor(newElement);
		}
		else if(sourceElement.getSuccessor()!=null && sourceElement.getSuccessor().equals(oldElement)){
			sourceElement.setSuccessor(newElement);
		}
		else if(sourceElement instanceof GatewaySplit && 
				((IGatewaySplit) sourceElement).getSuccessor2().equals(oldElement)){
			// try second element
			((IGatewaySplit) sourceElement).setSuccessor2(oldElement);
		}
		else if(sourceElement instanceof GatewayJoin && 
				((IGatewayJoin) sourceElement).getPredecessor2().equals(oldElement)){
			// try second element
			((IGatewayJoin) sourceElement).setPredecessor2(oldElement);
		}
		else{
			throw new Error("Old Element not referenced by sourceElement");
		}
		
	}
	
	@Override
	public void connectToJoin(IFlowObject source1, IFlowObject source2, IGatewayJoin join){
		source1.setSuccessor(join);
		source2.setSuccessor(join);
		join.setPredecessor(source1);
		join.setPredecessor2(source2);
	}
	
	@Override
	public void connectFromSplit(IGatewaySplit source, IFlowObject branch1, IFlowObject branch2){
		source.setSuccessor(branch1);
		source.setSuccessor2(branch2);
		branch1.setPredecessor(source);
		branch2.setPredecessor(source);
	}
	
	@Override
	public void insertIntoWhileLoop(IFlowObject source, IFlowObject sink, ICondition cond, IFlowObject firstItem, IFlowObject lastItem){
		IGatewaySplit split = BPGFactory.createGatewayXorSplit(cond);
		IGatewayJoin join = BPGFactory.createGatewayXorJoin();
		
		this.connectToJoin(source, split, join);
		this.connect(join, firstItem);
		this.connect(lastItem, split);
		split.setSuccessor2(sink);
		sink.setPredecessor(split);
		
	}
	
	@Override
	public void insertIntoWhileLoop(IFlowObject source, IFlowObject sink, ICondition cond, IFlowObject content){
		insertIntoWhileLoop(source, sink, cond, content, content);
	}
	
	@Override
	public IFlowObject getStart(){
		return start;
	}

	@Override
	public IFlowObject getEnd() {
		return end;
	}
}
