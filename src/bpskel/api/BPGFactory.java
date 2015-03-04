package bpskel.api;

import bpskel.bpg.elements.gateway.Condition;
import bpskel.bpg.elements.gateway.GatewayAndJoin;
import bpskel.bpg.elements.gateway.GatewayAndSplit;
import bpskel.bpg.elements.gateway.GatewayXorJoin;
import bpskel.bpg.elements.gateway.GatewayXorSplit;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;


public class BPGFactory {

	static private BPGFactory instance;
	
	private static IProcessEngine engine;

	private BPGFactory(IProcessEngine pe){
		engine = pe;
	}
	
	public static void initialize(IProcessEngine pe){
		if(instance == null){
			instance = new BPGFactory(pe);
		}
	}
	
	public static int executeProcess(IBPG pro){
		return engine.execute(pro);
	}
	
	public static IBPG createBPG(){
		return new BusinessProcessGraph();
	}
	
	public static IGatewaySplit createGatewayAndSplit(){
		return new GatewayAndSplit();
	}
	
	public static IGatewayJoin createGatewayAndJoin(){
		return new GatewayAndJoin();
	}

	public static IGatewaySplit createGatewayXorSplit(ICondition cond){
		return new GatewayXorSplit(cond);
	}	
	
	public static IGatewayJoin createGatewayXorJoin(){
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
	public static IGatewaySplit createGatewayXorSplit(String string,
			IDataContainer lhs, IDataContainer rhs) {
		return createGatewayXorSplit(createCondition(lhs, string, rhs));
	}
	
	/**
	 * Creates a expression which will evaluate at some point.
	 * @param lhs the left hand side of the boolean expression
	 * @param string A boolean expression EXPR which will look like <code>lhs EXPR rhs</code> (e.g. 4 < 5, where "<" is EXPR)
	 * @param rhs the right hand side of the boolean expression
	 * @return a condition
	 */
	public static ICondition createCondition(IDataContainer lhs,
			String string, IDataContainer rhs){
		return new Condition(lhs, string, rhs);
	}

	public static IProcessEngine getDefaultProcessEngine() {
		if(engine == null){
			engine = new SkeletonProcessEngine();
		}
		return engine;
				
	}	
}
