// $Id: CssBreakAfter.java,v 1.4 2012-08-24 07:33:40 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 by Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#column-breaks
 */

public class CssBreakAfter extends org.w3c.css.properties.css.CssBreakAfter {

	static CssIdent auto;
	private static CssIdent[] allowed_values;

	static {
		auto = CssIdent.getIdent("auto");
		String id_values[] = {"auto", "always", "avoid", "left", "right",
				"page", "column", "avoid-page", "avoid-column"};
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
	 * Create a new CssBreakAfter
	 */
	public CssBreakAfter() {
		value = initial;
	}

	/**
	 * Create a new CssBreakAfter
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Incorrect value
	 */
	public CssBreakAfter(ApplContext ac, CssExpression expression,
						 boolean check) throws InvalidParamException {

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

	public CssBreakAfter(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return (auto == value);
	}

}
