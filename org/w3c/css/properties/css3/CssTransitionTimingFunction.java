// $Id: CssTransitionTimingFunction.java,v 1.1 2012-10-03 15:11:04 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-transitions-20120403/#transition-timing-function
 */
public class CssTransitionTimingFunction extends org.w3c.css.properties.css.CssTransitionTimingFunction {

	public static final CssIdent[] allowed_values;
	public static final String steps_func = "steps";
	public static final String cubic_bezier_func = "cubic-bezier";
	public static final CssIdent start;
	public static final CssIdent end;

	static {
		String[] _allowed_values = {"ease", "linear", "ease-in",
				"ease-out", "ease-in-out", "step-start", "step-end"};
		allowed_values = new CssIdent[_allowed_values.length];
		int i = 0;
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
		// for 'steps function
		start = CssIdent.getIdent("start");
		end = CssIdent.getIdent("end");
	}

	public CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssTransitionTimingFunction
	 */
	public CssTransitionTimingFunction() {
		value = initial;
	}

	/**
	 * Creates a new CssTransitionTimingFunction
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssTransitionTimingFunction(ApplContext ac, CssExpression expression, boolean check)
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
				case CssTypes.CSS_FUNCTION:
					CssFunction function = (CssFunction) val;
					String fname = function.getName().toLowerCase();
					if (steps_func.equals(fname)) {
						parseStepsFunction(ac, function.getParameters(), this);
						values.add(val);
						break;
					} else if (cubic_bezier_func.equals(fname)) {
						parseCubicBezierFunction(ac, function.getParameters(), this);
						values.add(val);
						break;
					}
					// unrecognized function
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						singleVal = true;
						sValue = inherit;
						values.add(inherit);
						break;
					} else {
						CssIdent id = getAllowedIdent((CssIdent) val);
						if (id != null) {
							values.add(id);
							break;
						}
						// if not recognized, let it fail
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
		if (singleVal && values.size() > 1) {
			throw new InvalidParamException("value",
					sValue.toString(),
					getPropertyName(), ac);
		}
		value = (values.size() == 1) ? values.get(0) : new CssLayerList(values);
	}

	public CssTransitionTimingFunction(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	// parse and check timing function
	// value might be used later... currently ignored
	private static CssValue parseStepsFunction(ApplContext ac, CssExpression funcparam, CssProperty caller)
			throws InvalidParamException {
		CssValue val;
		char op;

		if (funcparam.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}

		ArrayList<CssValue> values = new ArrayList<CssValue>();
		val = funcparam.getValue();
		op = funcparam.getOperator();

		if (val.getType() != CssTypes.CSS_NUMBER) {
			throw new InvalidParamException("value",
					val.toString(),
					caller.getPropertyName(), ac);
		}
		// we have a number, continue checks
		CssNumber number = val.getNumber();
		// it must be a >0 integer
		number.checkInteger(ac, caller);
		number.checkStrictPositiveness(ac, caller);
		values.add(val);
		funcparam.next();
		if (!funcparam.end()) {
			// check the second value
			if (op != COMMA) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			val = funcparam.getValue();
			if (val.getType() != CssTypes.CSS_IDENT) {
				throw new InvalidParamException("value",
						val.toString(),
						caller.getPropertyName(), ac);
			}
			if (start.equals(val)) {
				values.add(start);
			} else if (end.equals(val)) {
				values.add(end);
			} else {
				// unrecognized ident
				throw new InvalidParamException("value",
						val.toString(),
						caller.getPropertyName(), ac);
			}
			funcparam.next();
		}
		return (values.size() == 1) ? values.get(0) : new CssLayerList(values);
	}

	private static CssValue parseCubicBezierFunction(ApplContext ac, CssExpression funcparam, CssProperty caller)
			throws InvalidParamException {
		CssValue val;
		char op;

		if (funcparam.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		if (funcparam.getCount() < 4) {
			throw new InvalidParamException("few-value", caller.getPropertyName(), ac);
		}

		ArrayList<CssValue> values = new ArrayList<CssValue>();

		for (int i = 0; i < 4; i++) {
			val = funcparam.getValue();
			op = funcparam.getOperator();
			if (val.getType() != CssTypes.CSS_NUMBER) {
				throw new InvalidParamException("value",
						val.toString(),
						caller.getPropertyName(), ac);
			}
			// we have a number, continue checks
			CssNumber number = val.getNumber();
			// it must be a >0 integer
			number.checkPositiveness(ac, caller);
			number.checkLowerEqualThan(ac, 1., caller);
			values.add(val);
			// go to the next item...
			funcparam.next();
			if (!funcparam.end() && (op != COMMA)) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
		return new CssLayerList(values);
	}
}

