//
// $Id: CssUnitsCSS2.java,v 1.1 2012-09-07 14:55:25 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS2-20080411/syndata.html#values
 */
public class CssUnitsCSS2 {
	private static final String[] relative_length_units = {
			"em", "ex", "px"
	};

	private static final String[] absolute_length_units = {
			"in", "cm", "mm", "pt", "pc"
	};

	private static final String[] angle_units = {
			"deg", "grad", "rad"
	};

	private static final String[] time_units = {
			"ms", "s"
	};

	private static final String[] frequency_units = {
			"kHz", "Hz"
	};

	protected static String getRelativeLengthUnit(String unit) {
		for (String s : relative_length_units) {
			if (s.equals(unit)) {
				return s;
			}
		}
		return null;
	}

	protected static String getAbsoluteLengthUnit(String unit) {
		for (String s : absolute_length_units) {
			if (s.equals(unit)) {
				return s;
			}
		}
		return null;
	}

	protected static void parseLengthUnit(String unit, CssLength length, ApplContext ac)
			throws InvalidParamException {
		String matchedUnit = getRelativeLengthUnit(unit);
		if (matchedUnit != null) {
			length.absolute = false;
		} else {
			matchedUnit = getAbsoluteLengthUnit(unit);
			if (matchedUnit == null) {
				throw new InvalidParamException("unit", unit, ac);
			}
			length.absolute = true;
		}
		length.unit = matchedUnit;
	}
}
