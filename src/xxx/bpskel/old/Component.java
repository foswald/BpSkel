package xxx.bpskel.old;

import java.util.List;

import bpskel.bpg.conversion.pattern.IPattern;
import bpskel.bpg.elements.core.FlowObject;
import bpskel.bpg.elements.core.IFlowObject;

/** A component is a concrete representation of a Pattern whithin an BusinessProcess.
 * It contains the reference to aligned Nodes/Components as well as to the 
 * Pattern (i.e. the skeleton) it represents.
 * @author foswald
 *
 */
public class Component extends FlowObject{
	
	List<IFlowObject> content;
	/** The pattern describing the content */
	IPattern pattern;
	/** The parent component of this */
	Component parent;
	
//	public Component(IPattern pattern, IFlowObject...content){
//		this.addPredecessor(content[0].getPredecessor().first);
//		this.addSuccessor(content[content.length-1].getSuccessor().first);
//		for(IFlowObject c : content){
//			this.content.add(c);
//		}
//		this.pattern = pattern;
//	}	
}
