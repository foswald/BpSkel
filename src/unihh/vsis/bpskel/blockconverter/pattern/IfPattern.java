package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.bpmn.core.IGateway;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorSplit;

public class IfPattern<T> implements IPattern<T> {

	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof GatewayXorSplit){
			IFlowObject first = start.getOutgoingFlowObjects().first;
			IFlowObject second = start.getOutgoingFlowObjects().second;
			// we must have two succeeding objects, both of them must be a component to make folding possible
			
			// either one might be the direct join (in case we only have one task in an XOR), 
			// at least one must be a Component 
			boolean oneEmpty = false;
			if((first instanceof IGateway 
					&& ((IGateway)first).isFlowJoin())
				|| (second instanceof IGateway 
					&& ((IGateway)second).isFlowJoin())){
				oneEmpty = true;
			}
			// one branch has no component, so the other one must have
			if(oneEmpty){
				
			}
		}
		return false;
	}

	@Override
	public Component<T> foldToComponent(IFlowObject start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T createSkeleton(Component<T> start) {
		// TODO Auto-generated method stub
		return null;
	}

}
