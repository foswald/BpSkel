package unihh.vsis.bpskel.blockconverter.pattern;

import unihh.vsis.bpskel.bpmn.core.IFlowObject;
import unihh.vsis.bpskel.exceptions.PatternMismatchException;

public class PatternMatcher {
	
	private IPattern[] patterns;

	public PatternMatcher(){
		patterns = new IPattern[1];
		patterns[0] = new PipePattern();
		
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
