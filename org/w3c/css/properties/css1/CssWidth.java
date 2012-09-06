// $Id: CssWidth.java,v 1.7 2012-09-06 12:37:56 ylafon Exp $
//
// (c) COPYRIGHT MIT, ERCIM and Keio University
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS1-20080411/#width
 */
public class CssWidth extends org.w3c.css.properties.css.CssWidth {

	/**
	 * Create a new CssWidth
	 */
	public CssWidth() {
	}

	/**
	 * Create a new CssWidth.
	 *
	 * @param expression The expression for this property
	 * @throws InvalidParamException Values are incorrect
	 */
	public CssWidth(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		CssValue val = expression.getValue();

		setByUser();

		switch (val.getType()) {
			case CssTypes.CSS_IDENT:
				CssIdent ident = (CssIdent) val;
				if (auto.equals(val)) {
					value = auto;
				} else {
					throw new InvalidParamException("unrecognize", ac);
				}
				break;
			case CssTypes.CSS_NUMBER:
				val = ((CssNumber) val).getLength();
			case CssTypes.CSS_LENGTH:
				CssLength l = (CssLength) val;
				if (!l.isPositive()) {
					throw new InvalidParamException("negative-value",
							val.toString(), ac);
				}
				value = l;
				break;
			case CssTypes.CSS_PERCENTAGE:
				CssPercentage p = (CssPercentage) val;
				if (!p.isPositive()) {
					throw new InvalidParamException("negative-value",
							val.toString(), ac);
				}
				value = p;
				break;
			default:
				throw new InvalidParamException("value", val, getPropertyName(), ac);
		}
		expression.next();
	}

	public CssWidth(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property is a default value.
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return value == auto;
	}

}
