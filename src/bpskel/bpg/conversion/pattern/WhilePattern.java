package bpskel.bpg.conversion.pattern;

import bpskel.api.IGatewayJoin;
import bpskel.api.IGatewaySplit;
import bpskel.bpg.elements.core.IFlowObject;

public class WhilePattern implements IPattern {

	@Override
	public boolean matchPattern(IFlowObject start) {
		boolean matches = false;
		if(start instanceof IGatewayJoin){
			IGatewayJoin join = (IGatewayJoin) start;
			
			if(join.getSuccessor() instanceof ProxyTask){
				ProxyTask proxy = (ProxyTask) join.getSuccessor();
				if(proxy.getSuccessor() instanceof IGatewaySplit){
					IFlowObject joinPred1 = join.getPredecessor();
					IFlowObject joinPred2 = join.getPredecessor2();
					matches = proxy.getSuccessor().equals(joinPred1) || proxy.getSuccessor().equals(joinPred2);
				}
			}
		}
		return matches;
	}

	@Override
	public IFlowObject getEndElement(IFlowObject start) {
		if(this.matchPattern(start)){
			return start.getSuccessor().getSuccessor();
		}
		return null;
	}

	@Override
	public PatternType getPatternType() {
		return PatternType.WHILE;
	}

}
