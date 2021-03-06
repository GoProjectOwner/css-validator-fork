//
// $Id: FontProperty.java,v 1.2 2010-01-05 13:49:47 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.properties.css2.font;

import org.w3c.css.properties.css.CssProperty;

/**
 * @version $Revision: 1.2 $
 */
public abstract class FontProperty extends CssProperty {

  /**
   * Returns true if the property is inherited
   */
  public boolean Inherited() {
    return false;
  }

}
