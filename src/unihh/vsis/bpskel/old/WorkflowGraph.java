package unihh.vsis.bpskel.old;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Graph nodes are stored in a plain ArrayList containing Nodes.
 * The Edges will be computed on the fly from relationships of adjacent nodes.
 * @author foswald
 *
 */

public class WorkflowGraph<T>{
	
	private ArrayList<Node<T>> nodeContainer;
	
	public WorkflowGraph(){
		this.nodeContainer = new ArrayList<>();
	}
	
	public void addEdge(DirectedEdge<T> e){
		if(!this.containsNode(e.source))	this.addNode(e.source);
		if(!this.containsNode(e.destination))	this.addNode(e.destination);
	}
	
	public void addNode(Node<T> n){
		this.nodeContainer.add(n);
	}
	
	public void connectNodes(Node<T> source, Node<T> sink){
		source.addSucceedingNode(sink);
		sink.addPrecedingNode(source);
	}
	
	public List<Node<T>> getSucceedingNodes(Node<T> n){
		return n.getSucceedingNodes();
	}
	

	/**
	 * Checks if the node exists in the graph
	 * @param n
	 * @return
	 */
	boolean containsNode(Node<T> n){
		return this.nodeContainer.contains(n);
	}
	
	boolean validate(){
		boolean valid = true;
		
		Stack<Node<T>> nodeStack = new Stack<>();
		for(Node<T> n:this.nodeContainer){
			if(n.isJoinNode()){
				nodeStack.push(n);
			}
			else if(n.isSplitNode()){
				nodeStack.pop();
			}
		}
		
		valid = nodeStack.size() == 0;
		
		
		return valid;
	}
}
