// $Id: CssOutlineOffset.java,v 1.4 2012-10-16 20:44:01 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-ui-20120117/#outline-offset0
 */
public class CssOutlineOffset extends org.w3c.css.properties.css.CssOutlineOffset {

	/**
	 * Create a new CssOutlineOffset
	 */
	public CssOutlineOffset() {
	}

	/**
	 * Creates a new CssOutlineOffset
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssOutlineOffset(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		CssValue val = expression.getValue();

		setByUser();

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
				val.getLength();
			case CssTypes.CSS_LENGTH:
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					value = inherit;
					break;
				}
			default:
				throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
		}
		expression.next();
	}

	public CssOutlineOffset(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

