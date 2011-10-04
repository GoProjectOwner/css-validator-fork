// $Id: CssBreakBefore.java,v 1.3 2011-10-04 20:08:11 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>

// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
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
 * http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#column-breaks
 * <p/>
 * When content is laid out in multiple columns, the user agent must determine
 * where column breaks are placed. The problem of breaking content into columns
 * is similar to breaking content into pages.
 * <p/>
 * Three new properties are introduced to allow column breaks to be described
 * in the same properties as page breaks: ‘break-before’, ‘break-after’, and
 * ‘break-inside’. These properties take the same values as
 * ‘page-break-before’, ‘page-break-after’, and ‘page-break-inside’ [CSS21].
 * In addition, some new keyword values are added.
 * <p/>
 * Name:  	break-before
 * Value: 	auto | always | avoid | left | right | page | column |
 * avoid-page | avoid-column
 * Initial: 	auto
 * Applies to: 	block-level elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	paged
 * Computed value: 	specified value
 */

public class CssBreakBefore extends CssProperty {


    CssValue value;


    /**
     * Create a new CssColumnWidth
     */
    public CssBreakBefore() {
    }

    /**
     * Create a new CssBreakBefore
     *
     * @param ac         the context
     * @param expression The expression for this property
     * @param check      if checking is needed
     * @throws InvalidParamException Incorrect value
     */
    public CssBreakBefore(ApplContext ac, CssExpression expression,
                          boolean check) throws InvalidParamException {

            throw new InvalidParamException("unrecognize", ac);
    }

    public CssBreakBefore(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssBreakBefore != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssBreakBefore = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getBreakBefore();
        } else {
            return ((Css3Style) style).cssBreakBefore;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBreakBefore &&
                value.equals(((CssBreakBefore) property).value));
    }

    /**
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return "break-before";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return false;
    }

}
