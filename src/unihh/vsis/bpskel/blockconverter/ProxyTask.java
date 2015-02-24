package unihh.vsis.bpskel.blockconverter;

import unihh.vsis.bpskel.api.skeleton.ISkeleton;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

/**
 * This class is used during transformation of the BPG.
 * It represents a transformed Pattern by holding the skeleton and connecting nodes
 * @author foswald
 *
 */
public class ProxyTask {
	
	ISkeleton skelRef;
	IFlowObject preceeding;
	IFlowObject succeeding;
	
	public ProxyTask(ISkeleton skelRef, IFlowObject preceeding, IFlowObject succeeding){
		this.skelRef = skelRef;
		this.preceeding = preceeding;
		this.succeeding = succeeding;
	}
}
