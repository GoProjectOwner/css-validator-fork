// $Id: CssNavRight.java,v 1.4 2012-10-18 09:55:39 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-ui-20120117/#nav-right0
 * @see org.w3c.css.properties.css3.CssNavUp
 */
public class CssNavRight extends org.w3c.css.properties.css.CssNavRight {

	public static final CssIdent auto = CssIdent.getIdent("auto");

	/**
	 * Create a new CssNavRight
	 */
	public CssNavRight() {
		value = initial;
	}

	/**
	 * Create a new CssNavRight
	 *
	 * @param ac         The context
	 * @param expression The expression for this property
	 * @param check      true will test the number of parameters
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssNavRight(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {

		setByUser();
		value = CssNavUp.checkValues(ac, expression, check, this);
	}

	/**
	 * Create a new CssNavRight
	 *
	 * @param ac,        the Context
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssNavRight(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	public boolean isDefault() {
		return (auto == value) || (auto == initial);
	}
}
