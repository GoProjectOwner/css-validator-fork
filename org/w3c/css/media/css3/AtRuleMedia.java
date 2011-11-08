// $Id: AtRuleMedia.java,v 1.3 2011-11-08 17:07:01 ylafon Exp $
//
// (c) COPYRIGHT MIT, ERCIM and Keio University
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.media.css3;

import org.w3c.css.media.Media;
import org.w3c.css.media.MediaFeature;
import org.w3c.css.parser.AtRule;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2010/CR-css3-mediaqueries-20100727/
 */

public class AtRuleMedia extends org.w3c.css.media.AtRuleMedia {
    static final String[] mediaCSS21 = {
            "all", "braille", "embossed", "handheld", "print", "projection",
            "screen", "speech", "tty", "tv"
    };

    static final String[] deprecatedMedia = {
            "aural"
    };

    /**
     * Adds a medium.
     *
     * @throws InvalidParamException the medium doesn't exist
     */
    public org.w3c.css.media.AtRuleMedia addMedia(String restrictor, String medium,
                                                  ApplContext ac) throws InvalidParamException {
        Media media = new Media();
        if (restrictor != null) {
            if ("not".equalsIgnoreCase(restrictor)) {
                media.setNot(true);
            } else if ("only".equalsIgnoreCase(restrictor)) {
                media.setOnly(true);
            }
        }
        if (medium == null) {
            allMedia.add(media);
            return this;
        }
        medium = medium.toLowerCase();
        for (String s : mediaCSS21) {
            if (medium.equals(s)) {
                media.setMedia(s);
                allMedia.add(media);
                return this;
            }
        }
        for (String s : deprecatedMedia) {
            if (medium.equals(s)) {
                // we add a warning as it has been deprecated
                ac.getFrame().addWarning("deprecatedmedia", medium);
                allMedia.add(new Media(s));
                return this;
            }
        }
        // FIXME we can check if media exists in other CSS versions
        throw new InvalidParamException("media", medium, ac);
    }

    /**
     * @param feature
     * @param ac
     * @throws InvalidParamException
     */
    public void addMediaFeature(MediaFeature feature, ApplContext ac)
            throws InvalidParamException {
        Media latest = allMedia.get(allMedia.size() - 1);
        latest.addFeature(feature);
    }

    /**
     * The second must be exactly the same as this one
     * so we check that each one match each other
     */
    public boolean canApply(AtRule atRule) {
        try {
            org.w3c.css.media.AtRuleMedia second = (org.w3c.css.media.AtRuleMedia) atRule;
            return (canMatch(second) && second.canMatch(this));
        } catch (ClassCastException cce) {
            return false;
        }
    }

    /**
     * The second must only match this one
     */
    public boolean canMatch(AtRule atRule) {
        try {
            org.w3c.css.media.AtRuleMedia second = (org.w3c.css.media.AtRuleMedia) atRule;
            ArrayList<Media> otherMediaList = second.getMediaList();

            for (Media m : otherMediaList) {
                if (!allMedia.contains(m)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException cce) {
            return false;
        }
    }

    public String getValueString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Media m : allMedia) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(m.toString());
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('@').append(keyword()).append(' ');
        sb.append(getValueString());
        return sb.toString();
    }
}

