/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.gradient;

import com.interactive.jcarnac2d.model.shapes.cgRectangle;

/**
 *
 * @author allanlee
 */
public class TickRectangle extends cgRectangle {

    private int patternType = -1;
    private String text;

    TickRectangle(double x, double y, double maxX, double maxY) {
        super(x, y, maxX, maxY);
    }

    public int getPatternType() {
        return patternType;
    }

    public void setPatternType(int patternType) {
        this.patternType = patternType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
