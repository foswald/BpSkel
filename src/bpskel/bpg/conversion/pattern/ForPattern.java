/**
 * 
 */
package bpskel.bpg.conversion.pattern;

import bpskel.api.IForTask;
import bpskel.bpg.elements.core.IFlowObject;

/**
 * @author foswald
 *
 */
public class ForPattern implements IPattern {

	/**
	 * @see bpskel.bpg.conversion.pattern.IPattern#matchPattern(bpskel.bpg.elements.core.IFlowObject)
	 */
	@Override
	public boolean matchPattern(IFlowObject start) {
		if(start instanceof IForTask){
			return true;
		}
		return false;
	}

	/**
	 * @see bpskel.bpg.conversion.pattern.IPattern#getEndElement(bpskel.bpg.elements.core.IFlowObject)
	 */
	@Override
	public IFlowObject getEndElement(IFlowObject start) {
		return start;
	}

	/**
	 * @see bpskel.bpg.conversion.pattern.IPattern#getPatternType()
	 */
	@Override
	public PatternType getPatternType() {
		return PatternType.FOR;
	}

}
