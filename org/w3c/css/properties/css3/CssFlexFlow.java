// $Id: CssFlexFlow.java,v 1.1 2012-10-07 12:49:05 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-flexbox-20120918/#flex-flow
 */
public class CssFlexFlow extends org.w3c.css.properties.css.CssFlexFlow {

	private CssFlexDirection flexDirection;
	private CssFlexWrap flexWrap;

	/**
	 * Create a new CssFlexFlow
	 */
	public CssFlexFlow() {
		value = initial;
		flexDirection = new CssFlexDirection();
		flexWrap = new CssFlexWrap();
	}

	/**
	 * Creates a new CssFlexFlow
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFlexFlow(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue directionVal = null;
		CssValue wrapVal = null;
		CssValue val;
		char op;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
					CssIdent ident = (CssIdent) val;
					if (inherit.equals(ident)) {
						value = inherit;
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						break;
					}
					if (directionVal == null) {
						directionVal = CssFlexDirection.getAllowedIdent(ident);
						if (directionVal != null) {
							break;
						}
					}
					if (wrapVal == null) {
						wrapVal = CssFlexWrap.getAllowedIdent(ident);
						if (wrapVal != null) {
							break;
						}
					}
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			if (op != CssOperator.SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();
		}
		// for addToStyle, redefinitions and equality check
		flexDirection = new CssFlexDirection();
		flexWrap = new CssFlexWrap();
		if (value == inherit) {
			flexDirection.value = inherit;
			flexWrap.value = inherit;
		} else {
			CssValueList v = new CssValueList();
			if (directionVal != null) {
				v.add(directionVal);
				flexDirection.value = directionVal;
			}
			if (wrapVal != null) {
				v.add(wrapVal);
				flexWrap.value = wrapVal;
			}
			value = v;
		}
	}

	public CssFlexFlow(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
		super.addToStyle(ac, style);
		flexDirection.addToStyle(ac, style);
		flexWrap.addToStyle(ac, style);
	}
}

