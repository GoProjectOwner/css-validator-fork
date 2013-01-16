// $Id: CssProfile.java,v 1.2 2011-09-17 06:02:27 ylafon Exp $
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.util;

public enum CssProfile {
    EMPTY(""), NONE("none"), SVG("svg"), SVGBASIC("svgbasic"), SVGTINY("svgtiny"),
    MOBILE("mobile"), TV("tv"), ATSCTV("atsc-tv");
    private final String profile;

    CssProfile(String version) {
        this.profile = version;
    }

    public String toString() {
        return profile;
    }

    public static CssProfile resolve(ApplContext ac, String s)
  //          throws InvalidParamException {
    {
        for (CssProfile p : CssProfile.values()) {
            if (p.toString().equals(s)) {
                return p;
            }
        }
        // TODO this or get the default ???
 //       throw new InvalidParamException("invalid-level", s, ac);
        return EMPTY;
    }
}

