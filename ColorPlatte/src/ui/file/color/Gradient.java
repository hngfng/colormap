/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.file.color;

import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author Dell
 */
public class Gradient {
    /*根据颜色RGBA生成Image*/
    public static BufferedImage buildMapImage(Paint[] colors) {
        BufferedImage sourceImage = new BufferedImage(colors.length, 1, 1);
        WritableRaster raster = sourceImage.getRaster();
        int[] colorVal = new int[4];
        for (int i = 0; i < colors.length; i++) {
            if(colors[i] instanceof Color){
            Color clr = (Color) colors[i];
            colorVal[0] = clr.getRed();
            colorVal[1] = clr.getGreen();
            colorVal[2] = clr.getBlue();
            colorVal[3] = clr.getAlpha();
            raster.setPixel(i, 0, colorVal);}
        }
        BufferedImage desImage = new BufferedImage(colors.length, 25, 1);
        AffineTransform transform = new AffineTransform();
        transform.setToScale(256 / colors.length, 25.0D);
        AffineTransformOp imageOp = new AffineTransformOp(transform, null);
        imageOp.filter(sourceImage, desImage);

        return desImage;
    }

    /*根据颜色值生成Image*/
    public static BufferedImage buildMapImage(int[] paints) {
        BufferedImage image = new BufferedImage(paints.length, 1, BufferedImage.TYPE_4BYTE_ABGR);

        WritableRaster raster = image.getRaster();
        int[] colorVal = new int[4];
        for (int i = 0; i < paints.length; i++) {
            colorVal[0] = ( paints[i] >> 16) & 0xFF;
            colorVal[1] = (paints[i] >> 8) & 0xFF;
            colorVal[2] = (paints[i] >> 0) & 0xFF;
            colorVal[3] = (paints[i] >> 24) & 0xff;
            raster.setPixel(i, 0, colorVal);
        }
        BufferedImage desImage = new BufferedImage(100, 25, 1);
        AffineTransform transform = new AffineTransform();
        transform.setToScale(100.0d / paints.length, 25.0D);
        AffineTransformOp imageOp = new AffineTransformOp(transform, null);
        imageOp.filter(image, desImage);
        return image;
    }

    public static Color[] createGradient(final Color one, final Color two,
            final int numSteps) {
        int r1 = one.getRed();
        int g1 = one.getGreen();
        int b1 = one.getBlue();
        int a1 = one.getAlpha();

        int r2 = two.getRed();
        int g2 = two.getGreen();
        int b2 = two.getBlue();
        int a2 = two.getAlpha();

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int newA = 0;

        Color[] gradient = new Color[numSteps];
        double iNorm;
        for (int i = 0; i < numSteps; i++) {
            iNorm = i / (double) numSteps; // a normalized [0:1] variable
            newR = (int) (r1 + iNorm * (r2 - r1));
            newG = (int) (g1 + iNorm * (g2 - g1));
            newB = (int) (b1 + iNorm * (b2 - b1));
            newA = (int) (a1 + iNorm * (a2 - a1));
            gradient[i] = new Color(newR, newG, newB, newA);
        }

        return gradient;
    }

    public static Color[] createMultiGradient(Color[] colors, int numSteps) {

        int numSections = colors.length - 1;
        int gradientIndex = 0; 
        // gradient
        Color[] gradient = new Color[numSteps];
        Color[] temp;

        if (numSections <= 0) {
            throw new IllegalArgumentException();
        }

        for (int section = 0; section < numSections; section++) {
            temp = createGradient(colors[section], colors[section + 1],
                    numSteps / numSections);
            for (int i = 0; i < temp.length; i++) {
                gradient[gradientIndex++] = temp[i];
            }
        }

        if (gradientIndex < numSteps) {
            for (; gradientIndex < numSteps; gradientIndex++) {
                gradient[gradientIndex] = colors[colors.length - 1];
            }
        }
        return gradient;
    }
}
