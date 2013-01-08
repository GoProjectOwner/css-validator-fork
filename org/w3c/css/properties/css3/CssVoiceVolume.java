// $Id: CssVoiceVolume.java,v 1.1 2013-01-08 14:46:19 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2013.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-speech-20120320/#voice-volume
 */
public class CssVoiceVolume extends org.w3c.css.properties.css.CssVoiceVolume {

	public static final CssIdent[] allowed_values;
	public static final CssIdent silent;

	static {
		silent = CssIdent.getIdent("silent");

		String[] _allowed_values = {"x-soft", "soft", "medium", "loud", "x-loud"};
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
	 * Create a new CssVoiceVolume
	 */
	public CssVoiceVolume() {
		value = initial;
	}

	/**
	 * Creates a new CssVoiceVolume
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssVoiceVolume(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		CssValue dbValue = null;
		CssValue ideValue = null;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_VOLUME:
					if (dbValue != null) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					dbValue = val;
					break;
				case CssTypes.CSS_IDENT:
					CssIdent id = (CssIdent) val;
					if (inherit.equals(id)) {
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									inherit,
									getPropertyName(), ac);
						}
						value = inherit;
						break;
					} else if (silent.equals(id)) {
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									silent,
									getPropertyName(), ac);
						}
						value = silent;
						break;
					} else {
						if (ideValue == null) {
							ideValue = getAllowedIdent(id);
							if (ideValue != null) {
								break;
							}
						}
					}
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			if (op != SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();
		}
		// now check what we have...
		if (value != inherit && value != silent) {
			ArrayList<CssValue> v = new ArrayList<CssValue>(2);
			if (ideValue != null) {
				v.add(ideValue);
			}
			if (dbValue != null) {
				v.add(dbValue);
			}
			value = (v.size() == 1) ? v.get(0) : new CssValueList(v);
		}
	}

	public CssVoiceVolume(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

