/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap;

import ui.colormap.point.PointMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import net.sf.json.JSONObject;
import ui.colormap.gradient.GradientMap;
import ui.colormap.step.StepColorMap;

/**
 *
 * @author allanlee
 */
public class ColorMapLoader {
     public static  ColorMap  load(String path) {
        if (!path.endsWith(".cmp")) {
            return null;
        }
        ColorMap map = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            JSONObject fromObject = JSONObject.fromObject(reader.readLine());
            String mt = fromObject.getString("maptype");
            MapType mapType = MapType.valueOf(mt);
            switch (mapType) {
                case Gradient:
                    map = new GradientMap();
                    break;
                case Step:
                    map = new StepColorMap();
                    break;
                default:
                    map = new PointMap();
                    break;
            }
            map.load(path);
            reader.close();
        } catch (IOException ex) {
        }
        return map;
    }
}
