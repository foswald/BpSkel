package unihh.vsis.bpskel.old;

public class DirectedEdge<T> {
	public Node<T> source;
	public Node<T> destination;	
	
	public DirectedEdge(Node<T> source, Node<T> destination){
		this.source = source;
		this.destination = destination;
	}
}
