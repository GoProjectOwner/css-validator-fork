// $Id: CssBorderTopColor.java,v 1.1 2012-04-25 20:22:01 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @see CssBorderColor
 * @version $Revision: 1.1 $
 */
public class CssBorderTopColor extends org.w3c.css.properties.css.CssBorderTopColor {

    /**
     * Create a new CssBorderTopColor
     */
    public CssBorderTopColor() {
    }

    /**
     * Creates a new CssBorderTopColor
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderTopColor(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        value = CssBorderColor.checkBorderSideColor(ac, this, expression, check);
    }

    public CssBorderTopColor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

