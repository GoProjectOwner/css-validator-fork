// $Id: CssColumnGap.java,v 1.2 2011-10-05 07:12:17 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// COPYRIGHT (c) 1995-2010 World Wide Web Consortium, (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css3.Css3Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @since CSS3
 */

public class CssColumnGap extends CssProperty {

    private static final String propertyName = "column-gap";

    CssValue columngap;

    /**
     * Create a new CssColumnGap
     */
    public CssColumnGap() {
    }

    /**
     * Create a new CssColumnGap
     */
    public CssColumnGap(ApplContext ac, CssExpression expression,
                        boolean check) throws InvalidParamException {

        throw new InvalidParamException("unrecognize", ac);
    }

    public CssColumnGap(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssColumnGap != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssColumnGap = this;

    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getColumnGap();
        } else {
            return ((Css3Style) style).cssColumnGap;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnGap &&
                columngap.equals(((CssColumnGap) property).columngap));
    }

    /**
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return columngap;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == columngap);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return columngap.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return false;
    }

}
