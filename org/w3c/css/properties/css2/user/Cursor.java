//
// $Id: Cursor.java,v 1.4 2011-08-29 07:21:01 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated september 14th 2000 by Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css2.user;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

/**
 * @version $Revision: 1.4 $
 */
public class Cursor extends CssProperty
        implements CssOperator {

    int value;
    ArrayList<CssValue> uris = new ArrayList<CssValue>();
    boolean inheritedValue;

    private static String CURSOR[] = {
            "auto", "crosshair", "default", "pointer", "move", "e-resize",
            "ne-resize", "nw-resize", "n-resize", "se-resize", "sw-resize",
            "s-resize", "w-resize", "text", "wait", "help", "progress", "copy", "alias",
            "context-menu", "cell", "all-scroll", "col-resize", "row-resize", "no-drop",
            "not-allowed", "vertical-text"
    };

    private static int[] hash_values;


    /**
     * Create a new CssCursor
     */
    public Cursor() {
        value = 0;
    }

    /**
     * Create a new CssCursor
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public Cursor(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        CssValue val = expression.getValue();
        char op = expression.getOperator();

        setByUser();

        if (val.equals(inherit)) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }
            inheritedValue = true;
            expression.next();
            return;
        }

        while ((op == COMMA) && (val.getType() == CssTypes.CSS_URL)) {
            if (val != null && val.equals(inherit)) {
                throw new InvalidParamException("unrecognize", ac);
            }
            uris.add(val);
            expression.next();
            val = expression.getValue();
            op = expression.getOperator();
        }
        if (val.getType() == CssTypes.CSS_URL) {
            throw new InvalidParamException("comma",
                    val.toString(),
                    getPropertyName(), ac);
        }

        if (val.getType() == CssTypes.CSS_IDENT) {
            int hash = val.hashCode();

            for (int i = 0; i < CURSOR.length; i++) {
                if (hash_values[i] == hash) {
                    value = i;
                    expression.next();
                    if (check && !expression.end()) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    return;
                }
            }
        }

        throw new InvalidParamException("value",
                val.toString(), getPropertyName(), ac);
    }

    public Cursor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return null;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
        return "cursor";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return inheritedValue;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (inheritedValue) {
            return inherit.toString();
        } else {
            StringBuilder ret = new StringBuilder();
            for (CssValue val : uris) {
                ret.append(val).append(COMMA).append(' ');
            }
            ret.append(' ').append(CURSOR[value]);
            return ret.toString();
        }
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        Css2Style style0 = (Css2Style) style;
        if (style0.cursor != null)
            style0.addRedefinitionWarning(ac, this);
        style0.cursor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css2Style) style).getCursor();
        } else {
            return ((Css2Style) style).cursor;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof Cursor
                && value == ((Cursor) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return value == 0;
    }

    static {
        hash_values = new int[CURSOR.length];
        for (int i = 0; i < CURSOR.length; i++)
            hash_values[i] = CURSOR[i].hashCode();
    }
}
