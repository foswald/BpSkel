package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.exceptions.PatternMismatchException;

public class PatternMatcher {
	
	private static final int NUM_PATTERNS = 4;
	
	private IPattern[] patterns;

	public PatternMatcher(){
		patterns = new IPattern[NUM_PATTERNS];
		patterns[0] = new SeqPattern();
		patterns[1] = new PipePattern();
		patterns[2] = new IfPattern();
		patterns[2] = new ForkPattern();
		
		
	}
	
	public boolean isValidPattern(IFlowObject f){
			try {
				this.matchPattern(f);
				return true;
			} catch (PatternMismatchException e) {
				return false;
			}
	}
	
	public IPattern matchPattern(IFlowObject f) throws PatternMismatchException{
		for(IPattern p:patterns) {
			if(p.matchPattern(f)){
				return p;
			}
		}
		throw new PatternMismatchException();
	}

}
