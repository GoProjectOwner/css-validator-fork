// $Id: CssAnimationDuration.java,v 1.1 2012-10-08 08:46:53 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-animations-20120403/#animation-duration
 */
public class CssAnimationDuration extends org.w3c.css.properties.css.CssAnimationDuration {

	/**
	 * Create a new CssAnimationDuration
	 */
	public CssAnimationDuration() {
		value = initial;
	}

	/**
	 * Creates a new CssAnimationDuration
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssAnimationDuration(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();

		CssValue val;
		char op;
		ArrayList<CssValue> values = new ArrayList<CssValue>();
		boolean gotinherit = false;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_TIME:
					CssTime t = val.getTime();
					t.warnPositiveness(ac, this);
					values.add(val);
					break;
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						gotinherit = true;
						values.add(inherit);
						break;
					}
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			expression.next();
			if (!expression.end() && (op != COMMA)) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
		if (gotinherit && values.size() > 1) {
			throw new InvalidParamException("value",
					inherit.toString(),
					getPropertyName(), ac);
		}
		value = (values.size() == 1) ? values.get(0) : new CssLayerList(values);
	}

	public CssAnimationDuration(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

