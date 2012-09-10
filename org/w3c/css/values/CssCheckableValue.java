// $Id: CssCheckableValue.java,v 1.3 2012-09-10 17:04:58 ylafon Exp $
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

public abstract class CssCheckableValue extends CssValue {

	abstract boolean isPositive();

	abstract void checkPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException;

	abstract boolean isStrictlyPositive();

	abstract void checkStrictPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException;


	public boolean isInteger() {
		return false;
	}

	/**
	 * check if the value is an integer
	 * @param ac the validation context
	 * @param property the property the value is defined in
	 * @throws InvalidParamException
	 */
	public void checkInteger(ApplContext ac, CssProperty property)
			throws InvalidParamException {
	}
}
