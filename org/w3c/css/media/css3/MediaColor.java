// $Id: MediaColor.java,v 1.3 2012-06-19 19:21:22 ylafon Exp $
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
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#color
 */
public class MediaColor extends MediaFeature {

    /**
     * Create a new MediaColor
     */
    public MediaColor() {
    }

    /**
     * Create a new MediaColor.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaColor(ApplContext ac, String modifier,
                      CssExpression expression, boolean check)
            throws InvalidParamException {

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
                if (!valnum.isPositive()) {
                    throw new InvalidParamException("negative-value",
                                val.toString(), ac);
                }
                value = valnum;
            } else {
                throw new InvalidParamException("unrecognize", ac);
            }
            setModifier(ac, modifier);
        } else {
            if (modifier != null) {
                throw new InvalidParamException("nomodifiershortmedia",
                        getFeatureName(), ac);
            }
        }
    }

    public MediaColor(ApplContext ac, String modifier, CssExpression expression)
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
        return "color";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaColor mh = (MediaColor) other;
            return (((value == null) && (mh.value == null)) || ((value != null) && value.equals(mh.value)))
                    && (((modifier == null) && (mh.modifier == null)) || ((modifier != null) && modifier.equals(mh.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
