// $Id: CssBackgroundRepeat.java,v 1.5 2011-10-04 13:05:24 ylafon Exp $
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-repeat
 * <p/>
 * Name: 	background-repeat
 * Value: 	&lt;repeat-style&gt; [ , &lt;repeat-style&gt; ]*
 * Initial: 	repeat
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	as specified
 * <p/>
 * Specifies how background images are tiled after they have been sized and
 * positioned. Where
 * <p/>
 * &lt;repeat-style&gt; = repeat-x | repeat-y | [repeat | space |
 * round | no-repeat]{1,2}
 */
public class CssBackgroundRepeat extends CssProperty {
    public final static CssIdent repeat;

    static {
        repeat = CssIdent.getIdent("repeat");
    }

    public Object value;

    /**
     * Create a new CssBackgroundRepeat
     */
    public CssBackgroundRepeat() {
    }

    /**
     * Set the value of the property
     *
     * @param ac         the context
     * @param expression The expression for this property
     * @param check      is length checking needed
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackgroundRepeat(ApplContext ac, CssExpression expression,
                               boolean check) throws InvalidParamException {
        throw new InvalidParamException("unrecognize", ac);
    }


    public CssBackgroundRepeat(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */

    public Object get() {
        return value;
    }

    public void set(Object val) {
        value = val;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return "background-repeat";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        CssBackground cssBackground = ((Css1Style) style).cssBackground;
        if (cssBackground.repeat != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.repeat = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundRepeat();
        } else {
            return ((Css1Style) style).cssBackground.repeat;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundRepeat &&
                value == ((CssBackgroundRepeat) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return false;
    }

}



