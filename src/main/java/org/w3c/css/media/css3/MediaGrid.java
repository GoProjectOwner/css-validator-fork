// $Id: MediaGrid.java,v 1.2 2012-06-19 19:21:22 ylafon Exp $
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.media.css3;

import org.w3c.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#grid
 */
public class MediaGrid extends MediaFeature {

    /**
     * Create a new MediaGrid
     */
    public MediaGrid() {
    }

    /**
     * Create a new MediaGrid.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaGrid(ApplContext ac, String modifier,
                     CssExpression expression, boolean check)
            throws InvalidParamException {

        if (modifier != null) {
            throw new InvalidParamException("nomodifiermedia",
                    getFeatureName(), ac);
        }

        if (expression != null) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }

            CssValue val = expression.getValue();
            // it must be a >=0 integer only
            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssNumber valnum = (CssNumber) val;
                if (!valnum.isInteger()) {
                    throw new InvalidParamException("integer",
                            val.toString(), ac);
                }
                int gridval = valnum.getInt();
                if (gridval != 0 && gridval != 1) {
                    throw new InvalidParamException("grid",
                            val.toString(), ac);
                }
                value = valnum;
            } else {
                throw new InvalidParamException("unrecognize", ac);
            }
        }
    }

    // just in case someone wants to call it externally...
    public void setModifier(ApplContext ac, String modifier)
            throws InvalidParamException {
        throw new InvalidParamException("nomodifiermedia",
                getFeatureName(), ac);
    }

    public MediaGrid(ApplContext ac, String modifier, CssExpression expression)
            throws InvalidParamException {
        this(ac, modifier, expression, false);
    }

    /**
     * Returns the value of this media feature.
     */

    public Object get() {
        return value;
    }

    /**
     * Returns the name of this media feature.
     */
    public final String getFeatureName() {
        return "grid";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaGrid mg = (MediaGrid) other;
            return (((value == null) && (mg.value == null)) || ((value != null) && value.equals(mg.value)))
                    && (((modifier == null) && (mg.modifier == null)) || ((modifier != null) && modifier.equals(mg.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
