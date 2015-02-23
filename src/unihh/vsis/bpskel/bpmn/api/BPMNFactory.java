package unihh.vsis.bpskel.bpmn.api;

import unihh.vsis.bpskel.bpmn.core.EndElement;
import unihh.vsis.bpskel.bpmn.core.IProcessEngine;
import unihh.vsis.bpskel.bpmn.core.ICondition;
import unihh.vsis.bpskel.bpmn.core.IGateway;
import unihh.vsis.bpskel.bpmn.core.SimpleProcessEngine;
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
	
	private BPMNFactory(){
		start = new StartElement();
		end = new EndElement();
		engine = new SimpleProcessEngine();
	}
	
	public static void initialize(){
		if(instance == null){
			instance = new BPMNFactory();
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
	
	public static IGateway createGatewayAndSplit(){
		return new GatewayAndSplit();
	}
	
	public static IGateway createGatewayAndJoin(){
		return new GatewayAndJoin();
	}

	public static IGateway createGatewayXorSplit(ICondition cond){
		return new GatewayXorSplit(cond);
	}
	
	
	
	public static IGateway createGatewayXorJoin(){
		return new GatewayXorJoin();
	}

	/**
	 * Create a new XOR split with a condition which will be evaluated when the gateway is reached. <br>
	 * If the condition evaluates to true, the first succeeding element will be processed next. <br>
	 * If the condition evaluates to false, the second succeeding element will be processed next.
	 * @param string A boolean expression EXPR which will look like <code>lhs EXPR rhs</code> (e.g. 4 < 5)
	 * @param lhs the left hand side of the boolean expression
	 * @param rhs the right hand side of the boolean expression
	 * @return A XOR gateway evaluating the given expression when processed.
	 */
	public static IGateway createGatewayXorSplit(String string,
			IDataContainer lhs, IDataContainer rhs) {
		return createGatewayXorSplit(new Condition(string, lhs, rhs));
	}
	
	public static ITask createTask(ITask task){
		return task;
	}

	
}
