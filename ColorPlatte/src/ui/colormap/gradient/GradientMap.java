/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.gradient;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ui.colormap.ColorMap;
import ui.colormap.panel.ColorMapChangeLisener;
import ui.colormap.MapType;
import ui.file.color.Util;

/**
 *
 * @author allanlee
 */
public class GradientMap extends ColorMap{

    private Paint[] paints;
    private int[] paintTypes;
    private String[] texts;
    private int gradient = 0;
    private PropertyChangeSupport support;
    private int intervals = 1;
    private String scale=LINER;//0--线性，1--对数,2--自定义 
    private String colorMapType = MANUAL;//0---手动，1--梯度，2--间隔梯度
    public static final  String MANUAL="manual",GRADIENT="gradient",INTERVAL="interval";
    public static final String LINER="liner",LOG="log",CUSTOM="Custom";
    /**
     * 色标类型
     */
    public static final int MAP_MANUAL = 0, MAP_GRADIENT = 1, MAP_INTERVAL_GRADIENT = 2;
    /**
     * 刻度类型
     */
    public static final int TICK_LINER = 0, TICK_LOG = 1, TICK_DEFINE = 2;

    public GradientMap() {
        support = new PropertyChangeSupport(this);
        mapType = MapType.Gradient;
    }

    public void addColorMapListener(ColorMapChangeLisener lisener) {
        support.addPropertyChangeListener(lisener);
    }

    private void fireColorMapChangeListener() {
        support.firePropertyChange(new PropertyChangeEvent(this, "tick", 1, 2));
        support.firePropertyChange(new PropertyChangeEvent(this, "rects", 1, 2));
    }
    public void setStart(double start) {
        support.firePropertyChange(new PropertyChangeEvent(this, "tick", 1, 2));
      super.setStart(start);
    }
    public void setEnd(double end) {
        super.setEnd(end);
        support.firePropertyChange(new PropertyChangeEvent(this, "tick", 1, 2));
    }

    public String getType() {
        return scale;
    }

    public void setType(String type) {
        this.scale = type;
        fireColorMapChangeListener();
    }

    public int getIntervals() {
        return intervals;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
        fireColorMapChangeListener();
    }

    public void setTail(int tail) {
        super.setTail(tail);
        support.firePropertyChange(new PropertyChangeEvent(this, "tick", 1, 2));
    }

    public Paint[] getPaints() {
        return paints;
    }

    public int[] getPaintTypes() {
        return paintTypes;
    }

    public String[] getTexts() {
        return texts;
    }

    public String getColorMapType() {
        return colorMapType;
    }

    public void setColorMapType(String colorMapType) {
        this.colorMapType = colorMapType;
    }

    public void setPaints(TickRectangle[] tickRectangles, Color... clrs) {
        List<Paint> colorArray = new ArrayList<>();
        if (clrs != null) {
            colorArray.clear();
            colorArray.addAll(Arrays.asList(clrs));
        } else {
            for (TickRectangle tickRectangle : tickRectangles) {
                Paint p = tickRectangle.getAttribute().getFillColor();
                colorArray.add(p);
            }
        }
        paints = colorArray.toArray(new Paint[0]);
    }
    public void clear()
    {
        paints = new Paint[0];
    }
    public void save(String path, TickRectangle[] tickRectangles, Color... clrs) {
        JSONObject json = new JSONObject();
        json.put("maptype", mapType.toString());
        json.put("start", getStart());
        json.put("end", getEnd());
        json.put("intervals", intervals);
        json.put("type", scale);
        json.put("colorMapType", colorMapType);
        json.put("tail", getTail());
        JSONArray colorArray = new JSONArray();
        JSONArray typeArray = new JSONArray();
        JSONArray textArray = new JSONArray();

        for (TickRectangle tickRectangle : tickRectangles) {
            Paint p = tickRectangle.getAttribute().getFillColor();
            if (p instanceof Color) {
                colorArray.add(((Color) p).getRGB());
            } else {
                colorArray.add(Color.white.getRGB());
            }
            typeArray.add(tickRectangle.getPatternType());
            textArray.add(tickRectangle.getText());
        }
        if (clrs != null) {
            colorArray.clear();
            for (Color clr : clrs) {
                colorArray.add(clr.getRGB());
            }
        }
        json.put("colors", colorArray);
        json.put("paintTypes", typeArray);
        json.put("texts", textArray);
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            json.write(writer);
        } catch (IOException e) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(GradientMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void load(String path) {
        if (!path.endsWith(".cmp")) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            JSONObject fromObject = JSONObject.fromObject(reader.readLine());
            setMapType(MapType.valueOf(fromObject.getString("maptype")));
            setStart(fromObject.getDouble("start"));
            setEnd(fromObject.getDouble("end"));
            setIntervals(fromObject.getInt("intervals"));
            scale = fromObject.getString("type");
            colorMapType = fromObject.getString("colorMapType");
            setTail(fromObject.getInt("tail"));

            JSONArray paintsTypes = fromObject.getJSONArray("paintTypes");

            JSONArray colorArray = fromObject.getJSONArray("colors");
            JSONArray textsArray = fromObject.getJSONArray("texts");
            paintTypes = new int[paintsTypes.size()];
            paints = new Paint[colorArray.size()];
            texts = new String[textsArray.size()];
            if (colorMapType.equals(GRADIENT)) {
                for (int i = 0; i < colorArray.size(); i++) {
                    paints[i] = new Color(colorArray.getInt(i));
                }
                for (int i = 0; i < paintsTypes.size(); i++) {
                    texts[i] = textsArray.getString(i);
                    paintTypes[i] = paintsTypes.getInt(i);
                }
            } else {
                for (int i = 0; i < paintsTypes.size(); i++) {
                    int idx = paintsTypes.getInt(i);
                    if (idx < 0) //pattern
                    {
                        paints[i] = new Color(colorArray.getInt(i));
                    } else {//color
                        String path1 = Util.getBeans()[idx].getPath();
                        BufferedImage image = ImageIO.read(new FileInputStream(path1));
                        TexturePaint paint = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
                        paints[i] = paint;
                    }
                    texts[i] = textsArray.getString(i);
                    paintTypes[i] = paintsTypes.getInt(i);
                }
            }
            reader.close();
        } catch (IOException ex) {
        }
    }
}
