// $Id: CssTextJustify.java,v 1.4 2012-08-31 10:04:08 ylafon Exp $
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
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#text-justify0
 */
public class CssTextJustify extends org.w3c.css.properties.css.CssTextJustify {

	private static CssIdent[] allowed_values;

	static {
		String id_values[] = {"auto", "none", "inter", "word", "inter",
				"ideograph", "inter", "cluster", "distribute", "kashida"};
		allowed_values = new CssIdent[id_values.length];
		int i = 0;
		for (String s : id_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static CssIdent getMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssTextJustify
	 */
	public CssTextJustify() {
		value = initial;
	}

	/**
	 * Creates a new CssTextJustify
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssTextJustify(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();
		CssValue val = expression.getValue();

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		if (val.getType() != CssTypes.CSS_IDENT) {
			throw new InvalidParamException("value",
					expression.getValue(),
					getPropertyName(), ac);
		}
		// ident, so inherit, or allowed value
		if (inherit.equals(val)) {
			value = inherit;
		} else {
			val = getMatchingIdent((CssIdent) val);
			if (val == null) {
				throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
			}
			value = val;
		}
		expression.next();
	}

	public CssTextJustify(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

