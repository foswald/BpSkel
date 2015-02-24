package unihh.vsis.bpskel.blockconverter.pattern;

import java.util.List;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.core.AbstractFlowObject;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

/** A component is a concrete representation of a Pattern whithin an BusinessProcess.
 * It contains the reference to aligned Nodes/Components as well as to the 
 * Pattern (i.e. the skeleton) it represents.
 * @author foswald
 *
 */
public class Component extends AbstractFlowObject{
	
	List<IFlowObject> content;
	/** The pattern describing the content */
	IPattern pattern;
	/** The parent component of this */
	Component parent;
	
	public Component(IPattern pattern, IFlowObject...content){
		this.addIncomingFlowObject(content[0].getIncomingFlowObjects().first);
		this.addOutgoingFlowObject(content[content.length-1].getOutgoingFlowObjects().first);
		for(IFlowObject c : content){
			this.content.add(c);
		}
		this.pattern = pattern;
	}	
}
