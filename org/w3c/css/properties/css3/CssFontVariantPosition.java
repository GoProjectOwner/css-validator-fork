// $Id: CssFontVariantPosition.java,v 1.2 2012-08-19 17:28:25 ylafon Exp $
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

import java.util.HashMap;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#propdef-font-variant-position
 */
public class CssFontVariantPosition extends org.w3c.css.properties.css.CssFontVariantPosition {

	public static final String[] _allowedValues = {"normal", "sub",
			"super", "ordinal"};

	public static final HashMap<String, CssIdent> allowedValues;

	static {
		allowedValues = new HashMap<String, CssIdent>(_allowedValues.length);
		for (String s : _allowedValues) {
			allowedValues.put(s, CssIdent.getIdent(s));
		}
	}

	/**
	 * Create a new CssFontVariantPosition
	 */
	public CssFontVariantPosition() {
		value = initial;
	}

	/**
	 * Creates a new CssFontVariantPosition
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariantPosition(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			if (inherit.equals(ident)) {
				value = inherit;
			} else {
				value = allowedValues.get(ident.toString().toLowerCase());
				if (value == null) {
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				}
			}
		} else {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}
		expression.next();
	}

	public CssFontVariantPosition(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

