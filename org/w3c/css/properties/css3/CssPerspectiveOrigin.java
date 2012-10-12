// $Id: CssPerspectiveOrigin.java,v 1.2 2012-10-12 12:31:00 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-transforms-20120911/#perspective-origin
 */
public class CssPerspectiveOrigin extends org.w3c.css.properties.css.CssPerspectiveOrigin {

	public static CssIdent[] allowed_values;
	public static CssIdent center, top, bottom, left, right;

	static {
		top = CssIdent.getIdent("top");
		bottom = CssIdent.getIdent("bottom");
		left = CssIdent.getIdent("left");
		right = CssIdent.getIdent("right");
		center = CssIdent.getIdent("center");
		allowed_values = new CssIdent[5];
		allowed_values[0] = top;
		allowed_values[1] = bottom;
		allowed_values[2] = left;
		allowed_values[3] = right;
		allowed_values[4] = center;
	}

	public static CssIdent getMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public boolean isVerticalIdent(CssIdent ident) {
		return top.equals(ident) || center.equals(ident) || bottom.equals(ident);
	}

	public boolean isHorizontalIdent(CssIdent ident) {
		return left.equals(ident) || center.equals(ident) || right.equals(ident);
	}

	/**
	 * Create a new CssTransformOrigin
	 */
	public CssPerspectiveOrigin() {
		super();
	}

	/**
	 * Creates a new CssTransformOrigin
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Values are incorrect
	 */
	public CssPerspectiveOrigin(ApplContext ac, CssExpression expression,
								boolean check) throws InvalidParamException {

		int nb_val = expression.getCount();

		if (check && nb_val > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();
		CssValue val;
		char op;
		ArrayList<CssValue> values = new ArrayList<CssValue>();

		// we just accumulate values and check at validation
		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			if (inherit.equals(val)) {
				if (expression.getCount() > 1) {
					throw new InvalidParamException("value", val,
							getPropertyName(), ac);
				}
				value = inherit;
				expression.next();
				return;
			}
			// we will check later
			values.add(val);
			expression.next();

			if (op != SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
		// if we reach the end in a value that can come in pair
		check(values, ac);
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
	}

	// check the value

	private void check(ArrayList<CssValue> values, ApplContext ac)
			throws InvalidParamException {
		int nb_keyword = 0;
		int nb_values = values.size();

		if (nb_values > 2 || nb_values == 0) {
			throw new InvalidParamException("unrecognize", ac);
		}
		// basic check
		for (CssValue aValue : values) {
			switch (aValue.getType()) {
				case CssTypes.CSS_NUMBER:
				case CssTypes.CSS_LENGTH:
					CssLength len = aValue.getLength();
					break;
				case CssTypes.CSS_PERCENTAGE:
					break;
				case CssTypes.CSS_IDENT:
					nb_keyword++;
					break;
				default:
					throw new InvalidParamException("value", aValue,
							getPropertyName(), ac);
			}
		}
		// then we need to ckeck the values if we got two values and
		// at least one keyword (as restrictions may occur)
		if (nb_keyword > 0 && nb_values == 2) {
			CssValue v = values.get(0);
			if (v.getType() == CssTypes.CSS_IDENT) {
				CssIdent id = (CssIdent) v;
				if (!isHorizontalIdent(id)) {
					throw new InvalidParamException("value", id,
							getPropertyName(), ac);
				}
			}
			v = values.get(1);
			if (v.getType() == CssTypes.CSS_IDENT) {
				CssIdent id = (CssIdent) v;
				if (!isVerticalIdent(id)) {
					throw new InvalidParamException("value", id,
							getPropertyName(), ac);
				}
			}
		}
	}

	public CssPerspectiveOrigin(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}
