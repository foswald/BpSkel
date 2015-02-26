package bpskel.exceptions;


/**
 * Gets thrown if a sequence of IFlowObjects does not meet an expected pattern.
 * @author foswald
 *
 */
@SuppressWarnings("serial")
public class PatternMismatchException extends Exception {
	
	public PatternMismatchException(){
		super("Pattern error!");	
	}
}
