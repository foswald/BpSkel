package bpskel.engine.simple;

import java.util.Stack;

import bpskel.api.BusinessProcessGraph;
import bpskel.api.IProcessEngine;
import bpskel.api.ITask;
import bpskel.impl.core.EndElement;
import bpskel.impl.core.IFlowObject;
import bpskel.impl.gateway.GatewayAndJoin;
import bpskel.impl.gateway.GatewayAndSplit;
import bpskel.impl.gateway.GatewayXorSplit;
import bpskel.impl.gateway.IGateway;

public class SimpleProcessEngine implements IProcessEngine{
	
	private static Stack<GatewayAndSplit> visitedAndSplits;

	@Override
	public void execute(BusinessProcessGraph pro) {
		visitedAndSplits = new Stack<>();
		// first find element after start
		IFlowObject f = pro.getStart().getSuccessor();
		run(f);

	}
	
	private static void run(IFlowObject f){
		do{
			// check if we have a simple task and execute
			if(f instanceof ITask){
				((ITask) f).execute();				
			}
			// Process next element
			IFlowObject next = f.getSuccessor();
			
			// check if we have Gateway and might branch
			if(next instanceof IGateway) {
				// Check if we have an Join which implies another branch to process (i.e. AND)
				if(next instanceof GatewayAndJoin){
					// fetch corresponding split
					GatewayAndSplit split = visitedAndSplits.lastElement();
					if(!split.didBranch()){
						// We computed the first branch of an AND split, fetch the second element of the split
						next = split.getSuccessor2();
						split.setBranched();
					}
					else if(split.didBranch()){
						// the second branch arrived, so its safe to proceed
						next = next.getSuccessor();
						visitedAndSplits.pop();
					}
				}
				else if(next instanceof GatewayAndSplit){
					visitedAndSplits.push((GatewayAndSplit) next);
					next = next.getSuccessor();
				}
				
				// next from AND task might be XOR split
				if(next instanceof GatewayXorSplit){
					next = ((GatewayXorSplit)next).getNextValidFlowObject();
				}
							
			}
			f = next;
		}while(!(f instanceof EndElement));
	}

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		return false;
	}
}
