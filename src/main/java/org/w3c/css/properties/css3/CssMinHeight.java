// $Id: CssMinHeight.java,v 1.1 2012-10-11 12:58:56 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2007/WD-css3-box-20070809/#min-height
 * @spec http://www.w3.org/TR/2012/CR-css3-flexbox-20120918/#min-size-auto
 */
public class CssMinHeight extends org.w3c.css.properties.css.CssMinHeight {

	public static final CssIdent auto = CssIdent.getIdent("auto");
    /**
     * Create a new CssMinHeight
     */
    public CssMinHeight() {
		value = initial;
    }

    /**
     * Creates a new CssMinHeight
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssMinHeight(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		CssValue val = expression.getValue();

		setByUser();

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
			case CssTypes.CSS_LENGTH:
				CssLength length = val.getLength();
				length.checkPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_PERCENTAGE:
				CssPercentage percentage = val.getPercentage();
				percentage.checkPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					value = inherit;
					break;
				}
				// auto is from flexbox specification
				if (auto.equals(val)) {
					value = auto;
					break;
				}
			default:
				throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
		}
		expression.next();
    }

    public CssMinHeight(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

