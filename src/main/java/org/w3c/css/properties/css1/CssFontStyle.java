// $Id: CssFontStyle.java,v 1.5 2012-08-04 21:17:05 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS1-20080411/#font-style
 */
public class CssFontStyle extends org.w3c.css.properties.css.CssFontStyle {

	static final String[] _allowed_values = {"italic", "normal", "oblique"};
	static final ArrayList<CssIdent> allowed_values;

	static {
		allowed_values = new ArrayList<CssIdent>(3);
		for (String s : _allowed_values) {
			allowed_values.add(CssIdent.getIdent(s));
		}
	}

	/**
	 * Create a new CssFontStyle
	 */
	public CssFontStyle() {
	}

	/**
	 * Creates a new CssFontStyle
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontStyle(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		val = expression.getValue();
		expression.getOperator();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			int pos = allowed_values.indexOf(ident);
			if (pos < 0) {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
			value = allowed_values.get(pos);
		} else {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}
		expression.next();
	}

	public CssFontStyle(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

