//
// $Id: Css2Style.java,v 1.8 2012-12-06 18:58:14 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css2.table;

/**
 * @version $Revision: 1.8 $
 */
public class Css2Style extends org.w3c.css.properties.css2.Css2Style {

    RowSpan rowSpan;
    RowSpanATSC rowSpanATSC;
    ColumnSpan columnSpan;
    ColumnSpanATSC columnSpanATSC;
    SpeakHeader speakHeader;
    SpeakHeaderATSC speakHeaderATSC;

    /**
     * Get the row-span property
     */
    public final RowSpan getRowSpan() {
	if (rowSpan == null) {
	    rowSpan = (RowSpan)
		style.CascadingOrder(new RowSpan(), style, selector);
	}
	return rowSpan;
    }

    public final RowSpanATSC getRowSpanATSC() {
	if (rowSpanATSC == null) {
	    rowSpanATSC = (RowSpanATSC)
		style.CascadingOrder(new RowSpanATSC(), style, selector);
	}
	return rowSpanATSC;
    }

    /**
     * Get the column-span property
     */

    public final ColumnSpan getColumnSpan() {
	if (columnSpan == null) {
	    columnSpan = (ColumnSpan)
		style.CascadingOrder(new ColumnSpan(), style, selector);
	}
	return columnSpan;
    }

    public final ColumnSpanATSC getColumnSpanATSC() {
	if (columnSpanATSC == null) {
	    columnSpanATSC = (ColumnSpanATSC)
		style.CascadingOrder(new ColumnSpanATSC(), style, selector);
	}
	return columnSpanATSC;
    }

    /**
     * Get the emtpy-header property
     */
    public final SpeakHeader getSpeakHeader() {
	if (speakHeader == null) {
	    speakHeader = (SpeakHeader)
		style.CascadingOrder(new SpeakHeader(), style, selector);
	}
	return speakHeader;
    }

    public final SpeakHeaderATSC getSpeakHeaderATSC() {
	if (speakHeaderATSC == null) {
	    speakHeaderATSC = (SpeakHeaderATSC)
		style.CascadingOrder(new SpeakHeaderATSC(), style, selector);
	}
	return speakHeaderATSC;
    }


}
