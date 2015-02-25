package unihh.vsis.bpskel.bpmn.api;

import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.GatewayJoin;
import unihh.vsis.bpskel.bpmn.core.GatewaySplit;
import unihh.vsis.bpskel.bpmn.core.ICondition;
import unihh.vsis.bpskel.bpmn.core.IProcessEngine;
import unihh.vsis.bpskel.bpmn.core.StartElement;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayAndJoin;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayAndSplit;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorJoin;
import unihh.vsis.bpskel.bpmn.impl.gateway.GatewayXorSplit;


public class BPMNFactory {

	static private BPMNFactory instance;
	
	// only one start/end element
	private static StartElement start;
	private static EndElement end;
	
	private static IProcessEngine engine;

	private BPMNFactory(IProcessEngine pe){
		start = new StartElement();
		end = new EndElement();
		engine = pe;
	}
	
	public static void initialize(IProcessEngine pe){
		if(instance == null){
			instance = new BPMNFactory(pe);
		}
	}
	
	public static IProcessEngine getProcessEngine(){
		return engine;
	}
	
	public static StartElement getStartElement(){
		return start;
	}
	
	public static EndElement getEndElement(){
		return end;
	}
	
	public static GatewaySplit createGatewayAndSplit(){
		return new GatewayAndSplit();
	}
	
	public static GatewayJoin createGatewayAndJoin(){
		return new GatewayAndJoin();
	}

	public static GatewaySplit createGatewayXorSplit(ICondition cond){
		return new GatewayXorSplit(cond);
	}
	
	
	
	public static GatewayJoin createGatewayXorJoin(){
		return new GatewayXorJoin();
	}

	/**
	 * Create a new XOR split with a condition which will be evaluated when the gateway is reached. <br>
	 * If the condition evaluates to true, the first succeeding element will be processed next. <br>
	 * If the condition evaluates to false, the second succeeding element will be processed next.
	 * @param string A boolean expression EXPR which will look like <code>lhs EXPR rhs</code> (e.g. 4 < 5, where "<" is EXPR)
	 * @param lhs the left hand side of the boolean expression
	 * @param rhs the right hand side of the boolean expression
	 * @return A XOR gateway evaluating the given expression when processed.
	 */
	public static GatewaySplit createGatewayXorSplit(String string,
			IDataContainer lhs, IDataContainer rhs) {
		return createGatewayXorSplit(new Condition(string, lhs, rhs));
	}
	
	public static ITask createTask(ITask task){
		return task;
	}

	
}
