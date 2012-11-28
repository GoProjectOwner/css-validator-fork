// $Id: CssObjectPosition.java,v 1.1 2012-11-28 09:28:11 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#object-position
 */
public class CssObjectPosition extends org.w3c.css.properties.css.CssObjectPosition {

	/**
	 * Create a new CssObjectPosition
	 */
	public CssObjectPosition() {
		value = initial;
	}

	/**
	 * Creates a new CssObjectPosition
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssObjectPosition(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();
		value = CssBackgroundPosition.checkSyntax(expression, ac, getPropertyName());
	}

	public CssObjectPosition(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

