// $Id: CssSemitone.java,v 1.1 2013-01-11 10:06:21 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2013.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

/**
 * @since CSS3
 */
public class CssSemitone extends CssCheckableValue {

	public static final int type = CssTypes.CSS_SEMITONE;

	public final int getType() {
		return type;
	}


	private BigDecimal value;
	protected String unit;

	/**
	 * Create a new CssVolume
	 */
	public CssSemitone() {
		value = BigDecimal.ZERO;
	}

	/**
	 * Set the value of this length.
	 *
	 * @param s the string representation of the length.
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The unit is incorrect
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

		// TODO check the  if (!BigDecimal.ZERO.equals(value))) test
		// that was here earlier
		// seems legit to always test the unit no matter the value
		switch (ac.getCssVersion()) {
			case CSS3:
				CssUnitsCSS3.parseSemitoneUnit(unit_str, this, ac);
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
	 * set the native value
	 *
	 * @param v the BigDecimal
	 */
	public void setValue(BigDecimal v) {
		value = v;
	}

	// return self
	public CssSemitone getVolume() throws InvalidParamException {
		return this;
	}

	/**
	 * Returns the current value
	 */
	public Object get() {
		// TODO this is old ugly crap, needed for not breaking everything
		// remove as soon as reference to get is removed...
		return new Float(value.floatValue());
	}

	/**
	 * return the float value
	 */
	public float floatValue() {
		return value.floatValue();
	}

	/**
	 * Returns true is the value is positive of null
	 *
	 * @return a boolean
	 */
	public boolean isPositive() {
		return (value.signum() >= 0);
	}

	/**
	 * Returns true is the value is positive of null
	 *
	 * @return a boolean
	 */
	public boolean isStrictlyPositive() {
		return (value.signum() == 1);
	}

	/**
	 * Returns true is the value is zero
	 *
	 * @return a boolean
	 */
	public boolean isZero() {
		return (BigDecimal.ZERO.compareTo(value) == 0);
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
		return value.toPlainString() + unit;
	}

	/**
	 * Compares two values for equality.
	 *
	 * @param value The other value.
	 */
	public boolean equals(Object value) {
		return (value instanceof CssSemitone &&
				this.value.equals(((CssSemitone) value).value) &&
				unit.equals(((CssSemitone) value).unit));
	}


	/**
	 * check if the value is positive or null
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 * @throws org.w3c.css.util.InvalidParamException
	 *
	 */
	public void checkPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		if (!isPositive()) {
			throw new InvalidParamException("negative-value",
					toString(), property.getPropertyName(), ac);
		}
	}

	/**
	 * check if the value is strictly positive
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 * @throws org.w3c.css.util.InvalidParamException
	 *
	 */
	public void checkStrictPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		if (!isStrictlyPositive()) {
			throw new InvalidParamException("strictly-positive",
					toString(), property.getPropertyName(), ac);
		}
	}

	/**
	 * warn if the value is not positive or null
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 */
	public void warnPositiveness(ApplContext ac, CssProperty property) {
		if (!isPositive()) {
			ac.getFrame().addWarning("negative", toString());
		}
	}
}

