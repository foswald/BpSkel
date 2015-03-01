/**
 * 
 */
package bpskel.engine.skeleton.impl.pattern;

import bpskel.bpg.impl.core.IFlowObject;
import bpskel.bpg.impl.core.IForTask;

/**
 * @author foswald
 *
 */
public class ForPattern implements IPattern {

	/**
	 * @see bpskel.engine.skeleton.impl.pattern.IPattern#matchPattern(bpskel.bpg.impl.core.IFlowObject)
	 */
	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof IForTask){
			return true;
		}
		return false;
	}

	/**
	 * @see bpskel.engine.skeleton.impl.pattern.IPattern#getEndElement(bpskel.bpg.impl.core.IFlowObject)
	 */
	@Override
	public IFlowObject getEndElement(IFlowObject start) {
		return start;
	}

	/**
	 * @see bpskel.engine.skeleton.impl.pattern.IPattern#getPatternType()
	 */
	@Override
	public PatternType getPatternType() {
		return PatternType.FOR;
	}

}
