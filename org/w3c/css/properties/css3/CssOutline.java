// $Id: CssOutline.java,v 1.3 2012-10-18 09:46:03 ylafon Exp $
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
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-ui-20120117/#outline
 * @see org.w3c.css.properties.css3.CssBorderStyle
 * @see org.w3c.css.properties.css3.CssBorderWidth
 */
public class CssOutline extends org.w3c.css.properties.css.CssOutline {

	/**
	 * Create a new CssOutline
	 */
	public CssOutline() {
		_color = new CssOutlineColor();
		_style = new CssOutlineStyle();
		_width = new CssOutlineWidth();
	}

	/**
	 * Creates a new CssOutline
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssOutline(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {

		if (check && expression.getCount() > 3) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();

		CssValue val;
		char op;

		CssValue colorValue = null;
		CssValue widthValue = null;
		CssValue styleValue = null;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
				case CssTypes.CSS_LENGTH:
					if (widthValue == null) {
						CssExpression ex = new CssExpression();
						ex.addValue(val);
						_width = new CssOutlineWidth(ac, ex, check);
						widthValue = _width.value;
						break;
					}
					// else, we already got one...
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				case CssTypes.CSS_HASH_IDENT:
				case CssTypes.CSS_COLOR:
					if (colorValue == null) {
						CssExpression ex = new CssExpression();
						ex.addValue(val);
						_color = new CssOutlineColor(ac, ex, check);
						colorValue = _color.value;
						break;
					}
					// else, we already got one...
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						if (expression.getCount() != 1) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						value = inherit;
						break;
					}
					CssIdent ident = (CssIdent) val;
					// let's try to find which ident we have...
					if (styleValue == null) {
						CssIdent match = CssOutlineStyle.getMatchingIdent(ident);
						if (match != null) {
							styleValue = match;
							break;
						}
					}
					if (widthValue == null) {
						CssIdent match = CssBorderWidth.getMatchingIdent(ident);
						if (match != null) {
							widthValue = match;
							break;
						}
					}
					if (colorValue == null) {
						CssIdent match = CssOutlineColor.getMatchingIdent(ident);
						if (match != null) {
							colorValue = match;
							break;
						} else {
							CssExpression ex = new CssExpression();
							ex.addValue(val);
							_color = new CssOutlineColor(ac, ex, check);
							colorValue = _color.value;
							break;
						}
					}
					// unrecognized... fail
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			expression.next();
			if (op != SPACE) {
				throw new InvalidParamException("operator",
						Character.toString(op),
						ac);
			}
		}
		if (_width == null) {
			_width = new CssOutlineWidth();
		}
		if (_style == null) {
			_style = new CssOutlineStyle();
		}
		if (_color == null) {
			_color = new CssOutlineColor();
		}
		// now construct the value...
		if (expression.getCount() == 1) {
			if (widthValue != null) {
				value = widthValue;
				_width.value = widthValue;
			} else if (styleValue != null) {
				value = styleValue;
				_style.value = styleValue;
			} else {
				value = colorValue;
				_color.value = colorValue;
			}
		} else {
			ArrayList<CssValue> values = new ArrayList<CssValue>(4);
			if (widthValue != null) {
				values.add(widthValue);
				_width.value = widthValue;
			}
			if (styleValue != null) {
				values.add(styleValue);
				_style.value = styleValue;
			}
			if (colorValue != null ){
				values.add(colorValue);
				_color.value = colorValue;
			}
			value = new CssValueList(values);
		}
	}

	public CssOutline(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

