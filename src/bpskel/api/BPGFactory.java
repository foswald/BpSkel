package bpskel.api;

import bpskel.bpg.elements.gateway.Condition;
import bpskel.bpg.elements.gateway.GatewayAndJoin;
import bpskel.bpg.elements.gateway.GatewayAndSplit;
import bpskel.bpg.elements.gateway.GatewayXorJoin;
import bpskel.bpg.elements.gateway.GatewayXorSplit;
import bpskel.engine.skeleton.api.SkeletonProcessEngine;

/**
 * This Factory provides methods to create BPGs and its elements for execution.
 * @author foswald
 * @note you must call <code>BPGFactory.initialize()</code> before executing a BPG.
 * @see bpskel.api.BusinessProcessGraph
 */
public class BPGFactory {

	static private BPGFactory instance;
	
	private static IProcessEngine engine;

	private BPGFactory(IProcessEngine pe){
		engine = pe;
	}
	
	/**
	 * Initialize the BPGFactory with a IProcessEngine.
	 * You can use BPGFactory.getDefaultProcessingEngine() to retrieve the default processing engine.
	 * @param pe the processing enine to use for processing the bpg.
	 */
	public static void initialize(IProcessEngine pe){
		if(instance == null){
			instance = new BPGFactory(pe);
		}
	}
	
	/**
	 * Executes the BPG using the previously set IProcessingEngine 
	 * @param pro
	 * @return
	 */
	public static int executeProcess(IBPG pro){
		return engine.execute(pro);
	}
	
	/**
	 * Returns an IBPG for BPG Modeling.
	 * @return
	 */
	public static IBPG createBPG(){
		return new BusinessProcessGraph();
	}
	
	/**
	 * Returns a GatewayANDSplit, for parallelization of two succeeding tasks.
	 * @see bpskel.api.IBPG#connectFromSplit(IGatewaySplit, bpskel.bpg.elements.core.IFlowObject, bpskel.bpg.elements.core.IFlowObject)
	 * @return an GatewayANDSplit
	 */
	public static IGatewaySplit createGatewayAndSplit(){
		return new GatewayAndSplit();
	}
	
	/**
	 * Returns a GatewayANDJoin, for joining previously split branches.
	 * @see bpskel.api.IBPG#connectToJoin(bpskel.bpg.elements.core.IFlowObject, bpskel.bpg.elements.core.IFlowObject, IGatewayJoin)
	 * @return an GatewayANDSplit
	 */
	public static IGatewayJoin createGatewayAndJoin(){
		return new GatewayAndJoin();
	}

	/**
	 * Returns a GatewayXorSplit which will route to one of two succeeding tasks depending on the result of <code>cond</code>
	 * @param cond the condition which will decide on which task to process next.
	 * @return A GatewayXorSplit
	 * @see bpskel.api.IBPG#connectFromSplit(IGatewaySplit, bpskel.bpg.elements.core.IFlowObject, bpskel.bpg.elements.core.IFlowObject)
	 * @see bpskel.api.BPGFactory#createCondition(IDataContainer, String, IDataContainer)
	 */
	public static IGatewaySplit createGatewayXorSplit(ICondition cond){
		return new GatewayXorSplit(cond);
	}
	
	/**
	 * The Gateway XoRJoin merges previously split branches, which have been split by an GatewayXorSplit.
	 * @return a GatewayXorJoin
	 * @see bpskel.api.IBPG#connectToJoin(bpskel.bpg.elements.core.IFlowObject, bpskel.bpg.elements.core.IFlowObject, IGatewayJoin)
	 */
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

	/**
	 * @return The default ProcessingEngine to use when executing a BPG
	 */
	public static IProcessEngine getDefaultProcessEngine() {
		if(engine == null){
			engine = new SkeletonProcessEngine();
		}
		return engine;
				
	}	
}
