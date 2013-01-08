//
// $Id: ACssStyle.java,v 1.26 2013-01-08 11:13:38 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warnings;

/**
 * @version $Revision: 1.26 $
 */
public class ACssStyle extends Css1Style {


	ACssVoiceVolume acssVoiceVolume;
	ACssVoiceRate acssVoiceRate;
	ACssVoicePitchRange acssVoicePitchRange;


	public ACssVoiceVolume getVoiceVolume() {
		if (acssVoiceVolume == null) {
			acssVoiceVolume =
					(ACssVoiceVolume) style.CascadingOrder(new ACssVoiceVolume(), style, selector);
		}
		return acssVoiceVolume;
	}

	public ACssVoiceRate getVoiceRate() {
		if (acssVoiceRate == null) {
			acssVoiceRate =
					(ACssVoiceRate) style.CascadingOrder(new ACssVoiceRate(), style, selector);
		}
		return acssVoiceRate;
	}

	public ACssVoicePitchRange getVoicePitchRange() {
		if (acssVoicePitchRange == null) {
			acssVoicePitchRange =
					(ACssVoicePitchRange) style.CascadingOrder(new ACssVoicePitchRange(), style, selector);
		}
		return acssVoicePitchRange;
	}

	/**
	 * Find conflicts in this Style
	 *
	 * @param warnings     For warnings reports.
	 * @param allSelectors All contexts is the entire style sheet.
	 */
	public void findConflicts(ApplContext ac, Warnings warnings,
							  CssSelectors selector, CssSelectors[] allSelectors) {
		super.findConflicts(ac, warnings, selector, allSelectors);
		/*
		if (acssVoiceFamily != null) {
			if (!acssVoiceFamily.containsGenericFamily()) {
				warnings.addWarning(new Warning(acssVoiceFamily,
						"no-generic-family", 2, ac));
			}
			if (acssVoiceFamily.withSpace) {
				warnings.addWarning(new Warning(acssVoiceFamily,
						"with-space", 1, ac));
			}
		}
		*/
	  /* TODO move to CSS2

		if ((acssPause.getBefore() != null) &&
				!acssPause.getBefore().isSoftlyInherited() &&
				!(acssPause.getBefore().get() instanceof CssPercentage)) {
			// Using relative units gives more robust stylesheets
			warnings.addWarning(new Warning(acssPause.getBefore(),
					"relative", 2, ac));
		}
		if ((acssPause.getAfter() != null) &&
				!acssPause.getAfter().isSoftlyInherited() &&
				!(acssPause.getAfter().get() instanceof CssPercentage)) {
			// Using relative units gives more robust stylesheets
			warnings.addWarning(new Warning(acssPause.getAfter(),
					"relative", 2, ac));
		}

      */
	}

}
