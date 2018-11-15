/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.file.color;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.SimpleCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import ui.colormap.gradient.GradientMap;
import ui.patterns.PatternBean;
import ui.patterns.PatternModel;

/**
 *
 * @author Dell
 */
public class Util {

    private static final Color[] initColors = new Color[100];

    static {
        for (int i = 0; i < initColors.length; i++) {
            initColors[i] = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        }
    }

    public static Color[] getColors() {
        return initColors;
    }

    public static  PatternBean[] getBeans() {
        List<PatternBean> beans = new ArrayList<>();
        String path = PatternModel.class.getResource("/ui/patterns/rock/").getPath();
        File file = new File(path);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            int i = 0;
            for (File fl : listFiles) {
                String name = fl.getName().substring(0, fl.getName().indexOf("."));
                beans.add(new PatternBean(i, name, fl.getPath()));
            }
            
        }
        return beans.toArray(new PatternBean[0]);
    }


    //根据模型值得到color的索引
    public static int[] getIndexs(double min, double max, double start, double end, double[] x, String type) {
        int[] idxs = new int[x.length];
        WeightedObservedPoints points = new WeightedObservedPoints();
        points.add(min, 0);
        points.add(max, 255);
        double[] result = Util.fitting(points, type.equals(GradientMap.LINER)?0:1, 1);
        double[] tickValues = Util.calculateY(0, result, x);
        double factor = (255) / (end - start);
        for (int i = 0; i < tickValues.length; i++) {
            double tickValue = tickValues[i];
            int idx = (int) ((tickValue - start) * factor);
            idxs[i] = idx;
        }
        return idxs;
    }

    public static double[] fitting(WeightedObservedPoints points, int type, int degree) {

        if (points == null || points.toList().size() <= 0) {
            return null;
        }
        double[] start = new double[]{0, 0};
        switch (type) {
            case 0: {
                PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);
                return fitter.fit(points.toList());
            }
            case 1: {
                LogarithmicParametricUnivariateFunction function = new LogarithmicParametricUnivariateFunction();
                SimpleCurveFitter fitter = SimpleCurveFitter.create(function, start);
                return fitter.fit(points.toList());
            }
        }
        return null;
    }

    public static double[] calculateY(int type, double[] results, double[] x) {
        double[] Y = new double[x.length];
        int cnt = x.length;
        switch (type) {
            case 0:
                PolynomialFunction function1 = new PolynomialFunction(results);
                for (int i = 0; i < cnt; i++) {
                    Y[i] = function1.value(x[i]);
                }
                break;
            case 1:
                ParametricUnivariateFunction function = new LogarithmicParametricUnivariateFunction();
                for (int i = 0; i < cnt; i++) {
                    Y[i] = function.value(x[i], results);
                }
                break;

        }
        return Y;
    }

}

class LogarithmicParametricUnivariateFunction implements ParametricUnivariateFunction {

    @Override
    //Compute the value of the function.
    public double value(double d, double... doubles) {
        return doubles[0] * Math.log(d) + doubles[1];
    }

    @Override
    //Compute the gradient of the function with respect to its parameters.
    public double[] gradient(double d, double... doubles) {
        final double[] gradient = new double[doubles.length];
        gradient[0] = Math.log(d);
        gradient[1] = 1;
        return gradient;
    }
}
