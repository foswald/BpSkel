package unihh.vsis.bpskel.old;

import java.util.List;

/**
 * This class represents a node of a well formed graph.
 * A Node has no more than two preceding or succeeding Nodes.
 * A well formed Graph should consist of nodes which have either one preceding and two succeeding Nodes if it is
 * a SPLIT node or two preceding and one succeeding Nodes if it is a JOIN node.
 * @author foswald
 *
 */
public class Node<T> {
	/**
	 * 0-2 nodes directly connected to this node where this is the source of the Edge	 */
	List<Node<T>> succeedingNodes;
	
	/**
	 * 0-2 nodes directly connected to this node where this is the sink of the Edge	*/
	List<Node<T>> precedingNodes;
	
	NodeType nodeType;
	
	T companion;

	public static <T> Node<T> ArbitraryNode(){
		return new Node<T>(NodeType.UNDEFINED);
	}
	
	public Node(NodeType type) {
		this.nodeType = type;
	}
	
	public Node(NodeType type, T companion) {
		this.nodeType = type;
		this.companion = companion;
	}
	
	public Node(NodeType type, List<Node<T>> precedingNodes, List<Node<T>> succeedingNode) {
		this.nodeType = type;
		this.precedingNodes = precedingNodes;
		this.succeedingNodes = succeedingNode;
	}
	
	public NodeType getNodeType(){
		return this.nodeType;
	}
	
	public void addSucceedingNode(Node<T> node){
		this.succeedingNodes.add(node);
	}
	
	public List<Node<T>> getSucceedingNodes(){
		return this.succeedingNodes;
	}
	
	public int numSucceedingNodes(){
		return this.succeedingNodes.size();
	}
	
	public void addPrecedingNode(Node<T> node){
		this.precedingNodes.add(node);
	}

	public List<Node<T>> getPrecedingNodes(){
		return this.precedingNodes;
	}
	
	public int numPrecedingNodes(){
		return this.precedingNodes.size();
	}
	
	public boolean isSplitNode(){
		switch(this.nodeType){
			case AND_SPLIT: return true;
			case XOR_SPLIT: return true;
			case OR_SPLIT: 	return true;
			default: 		return false;
		}
	}
	
	public boolean isJoinNode(){
		switch(this.nodeType){
			case AND_JOIN: 	return true;
			case XOR_JOIN: 	return true;
			case OR_JOIN: 	return true;
			default: 		return false;
		}
	}
	

	
	public boolean isEqual(Node<T> n){
		// num of adjacent nodes and type must match
		return (this.nodeType == n.nodeType &&
				this.numPrecedingNodes() == n.numPrecedingNodes() &&
				this.numSucceedingNodes() == n.numSucceedingNodes() &&
				n.getContainedElement().equals(this.getContainedElement())) ? true:false;
	}
	
	public T getContainedElement(){
		return companion;
	}
	
	public void setContainedElement(T element){
		this.companion = element;
	}
}
