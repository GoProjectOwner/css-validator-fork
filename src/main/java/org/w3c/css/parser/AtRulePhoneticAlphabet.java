//
// $Id: AtRulePhoneticAlphabet.java,v 1.3 2011-10-21 01:49:08 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRulePhoneticAlphabet.java
 * $Id: AtRulePhoneticAlphabet.java,v 1.3 2011-10-21 01:49:08 ylafon Exp $
 */
package org.w3c.css.parser;

import org.w3c.css.util.ApplContext;

/**
 *
 * @version $Revision: 1.3 $
 * @author  Philippe Le Hegaret
 */
public class AtRulePhoneticAlphabet extends AtRule {
    static int internal = 0;
    int hash;
    String alphabet = "ipa";

    /**
     * Create a new AtRulePhoneticAlphabet
     */
    public AtRulePhoneticAlphabet() {
       hash = ++internal;
    }


    /**
     * Returns the at rule keyword
     */
    public String keyword() {
		return "phonetic-alphabet";
    }

    public void addAlphabet(String alphabet, ApplContext ac) {
		this.alphabet = alphabet;
	}

    /**
     * The second must be exactly the same of this one
     */
    public boolean canApply(AtRule atRule) {
		return (atRule instanceof AtRulePhoneticAlphabet);
    }

    /**
     * Return true if other is an instance of AtRUlePhoneticAlphabet
     */
    public boolean equals(Object other) {
		return (other instanceof AtRulePhoneticAlphabet);
    }

    /**
     * The second must only match this one
     */
    public boolean canMatch(AtRule atRule) {
		return (atRule instanceof AtRulePhoneticAlphabet);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		return "@" + keyword() + " " + alphabet;
    }

    public int hashCode() {
		return hash;
    }

	public boolean isEmpty() {
		return true;
	}

}
