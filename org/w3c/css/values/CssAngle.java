//
// $Id: CssAngle.java,v 1.12 2012-09-07 20:41:11 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

/**
 * <H3>Angle</H3>
 * <p/>
 * <P>Angle units are used with aural cascading style sheets.
 * <p/>
 * <P>These are the legal angle units:
 * <p/>
 * <UL>
 * <LI>deg: degrees
 * <LI>grad: gradians
 * <LI>rad: radians
 * </UL>
 * <p/>
 * <p>Values in these units may be negative. They should be normalized to the
 * range 0-360deg by the UA. For example, -10deg and 350deg are equivalent.
 *
 * @version $Revision: 1.12 $
 */
public class CssAngle extends CssValue implements CssValueFloat {

	public static final int type = CssTypes.CSS_ANGLE;

	public final int getType() {
		return type;
	}

	private static final BigDecimal deg360;

	static {
		deg360 = BigDecimal.valueOf(360);
	}

	private BigDecimal value;
	protected BigDecimal factor = BigDecimal.ONE;
	String unit;

	/**
	 * Create a new CssAngle.
	 */
	public CssAngle() {
		this(BigDecimal.ZERO);
	}

	/**
	 * Create a new CssAngle
	 */
	public CssAngle(float v) {
		this(new BigDecimal(v));
	}

	/**
	 * Create a new CssAngle
	 */
	public CssAngle(BigDecimal angle) {
		value = angle;
	}

	/**
	 * Set the value of this angle.
	 *
	 * @param s  The string representation of the angle
	 * @param ac For errors and warnings reports
	 * @throws InvalidParamException The unit is incorrect
	 */
	public void set(String s, ApplContext ac) throws InvalidParamException {
		String low_s = s.toLowerCase();
		int length = low_s.length();
		int unitIdx = length - 1;
		char c = low_s.charAt(unitIdx);
		while (unitIdx > 0 && c <= 'z' && c >= 'a') {
			c = low_s.charAt(--unitIdx);
		}
		if (unitIdx == length - 1) {
			throw new InvalidParamException("unit", s, ac);
		}
		// we go back to the beginning of the unit
		unitIdx++;
		String unit_str = low_s.substring(unitIdx, length);
		// let's test the unit
		switch (ac.getCssVersion()) {
			case CSS2:
				CssUnitsCSS2.parseAngleUnit(unit_str, this, ac);
				break;
			case CSS21:
				CssUnitsCSS21.parseAngleUnit(unit_str, this, ac);
				break;
			case CSS3:
				CssUnitsCSS3.parseAngleUnit(unit_str, this, ac);
				break;
			default:
				throw new InvalidParamException("unit", s, ac);
		}
		try {
			value = new BigDecimal(low_s.substring(0, unitIdx));
		} catch (NumberFormatException nex) {
			throw new InvalidParamException("invalid-number",
					low_s.substring(0, unitIdx), ac);
		}
	}

	/**
	 * Returns the current value
	 */
	public Object get() {
		return value;
	}

	public float getValue() {
		return value.floatValue();
	}

	/**
	 * Returns the current value
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		return value.toPlainString() + ((BigDecimal.ZERO.equals(value)) ? "deg" : unit);
	}

	/**
	 * Compares two values for equality.
	 *
	 * @param value The other value.
	 */
	public boolean equals(Object value) {
		return (value instanceof CssAngle &&
				this.value.equals(((CssAngle) value).value) &&
				unit.equals(((CssAngle) value).unit));
	}

	private BigDecimal normalize(BigDecimal degree) {
		degree = degree.remainder(deg360);
		if (degree.compareTo(BigDecimal.ZERO) < 0) {
			degree.add(deg360);
		}
		return degree;
	}

	//@@FIXME I should return the remainder for all ...

	public float getDegree() {
		return normalize(value.multiply(factor)).floatValue();
	}

}

