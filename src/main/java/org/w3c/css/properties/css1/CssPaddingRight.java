// $Id: CssPaddingRight.java,v 1.6 2012-09-25 20:10:15 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS1-20080411/#padding-right
 */
public class CssPaddingRight extends org.w3c.css.properties.css.CssPaddingRight {

	/**
	 * Create a new CssPaddingRight
	 */
	public CssPaddingRight() {
	}

	/**
	 * Set the value of the property<br/>
	 * Does not check the number of values
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssPaddingRight(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Set the value of the property
	 *
	 * @param expression The expression for this property
	 * @param check      set it to true to check the number of values
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssPaddingRight(ApplContext ac, CssExpression expression,
						   boolean check) throws InvalidParamException {
		setByUser();
		value = CssPadding.checkValue(ac, expression, check, this);
	}
}
