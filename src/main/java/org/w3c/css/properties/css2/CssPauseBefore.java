// $Id: CssPauseBefore.java,v 1.1 2013-01-04 13:14:02 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2013.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS2-20080411/aural.html#propdef-pause-before
 */
public class CssPauseBefore extends org.w3c.css.properties.css.CssPauseBefore {

    /**
     * Create a new CssPauseBefore
     */
    public CssPauseBefore() {
    }

    /**
     * Creates a new CssPauseBefore
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssPauseBefore(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		val = expression.getValue();
		expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_TIME:
				CssTime t = val.getTime();
				t.checkPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_PERCENTAGE:
				CssPercentage p = val.getPercentage();
				p.checkPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				CssIdent id = (CssIdent) val;
				if (inherit.equals(id)) {
					value = inherit;
					break;
				}
			default:
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
		}
		expression.next();
    }

    public CssPauseBefore(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

