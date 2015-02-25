package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.core.AbstractFlowObject;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

/**
 * This class is used during transformation of the BPG.
 * It represents a transformed Pattern by holding the skeleton and connecting nodes
 * @author foswald
 *
 */
public class ProxyTask extends AbstractFlowObject{
	
	ISkeleton skelRef;
	
	public ProxyTask(ISkeleton skelRef, IFlowObject preceeding, IFlowObject succeeding){
		this.skelRef = skelRef;
		
		super.setDescription("ProxyTask");		
		super.addIncomingFlowObject(preceeding);
		super.addOutgoingFlowObject(succeeding);
	}
}
