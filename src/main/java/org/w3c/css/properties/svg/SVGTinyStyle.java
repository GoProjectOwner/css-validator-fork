//
// $Id: SVGTinyStyle.java,v 1.5 2011-09-09 12:16:46 ylafon Exp $
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.properties.css3.Css3Style;

public class SVGTinyStyle extends Css3Style {

    FillRule fillRule;
    StrokeLinejoin strokeLinejoin;
    StrokeLineCap strokeLineCap;
    StrokeMiterLimit strokeMiterLimit;
    StrokeWidth strokeWidth;
    StrokeDashOffset strokeDashOffset;
    StrokeDashArray strokeDashArray;
    Stroke stroke;
    Fill fill;

    public FillRule getFillRule() {
	if (fillRule == null) {
	    fillRule =
		(FillRule) style.CascadingOrder (
		    new FillRule(), style, selector);
	}
	return fillRule;
    }
    
    public StrokeLinejoin getStrokeLinejoin() {
	if (strokeLinejoin == null) {
	    strokeLinejoin =
		(StrokeLinejoin) style.CascadingOrder (
		    new StrokeLinejoin(), style, selector);
	}
	return strokeLinejoin;
    }

    public StrokeLineCap getStrokeLineCap() {
	if (strokeLineCap == null) {
	    strokeLineCap =
		(StrokeLineCap) style.CascadingOrder (
		    new StrokeLineCap(), style, selector);
	}
	return strokeLineCap;
    }

    public StrokeMiterLimit getStrokeMiterLimit() {
	if (strokeMiterLimit == null) {
	    strokeMiterLimit =
		(StrokeMiterLimit) style.CascadingOrder (
		    new StrokeMiterLimit(), style, selector);
	}
	return strokeMiterLimit;
    }

    public StrokeWidth getStrokeWidth() {
	if (strokeWidth == null) {
	    strokeWidth =
		(StrokeWidth) style.CascadingOrder (
		    new StrokeWidth(), style, selector);
	}
	return strokeWidth;
    }

    public StrokeDashOffset getStrokeDashOffset() {
	if (strokeDashOffset == null) {
	    strokeDashOffset =
		(StrokeDashOffset) style.CascadingOrder (
		    new StrokeDashOffset(), style, selector);
	}
	return strokeDashOffset;
    }

    public StrokeDashArray getStrokeDashArray() {
	if (strokeDashArray == null) {
	    strokeDashArray =
		(StrokeDashArray) style.CascadingOrder (
		    new StrokeDashArray(), style, selector);
	}
	return strokeDashArray;
    }

    public Stroke getStroke() {
	if (stroke == null) {
	    stroke =
		(Stroke) style.CascadingOrder (
		    new Stroke(), style, selector);
	}
	return stroke;
    }

    public Fill getFill() {
	if (fill == null) {
	    fill =
		(Fill) style.CascadingOrder (
		    new Fill(), style, selector);
	}
	return fill;
    }

    /**
     * Returns the name of the actual selector
     */
    public String getSelector()
    {
	return (selector.getElement().toLowerCase());
    }

}
