// $Id: CssBorderRightStyle.java,v 1.1 2012-04-25 20:22:01 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @since CSS2
 * @version $Revision: 1.1 $
 */
public class CssBorderRightStyle extends org.w3c.css.properties.css.CssBorderRightStyle {

    /**
     * Create a new CssBorderRightStyle
     */
    public CssBorderRightStyle() {
    }

    /**
     * Creates a new CssBorderRightStyle
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderRightStyle(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        setByUser();
        // here we delegate to BorderWidth implementation
        value = CssBorderStyle.checkBorderSideStyle(ac, this, expression, check);
    }

    public CssBorderRightStyle(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

