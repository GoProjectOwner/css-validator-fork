// $Id: CssBackgroundSize.java,v 1.6 2012-08-28 20:31:17 ylafon Exp $
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-size
 */
public class CssBackgroundSize extends org.w3c.css.properties.css.CssBackgroundSize {

	private static CssIdent auto;
	private static CssIdent[] allowed_values;

	static {
		auto = CssIdent.getIdent("auto");
		allowed_values = new CssIdent[3];
		allowed_values[0] = auto;
		allowed_values[1] = CssIdent.getIdent("cover");
		allowed_values[2] = CssIdent.getIdent("contain");
	}

	public static boolean isMatchingIdent(CssIdent ident) {
		return (getMatchingIdent(ident) != null);
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
	 * Create a new CssBackgroundSize
	 */
	public CssBackgroundSize() {
		value = auto;
	}

	/**
	 * Create a new CssBackgroundSize
	 *
	 * @param ac         The context
	 * @param expression The expression for this property
	 * @param check      if arguments count must be checked.
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Values are incorrect
	 */
	public CssBackgroundSize(ApplContext ac, CssExpression expression,
							 boolean check) throws InvalidParamException {
		ArrayList<CssValue> values = new ArrayList<CssValue>();
		char op;
		CssValue val;
		CssValueList vl = null;
		boolean is_complete = true;

		setByUser();

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					val = ((CssNumber) val).getLength();
				case CssTypes.CSS_LENGTH:
				case CssTypes.CSS_PERCENTAGE:
					// per spec only non-negative values are allowed
					float f = ((Float) val.get()).floatValue();
					if (f < 0) {
						throw new InvalidParamException("negative-value",
								val.toString(), ac);
					}
					if (is_complete) {
						vl = new CssValueList();
						vl.add(val);
					} else {
						vl.add(val);
						values.add(vl);
					}
					is_complete = !is_complete;
					break;
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						// if we got inherit after other values, fail
						// if we got more than one value... fail
						if ((values.size() > 0) || (expression.getCount() > 1)) {
							throw new InvalidParamException("value", val,
									getPropertyName(), ac);
						}
						values.add(inherit);
						break;
					} else if (auto.equals(val)) {
						if (is_complete) {
							vl = new CssValueList();
							vl.add(auto);
						} else {
							vl.add(auto);
							values.add(vl);
						}
						is_complete = !is_complete;
						break;
					} else {
						CssValue v = getMatchingIdent((CssIdent) val);
						// if ok, and if we are not in a middle of a compound
						// value...
						if (v != null && is_complete) {
							values.add(v);
							break;
						}
					}
				default:
					throw new InvalidParamException("value", val,
							getPropertyName(), ac);

			}
			expression.next();
			if (!expression.end()) {
				// incomplete value followed by a comma... it's complete!
				if (!is_complete && (op == COMMA)) {
					values.add(vl);
					is_complete = true;
				}
				// complete values are separated by a comma, otherwise space
				if ((is_complete && (op != COMMA)) ||
						(!is_complete && (op != SPACE))) {
					throw new InvalidParamException("operator",
							((new Character(op)).toString()), ac);
				}
			}
		}
		// if we reach the end in a value that can come in pair
		if (!is_complete) {
			values.add(vl);
		}
		if (values.size() == 1) {
			value = values.get(0);
		} else {
			value = new CssLayerList(values);
		}
	}


	public CssBackgroundSize(ApplContext ac, CssExpression expression)
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
