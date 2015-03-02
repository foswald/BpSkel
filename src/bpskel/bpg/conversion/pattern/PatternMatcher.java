package bpskel.bpg.conversion.pattern;

import bpskel.bpg.elements.core.IFlowObject;

public class PatternMatcher {
	
	private static final int NUM_PATTERNS = 8;
	
	private IPattern[] patterns;

	public PatternMatcher(){
		patterns = new IPattern[NUM_PATTERNS];
		patterns[0] = new SeqPattern();
		patterns[1] = new PipePattern();
		patterns[2] = new IfPattern();
		patterns[3] = new ForPattern();
		patterns[4] = new WhilePattern();
		patterns[5] = new ForkPattern();
		patterns[6] = new MapPattern();
		patterns[7] = new DCPattern();
		
	}
	
	public boolean isValidPattern(IFlowObject f){
		if(this.matchPattern(f)!=null) {
				return true;
		}
		
		return false;			
	}
	
	public IPattern matchPattern(IFlowObject f){
		for(IPattern p:patterns) {
			if(p.matchPattern(f)){
				return p;
			}
		}
		return null;
	}

}
