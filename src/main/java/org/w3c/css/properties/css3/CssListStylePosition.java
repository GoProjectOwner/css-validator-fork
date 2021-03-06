// $Id: CssListStylePosition.java,v 1.1 2012-11-07 11:34:59 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-lists-20110524/#list-style-position
 */
public class CssListStylePosition extends org.w3c.css.properties.css.CssListStylePosition {

	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"inside", "hanging", "outside"};
		int i = 0;
		allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssListStylePosition
	 */
	public CssListStylePosition() {
		value = initial;
	}


	/**
	 * Set the value of the property<br/>
	 * Does not check the number of values
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssListStylePosition(ApplContext ac, CssExpression expression)
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
	public CssListStylePosition(ApplContext ac, CssExpression expression,
								boolean check) throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		val = expression.getValue();
		expression.getOperator();

		if (val.getType() != CssTypes.CSS_IDENT) {
			throw new InvalidParamException("value", val,
					getPropertyName(), ac);
		}
		CssIdent id = (CssIdent) val;
		if (inherit.equals(id)) {
			value = inherit;
		} else {
			value = getAllowedIdent(id);
			if (value == null) {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
		}
		expression.next();
	}

}
