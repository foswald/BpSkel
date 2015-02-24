package unihh.vsis.bpskel.blockconverter;

import unihh.vsis.bpskel.blockconverter.pattern.Component;
import unihh.vsis.bpskel.blockconverter.pattern.IPattern;
import unihh.vsis.bpskel.blockconverter.pattern.PipePattern;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public class PatternMatcher {
	
	private IPattern[] patterns;

	public PatternMatcher(){
		patterns = new IPattern[1];
		patterns[0] = new PipePattern();
		
	}
	
	public Component matchPattern(IFlowObject f){
		for(IPattern p:patterns) {
			if(p.matchPattern(f)){
				return p.foldToComponent(f);
			}
		}
		throw new Error("Invalid pattern");
	}

}
