/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.patterns;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JLabel;

/**
 *
 * @author allanlee
 */
public class PaintLabel extends JLabel {

    private Paint p;

    @Override
    protected void paintComponent(Graphics g) {
        if (p != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if (getBorder() != null) {
                getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());
            }
            g2d.dispose();
        }
        super.paintComponent(g);
    }

    public void setP(Paint p) {
        this.p = p;
    }

}
