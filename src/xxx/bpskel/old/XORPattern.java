package xxx.bpskel.old;


public class XORPattern<T> extends WorkflowGraph<T> {

	private XORPattern(){
		super();
		
		// create arbitrary node to add as placeholder
		Node<T> arbitraryNode = new Node<>(NodeType.UNDEFINED);
		// Split has two edges to arbitrary Nodes
		Node<T> n1 = new Node<>(NodeType.XOR_SPLIT);		
		n1.addSucceedingNode(arbitraryNode);
		n1.addSucceedingNode(arbitraryNode);
		
		// The join does have exactly one arbitrary outgoing node and two preceeding nodes
		Node<T> n2 = new Node<>(NodeType.XOR_JOIN);
		n2.addSucceedingNode(arbitraryNode);
		n2.addPrecedingNode(arbitraryNode);
		n2.addPrecedingNode(arbitraryNode);
		this.addNode(n1);
		this.addNode(n2);
	}
}
