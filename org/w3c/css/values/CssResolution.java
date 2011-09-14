//
// $Id: CssResolution.java,v 1.7 2011-09-14 16:31:50 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 25th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * <H3>
 * &nbsp;&nbsp; Resolution units
 * </H3>
 * <p/>
 * The dpi and dpcm units describe the resolution of an output device i.e. the density of
 * the pixels. In dots per inch and dots per centimeter, respectively. These units are only used in the
 * resolution media feature.
 * </P>
 *
 * @version $Revision: 1.7 $
 */
public class CssResolution extends CssValue {

    public static final int type = CssTypes.CSS_RESOLUTION;

    public final int getType() {
        return type;
    }

    /**
     * Create a new CssResolution
     */
    public CssResolution() {
        value = defaultValue;
    }

    /**
     * Set the value of this Resolution.
     *
     * @param s  the string representation of the Resolution.
     * @param ac For errors and warnings reports.
     * @throws InvalidParamException The unit is incorrect
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
        s = s.toLowerCase();
        int length = s.length();
        String unit = "";
        if (s.contains("dpi")) {
            unit = s.substring(length - 3, length);
            this.value = new Float(s.substring(0, length - 3));
            if (unit.equals("dpi")) {
                this.unit = unit;
            }
            return;
        } else if (s.contains("dpcm")) {
            unit = s.substring(length - 4, length);
            this.value = new Float(s.substring(0, length - 4));
            if (unit.equals("dpcm")) {
                this.unit = unit;
            }
            return;
        }

        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            throw new InvalidParamException("unit", unit, ac);
        }

        throw new InvalidParamException("unit", unit, ac);
    }

    /**
     * Returns the current value
     */
    public Object get() {
        return value;
    }

    /**
     * @return the current value
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (value.floatValue() != 0.0) {
            return Util.displayFloat(value) + unit;
        } else {
            return Util.displayFloat(value);
        }
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssResolution &&
                this.value.equals(((CssResolution) value).value) &&
                unit.equals(((CssResolution) value).unit));
    }

    private Float value;
    private String unit;
    private static Float defaultValue = new Float(0);

}

