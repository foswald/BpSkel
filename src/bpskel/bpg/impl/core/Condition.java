package bpskel.bpg.impl.core;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bpskel.bpg.api.ICondition;
import bpskel.bpg.api.IDataContainer;

public class Condition implements ICondition {
	
	public static String GREATER_THAN = ">";
	public static String LESS_THAN = "<";
	public static String EQUAL = "=";
	public static String OR = "||";
	public static String AND = "&&";
	public static String NOT = "!";


	private IDataContainer lhs;
	private IDataContainer rhs;
	private String expr;
  
	public Condition(String booleanExpression, IDataContainer leftHandDataRef, IDataContainer rightHandDataRef) {
		this.expr = booleanExpression;
		this.lhs = leftHandDataRef;
		this.rhs = rightHandDataRef;
	}
	
	private String convertToString(IDataContainer dataRef){
		if(dataRef.getData().getClass().isPrimitive()){
			return String.valueOf(dataRef.getData());
		}
		else if(dataRef.getData() instanceof Integer){
			return Integer.toString((int) dataRef.getData());
		}
		// TODO assume String for now (throws if invalid)
		else if(dataRef.getData() instanceof String){
			return (String)dataRef.getData();
		}
		else {
			// TODO find better errorcode
			throw new Error("dataRef conversion error. String or primitive expected!");
		}
	}

	@Override
	public boolean evaluate() {
		boolean fullfilled = false;
		String expression = null;
        try {

    		expression = this.convertToString(lhs) + expr + this.convertToString(rhs);
    		System.out.println(expression);
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("JavaScript");

            Object result = se.eval(expression);

            if(result instanceof Boolean){
            	fullfilled = (boolean)result;
            }
            else{
            	throw new Error("Error evaluating expression!");
            }

        } catch (ScriptException e) {

            System.out.println("Invalid Expression: " + expression);
            e.printStackTrace();

        }
        return fullfilled;
	}

	@Override
	public String getDescription() {
		return "Expression: "+this.expr ;
	}
}
