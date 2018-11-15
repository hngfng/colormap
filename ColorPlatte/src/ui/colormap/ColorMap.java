/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ui.colormap.gradient.GradientMap;
import ui.file.color.Util;

/**
 *
 * @author allanlee
 */
public abstract  class ColorMap {

    private List<ColorMapBean> beans = new ArrayList<>();
    private double start = 0;
    private double end = 255;
    private int tail = 2;//小数位数
    private boolean autoColor = true;
    protected MapType mapType = MapType.Gradient;//0--gendient,1---step,2---point
    private NumberFormat format = NumberFormat.getInstance();

    public ColorMap() {
        format.setMaximumIntegerDigits(tail);
    }

    public List<ColorMapBean> getBeans() {
        return beans;
    }

    public void setBeans(List<ColorMapBean> beans) {
        this.beans = beans;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
        format.setMaximumFractionDigits(tail);
    }

    public boolean isAutoColor() {
        return autoColor;
    }

    public void setAutoColor(boolean autoColor) {
        this.autoColor = autoColor;
    }

    public String format(double value) {
        return format.format(value);
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    public void save(String path) {
        JSONObject json = new JSONObject();
        json.put("maptype", mapType.toString());
        json.put("start", start);
        json.put("end", end);
        json.put("tail", tail);
        JSONArray colorArray = new JSONArray();
        JSONArray typeArray = new JSONArray();
        JSONArray textArray = new JSONArray();
        JSONArray valueArray = new JSONArray();
        for (ColorMapBean bean : beans) {
            Paint p = bean.getPt();
            if (bean.getPt() instanceof Color) {
                colorArray.add(((Color) p).getRGB());
            } else {
                colorArray.add(Color.white.getRGB());
            }
            typeArray.add(bean.getType());
            textArray.add(bean.getText());
            valueArray.add(bean.getMin());
            valueArray.add(bean.getMax());
        }
        json.put("colors", colorArray);
        json.put("values", valueArray);
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

    public void load(String path) {
        if (!path.endsWith(".cmp")) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            JSONObject fromObject = JSONObject.fromObject(reader.readLine());
            String mt = fromObject.getString("maptype");
            setMapType(MapType.valueOf(mt));
            start = fromObject.getDouble("start");
            end = fromObject.getDouble("end");
            tail = fromObject.getInt("tail");
            beans.clear();
            JSONArray paintsTypes = fromObject.getJSONArray("paintTypes");
            JSONArray values = fromObject.getJSONArray("values");
            JSONArray colorArray = fromObject.getJSONArray("colors");
            JSONArray textsArray = fromObject.getJSONArray("texts");
            for (int i = 0; i < paintsTypes.size(); i++) {
                int type = paintsTypes.getInt(i);
                double min = values.getDouble(i * 2);
                double max = values.getDouble(i * 2 + 1);
                int rgb = colorArray.getInt(i);
                String text = textsArray.getString(i);
                Paint p;
                if (type < 0) {
                    p = new Color(rgb);
                } else {//color
                    String path1 = Util.getBeans()[type].getPath();
                    BufferedImage image = ImageIO.read(new FileInputStream(path1));
                    p = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
                }
                beans.add(new ColorMapBean(min, max, p, text, type));
            }
            reader.close();
        } catch (IOException ex) {
        }
    }

}
