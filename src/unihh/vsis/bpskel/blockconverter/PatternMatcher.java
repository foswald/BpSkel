package unihh.vsis.bpskel.blockconverter;

import unihh.vsis.bpskel.blockconverter.pattern.Component;
import unihh.vsis.bpskel.blockconverter.pattern.PipePattern;
import unihh.vsis.bpskel.bpmn.core.IFlowObject;

public class PatternMatcher<T> {
	
	private PatternMatcher<T> instance;
	private PipePattern<T> seq = new PipePattern<T>();
		
	private PatternMatcher(){
		seq = new PipePattern<T>();
		this.instance = new PatternMatcher<T>();
		
	}
	
	public PatternMatcher<T> getInstance() {
		if(instance == null){
			instance = new PatternMatcher<T>();
		}
		return instance;
	}

	public Component<T> matchPattern(IFlowObject f){
		if(seq.matchPattern(f)){
			return seq.foldToComponent(f);
		}
		else return null;
	}

}
