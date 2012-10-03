// $Id: CssTransitionProperty.java,v 1.2 2012-10-03 12:03:46 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.PropertiesLoader;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-transitions-20120403/#transition-property
 */
public class CssTransitionProperty extends org.w3c.css.properties.css.CssTransitionProperty {

	public static final CssIdent all = CssIdent.getIdent("all");
	/**
	 * Create a new CssTransitionProperty
	 */
	public CssTransitionProperty() {
		value = initial;
	}

	/**
	 * Creates a new CssTransitionProperty
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssTransitionProperty(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();

		CssValue val;
		char op;
		ArrayList<CssValue> values = new ArrayList<CssValue>();
		boolean singleVal = false;
		CssValue sValue = null;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						singleVal = true;
						sValue = inherit;
						values.add(inherit);
					} else if (none.equals(val)) {
						singleVal = true;
						sValue = none;
						values.add(none);
					} else if (all.equals(val)) {
						values.add(all);
					} else {
						if (PropertiesLoader.getProfile(ac.getPropertyKey()).getProperty(val.toString()) == null) {
							ac.getFrame().addWarning("noexproperty", val.toString());
						}
						values.add(val);
					}
					break;
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
		if (singleVal && values.size() > 1) {
			throw new InvalidParamException("value",
					sValue.toString(),
					getPropertyName(), ac);
		}
		value = (values.size() == 1) ? values.get(0) : new CssLayerList(values);
	}

	public CssTransitionProperty(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

