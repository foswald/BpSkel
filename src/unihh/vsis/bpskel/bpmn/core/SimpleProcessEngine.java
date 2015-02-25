package unihh.vsis.bpskel.bpmn.core;

import java.util.Stack;

import unihh.vsis.bpskel.bpmn.api.BusinessProcess;
import unihh.vsis.bpskel.bpmn.api.ITask;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayAndJoin;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayAndSplit;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorSplit;

public class SimpleProcessEngine implements IProcessEngine{
	
	private static Stack<GatewayAndSplit> visitedAndSplits;

	@Override
	public void execute(BusinessProcess pro) {
		visitedAndSplits = new Stack<>();
		// first find element after start
		IFlowObject f = pro.getStart().getOutgoingFlowObjects().first;
		run(f);

	}
	
	private static void run(IFlowObject f){
		do{
			// check if we have a simple task and execute
			if(f instanceof ITask){
				((ITask) f).execute();				
			}
			// Process next element
			IFlowObject next = f.getOutgoingFlowObjects().first;
			
			// check if we have Gateway and might branch
			if(next instanceof IGateway) {
				// Check if we have an Join which implies another branch to process (i.e. AND)
				if(next instanceof GatewayAndJoin){
					// fetch corresponding split
					GatewayAndSplit split = visitedAndSplits.lastElement();
					if(!split.didBranch()){
						// We computed the first branch of an AND split, fetch the second element of the split
						next = split.getOutgoingFlowObjects().second;
						split.setBranched();
					}
					else if(split.didBranch()){
						// the second branch arrived, so its safe to proceed
						next = next.getOutgoingFlowObjects().first;
						visitedAndSplits.pop();
					}
				}
				else if(next instanceof GatewayAndSplit){
					visitedAndSplits.push((GatewayAndSplit) next);
					next = next.getOutgoingFlowObjects().first;
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
