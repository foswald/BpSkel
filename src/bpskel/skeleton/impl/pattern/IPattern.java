package bpskel.skeleton.impl.pattern;

import bpskel.bpg.impl.core.IFlowObject;
import bpskel.exceptions.PatternMismatchException;

public interface IPattern {
	
	/**
	 * Checks if the sequence of FlowObjects starting with <code>start</code> matches the current pattern.
	 * @param start
	 * @return <code>true</code> if the sequence matches the pattern, <code>false</code> otherwise.
	 */
	boolean matchPattern(IFlowObject start);
	
	/**
	 * Returns the last element of the pattern (must be a single node!)
	 * @param start
	 * @return end element of current pattern
	 * @throws PatternMismatchException 
	 */
	IFlowObject getEndElement(IFlowObject start);
	
	/**
	 * For type determination of the current pattern.
	 * @return the type of the current pattern
	 */
	PatternType getPatternType();
}
