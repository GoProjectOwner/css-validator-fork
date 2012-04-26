// $Id: CssBorderBottomStyle.java,v 1.2 2012-04-26 18:33:30 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-background-20120417/#border-bottom-style
 * @see CssBorderStyle
 */
public class CssBorderBottomStyle extends org.w3c.css.properties.css.CssBorderBottomStyle {

    /**
     * Create a new CssBorderBottomStyle
     */
    public CssBorderBottomStyle() {
        value = initial;
    }

    /**
     * Creates a new CssBorderBottomStyle
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderBottomStyle(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        setByUser();
        // here we delegate to BorderWidth implementation
        value = CssBorderStyle.checkBorderSideStyle(ac, this, expression, check);
    }

    public CssBorderBottomStyle(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

