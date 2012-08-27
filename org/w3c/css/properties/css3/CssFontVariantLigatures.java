// $Id: CssFontVariantLigatures.java,v 1.6 2012-08-27 14:55:33 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-fonts-20120823/#propdef-font-variant-ligatures
 */
public class CssFontVariantLigatures extends org.w3c.css.properties.css.CssFontVariantLigatures {

	public static final CssIdent[] commonLigValues;
	public static final CssIdent[] discretionaryLigValues;
	public static final CssIdent[] historicalLigValues;
	public static final CssIdent[] contextualAltValues;

	public static final CssIdent normal;

	static {
		String[] _commonLigValues = {"common-ligatures", "no-common-ligatures"};
		String[] _discretionaryLigValues = {"discretionary-ligatures",
				"no-discretionary-ligatures"};
		String[] _historicalLigValues = {"historical-ligatures",
				"no-historical-ligatures"};
		String[] _contextualAltValues = {"contextual", "no-contextual"};

		normal = CssIdent.getIdent("normal");

		commonLigValues = new CssIdent[_commonLigValues.length];
		int i = 0;
		for (String s : _commonLigValues) {
			commonLigValues[i++] = CssIdent.getIdent(s);
		}

		discretionaryLigValues = new CssIdent[_discretionaryLigValues.length];
		i = 0;
		for (String s : _discretionaryLigValues) {
			discretionaryLigValues[i++] = CssIdent.getIdent(s);
		}

		historicalLigValues = new CssIdent[_historicalLigValues.length];
		i = 0;
		for (String s : _historicalLigValues) {
			historicalLigValues[i++] = CssIdent.getIdent(s);
		}

		contextualAltValues = new CssIdent[_contextualAltValues.length];
		i = 0;
		for (String s : _contextualAltValues) {
			contextualAltValues[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getCommonLigValues(CssIdent ident) {
		for (CssIdent id : commonLigValues) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getDiscretionaryLigValues(CssIdent ident) {
		for (CssIdent id : discretionaryLigValues) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getHistoricalLigValues(CssIdent ident) {
		for (CssIdent id : historicalLigValues) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getContextualAltValues(CssIdent ident) {
		for (CssIdent id : contextualAltValues) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getAllowedValue(CssIdent ident) {
		CssIdent id;
		if (none.equals(ident)) {
			return none;
		}
		id = getCommonLigValues(ident);
		if (id == null) {
			id = getDiscretionaryLigValues(ident);
			if (id == null) {
				id = getHistoricalLigValues(ident);
				if (id == null) {
					id = getContextualAltValues(ident);
				}
			}
		}
		return id;
	}

	/**
	 * Create a new CssFontVariantLigatures
	 */
	public CssFontVariantLigatures() {
		value = initial;
	}

	/**
	 * Creates a new CssFontVariantLigatures
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariantLigatures(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 3) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();

		CssValue val;
		char op;

		CssIdent histValue = null;
		CssIdent commonValue = null;
		CssIdent discValue = null;
		CssIdent altValue = null;
		boolean match;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			if (val.getType() == CssTypes.CSS_IDENT) {
				CssIdent ident = (CssIdent) val;
				if (inherit.equals(ident)) {
					if (expression.getCount() != 1) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					value = inherit;
				} else if (normal.equals(ident)) {
					if (expression.getCount() != 1) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					value = normal;
				} else if (none.equals(ident)) {
					if (expression.getCount() != 1) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					value = none;
				} else {
					// no inherit, nor normal, test the up-to-three values
					match = false;
					if (commonValue == null) {
						commonValue = getCommonLigValues(ident);
						match = (commonValue != null);
					}
					if (!match && histValue == null) {
						histValue = getHistoricalLigValues(ident);
						match = (histValue != null);
					}
					if (!match && discValue == null) {
						discValue = getDiscretionaryLigValues(ident);
						match = (discValue != null);
					}
					if (!match && altValue == null) {
						altValue = getContextualAltValues(ident);
						match = (altValue != null);
					}
					if (!match) {
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
			if (op != CssOperator.SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();
		}
		// now set the right value
		if (expression.getCount() == 1) {
			// the last test is here in case value is already set
			// (normal or inherit)
			if (commonValue != null) {
				value = commonValue;
			} else if (histValue != null) {
				value = histValue;
			} else if (discValue != null) {
				value = discValue;
			} else if (altValue != null) {
				value = altValue;
			}
		} else {
			// do this to keep the same order for comparisons
			ArrayList<CssValue> v = new ArrayList<CssValue>();
			if (commonValue != null) {
				v.add(commonValue);
			}
			if (histValue != null) {
				v.add(histValue);
			}
			if (discValue != null) {
				v.add(discValue);
			}
			if (altValue != null) {
				v.add(altValue);
			}
			value = new CssValueList(v);
		}
	}

	public CssFontVariantLigatures(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

