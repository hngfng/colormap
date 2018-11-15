/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap;

import java.awt.Color;
import java.awt.Paint;

/**
 *
 * @author allanlee
 */
public class ColorMapBean {

    private double min;
    private double max;
    private Paint pt;
    private String text;
    private int type = -1;//-1--color,0...-paint

    public ColorMapBean() {
    }

    public ColorMapBean(double min, double max, Paint pt, String text, int type) {
        this.min = min;
        this.max = max;
        this.pt = pt;
        this.text = text;
        this.type = type;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Paint getPt() {
        return pt;
    }

    public void setPt(Paint pt) {
        this.pt = pt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return paint 的类型
     * <p>
     * -1------Color</p>
     * <p>
     * 大于1------pattern的索引，Util.getPatternBeans()</p>
     */
    public int getType() {
        if (pt instanceof Color) {
            return -1;
        }
        return type;
    }

    /**
     * @param type paint 的类型
     * *<p>
     * -1------Color</p>
     * <p>
     * 大于1------pattern的索引，Util.getPatternBeans()</p>
     */
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ColorMapBean{" + "min=" + min + ", max=" + max + ", pt=" + pt + ", text=" + text + ", type=" + type + '}';
    }

}
