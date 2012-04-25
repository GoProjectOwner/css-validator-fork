// $Id: CssBorderTopStyle.java,v 1.1 2012-04-25 20:22:03 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @see CssBorderStyle
 * @version $Revision: 1.1 $
 */
public class CssBorderTopStyle extends org.w3c.css.properties.css.CssBorderTopStyle {

    /**
     * Create a new CssBorderTopStyle
     */
    public CssBorderTopStyle() {
    }

    /**
     * Creates a new CssBorderTopStyle
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderTopStyle(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        setByUser();
        // here we delegate to BorderWidth implementation
        value = CssBorderStyle.checkBorderSideStyle(ac, this, expression, check);
    }

    public CssBorderTopStyle(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

