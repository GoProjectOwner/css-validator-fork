// $Id: CssColumnCount.java,v 1.12 2012-09-10 17:04:58 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#cc
 */

public class CssColumnCount extends org.w3c.css.properties.css.CssColumnCount {


    static CssIdent auto = CssIdent.getIdent("auto");

    /**
     * Create a new CssColumnCount
     */
    public CssColumnCount() {
        value = initial;
    }

    /**
     * Create a new CssColumnCount
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Incorrect value
     */
    public CssColumnCount(ApplContext ac, CssExpression expression,
                          boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();
        CssNumber num;

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                num = val.getNumber();
				num.checkInteger(ac, this);
				num.checkStrictPositiveness(ac, this);
                value = val;
                break;
            case CssTypes.CSS_IDENT:
                if (auto.equals(val)) {
                    value = auto;
                    break;
                }
                if (inherit.equals(val)) {
                    value = inherit;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColumnCount(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == auto) || (value == initial);
    }
}
