/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.gradient;

import ui.colormap.gradient.GradientMap;
import com.interactive.jcarnac2d.axes.cgAxisShape;
import com.interactive.jcarnac2d.axes.renderers.cgAxisRenderer;
import com.interactive.jcarnac2d.axes.renderers.cgFixedSizeAxisRenderer;
import com.interactive.jcarnac2d.edit.manipulator.cgManipulatorFactory;
import com.interactive.jcarnac2d.edit.manipulator.cgMovableManipulator;
import com.interactive.jcarnac2d.edit.manipulator.geometry.cgSimpleListManipulator;
import com.interactive.jcarnac2d.edit.manipulator.palette.cgManipulatorAttributePalette;
import com.interactive.jcarnac2d.model.attributes.cgGraphicAttribute;
import com.interactive.jcarnac2d.model.attributes.cgLineAttribute;
import com.interactive.jcarnac2d.model.interfaces.cgAttribute;
import com.interactive.jcarnac2d.model.interfaces.cgShape;
import com.interactive.jcarnac2d.model.layers.cgShapeListLayer;
import com.interactive.jcarnac2d.model.models.cgContainerModel;
import com.interactive.jcarnac2d.model.shapes.cgImage;
import com.interactive.jcarnac2d.model.shapes.cgRectangle;
import com.interactive.jcarnac2d.util.cgRect;
import com.interactive.jcarnac2d.util.cgTransformation;
import com.interactive.jcarnac2d.view.cgPlot;
import com.interactive.jcarnac2d.view.cgPlotView;
import com.interactive.util.axes.NumericLogTickGenerator;
import com.interactive.util.axes.NumericTickGenerator;
import com.interactive.util.axes.TickGenerator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import ui.colormap.panel.ColorMapChangeLisener;
import ui.colormap.panel.MouseManipulator;
import ui.file.color.Gradient;
import ui.file.color.Util;

/**
 *
 * @author allanlee
 */
public class ColorMapPanel extends cgPlot implements ColorMapChangeLisener {

    /**
     * Creates new form ColorMapPanel
     */
    private cgContainerModel model;
    private cgShapeListLayer tickLayer;
    private cgShapeListLayer imageLayer;
    private cgShapeListLayer rectLayer;
    private cgShapeListLayer selectedLayer;
    private cgPlotView view;
    private cgRect preRect = null;
    private GradientMap manager;
    private TickGenerator tg;
    private BufferedImage bufferedImage;
    private cgImage image;
    //选择的图形
    private List<cgShape> selectedShapes = new ArrayList<>();

    public ColorMapPanel(GradientMap manager) {
        this.manager = manager;
        initComponents();
        initModel();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resetView();
            }
        });
    }

    public void addMouseManipulator(MouseListener listener) {
        view.addMouseListener(listener);
    }

    public List<cgShape> getSelectedShapes() {
        selectedShapes.sort(new Comparator<cgShape>() {
            @Override
            public int compare(cgShape o1, cgShape o2) {
                return (int) (o1.getBoundingBox(view.getTransformation()).x - o2.getBoundingBox(view.getTransformation()).x);
            }
        });
        return selectedShapes;
    }

    public void addSelectedShape(cgShape selectedShape) {
        cgRect rect = selectedShape.getBoundingBox(view.getTransformation());
        cgRectangle rrr = new cgRectangle(rect);
        cgLineAttribute lineAttr = new cgLineAttribute(Color.red);
        lineAttr.setLineWidth(2f);
        rrr.setAttribute(lineAttr);
        selectedLayer.addShape(rrr);
        selectedShapes.add(selectedShape);
    }

    public void clearSelectedAttr() {
        selectedShapes.clear();
        selectedLayer.removeAllShapes();
    }

    public void selectedAttr() {
        for (cgShape shape : selectedShapes) {
            cgGraphicAttribute attr = ((cgGraphicAttribute) shape.getAttribute()).duplicate();
            attr.setLineStyle(cgAttribute.CG_LS_SOLID);
            attr.setLineColor(Color.red);
            attr.setLineWidth(3f);
            shape.setAttribute(attr);
        }
    }

    private void initBufferedImage() {
        bufferedImage = Gradient.buildMapImage(manager.getPaints());
        image = new cgImage(0, 0, getColorBox().width, getColorBox().height, bufferedImage, true, true);
        image.setAttribute(new cgGraphicAttribute());
        imageLayer.addShape(image);
    }

    public cgRect getModelRect() {
        return model.getBoundingBox();
    }

    public void setModelRect(cgRect bbox) {
        model.setBoundingBox(bbox);
        Rectangle bounds = view.getBounds();
        cgTransformation tr = new cgTransformation(bbox, new cgRect(bounds));
        view.setTransformation(tr);
        setTransformation(tr);
    }

    public void resetView() {
        if (preRect != null) {
            cgRect rt = new cgRect(getBounds());
            double xscale = rt.getWidth() / preRect.getWidth();
            double yscale = rt.getHeight() / preRect.getHeight();
            view.scaleTo(xscale, yscale);
            if (image != null) {
                image.setWidth(getColorBox().width * view.getTransformation().getScaleX());
                image.setHeight(getColorBox().getHeight() * view.getTransformation().getScaleY());
            }
        } else {
            view.setBoundingBox(new cgRect(getBounds()));
            buildTick();
            buildTickRectangles(getColorBox(), tg);
            if (manager.getColorMapType().equals(GradientMap.GRADIENT)) {
                initBufferedImage();
            } else {
            }
        }
        preRect = new cgRect(getBounds());
    }

    public ColorMapPanel(cgContainerModel model, cgShapeListLayer shapeListLayer, cgShapeListLayer selectedLayer) {
        this.model = model;
        this.tickLayer = shapeListLayer;
        this.selectedLayer = selectedLayer;
    }

    private void initModel() {
        this.setLayout(new BorderLayout());
        cgRect modelSpace = new cgRect(0, 0, 1, 1);
        cgRect deviceSpace = new cgRect(0, 0, 1, 1);

        model = new cgContainerModel();
        model.setBoundingBox(modelSpace);

        tickLayer = new cgShapeListLayer();
        model.addLayer(tickLayer);
        rectLayer = new cgShapeListLayer();
        model.addLayer(rectLayer);
        imageLayer = new cgShapeListLayer();
        model.addLayer(imageLayer);

        selectedLayer = new cgShapeListLayer();
        model.addLayer(selectedLayer);

        view = new cgPlotView(model);
        cgTransformation tr = new cgTransformation(modelSpace, deviceSpace);
        view.setTransformation(tr);

        add(view, BorderLayout.CENTER);

        cgManipulatorAttributePalette palette = new cgManipulatorAttributePalette();
        cgSimpleListManipulator list = new cgSimpleListManipulator(selectedLayer);
        cgMovableManipulator manipulator = cgManipulatorFactory.
                createSelectAndScaleManipulator(3.0, 3.0, palette, rectLayer,
                        model,
                        view, selectedLayer, list);
//        cgDefaultMouseManipulator mouse = new MouseManipulator(view);
//        mouse.setMovableManipulator(manipulator);
        MouseManipulator mouse = new MouseManipulator(view, rectLayer, this);
        view.addMouseListener(mouse);
        view.addMouseMotionListener(mouse);
    }

    public void buildTick() {
        cgRect modelBox = getModelRect();
        double perH = modelBox.height / 4;
        cgRect tickBox = new cgRect(modelBox);
        tickBox.height = perH;
        tickBox.y = tickBox.y + perH * 3;

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(manager.getTail());
        cgAxisRenderer ar = new cgFixedSizeAxisRenderer(20, 15, 3, nf);
        switch (manager.getType()) {
            //线性
            case GradientMap.LINER:
                double step = (tickBox.getMaxX() - tickBox.getMinX()) / manager.getIntervals();
                //double modelOrigin, double modelStep, double valueOrigin, double valueStep
                double valueStep = (manager.getEnd() - manager.getStart()) / manager.getIntervals();
                tg = new NumericTickGenerator(0, step, manager.getStart(), valueStep);
                break;
            //对数
            case GradientMap.LOG:
                double xx;
                double st = manager.getStart() == 0 ? 1 : manager.getStart();
                if (manager.getEnd() > st) {
                    step = tickBox.width / (Math.log10(manager.getEnd() / st));
                    xx = tickBox.x - step * Math.log10(st);
                } else {
                    step = tickBox.width / (Math.log10(manager.getEnd() / st));
                    xx = tickBox.getMaxX() - step * Math.log10(manager.getEnd());
                }
                //double modelStep, double modelOrigin, double base)
                tg = new NumericLogTickGenerator(step, xx, 10);
                break;
            //自定义
            default:
                break;
        }
        cgAxisShape tick = new cgAxisShape(cgAxisShape.SOUTH, tickBox, tg, ar);
        this.tickLayer.addShape(tick);
    }

    private cgRect getColorBox() {
        cgRect colorBox = new cgRect(getModelRect());
        double perH = getModelRect().height / 4;
        colorBox.height = perH * 3 - 2;
        return colorBox;
    }

    private cgAttribute getAttribute() {
        cgGraphicAttribute attr = new cgGraphicAttribute();
        attr.setFillStyle(cgAttribute.CG_FS_SOLID);
        attr.setLineStyle(cgAttribute.CG_LS_EMPTY);
        return attr;
    }

    private void buildTickRectangles(cgRect colorBox, TickGenerator tg) {
        if (tg == null) {
            return;
        }
        double modelStart = 0.0D;
        double modelStop = 0.0D;
        double min = 0.0D;
        double max = 0.0D;
        double scale = 0.0D;
        modelStart = colorBox.getLeft();
        modelStop = colorBox.getRight();
        cgRect invRect = colorBox;
        min = invRect.getLeft();
        max = invRect.getRight();
        tg.reset(modelStart, modelStop, min, max, scale);
        int i = 0;
        double pos = 0;
        if (tg.hasNext()) {
            tg.next();
            pos = tg.getTickPosition();
        }
        while (tg.hasNext()) {
            tg.next();
            double pos1 = tg.getTickPosition();
            double x1 = pos;
            double x2 = pos1;
            double y2 = colorBox.getTop();
            double y1 = colorBox.getBottom();
            if (i >= rectLayer.getTotalShapes()) {
                cgAttribute attr = getAttribute();
                TickRectangle rectangle = new TickRectangle(x1, y1, x2, y2);
                //颜色
                if (manager.getPaints() != null && manager.getPaints().length > i && !manager.getColorMapType().equals(GradientMap.GRADIENT)) {
                    Paint paint = manager.getPaints()[i];
                    attr.setFillColor(paint);
                } else {
                    attr.setFillColor(Util.getColors()[i]);
                }
                //绘制类型
                if (manager.getPaintTypes() != null && manager.getPaintTypes().length > i) {
                    rectangle.setPatternType(manager.getPaintTypes()[i]);
                }
                //文本
                if (manager.getTexts() != null && manager.getTexts().length > i) {
                    rectangle.setText(manager.getTexts()[i]);
                }
                rectangle.setAttribute(attr);
                rectLayer.addShape(rectangle);
            } else {
                ((TickRectangle) rectLayer.getShape(i)).setRectangle(new cgRect(x1, y1, x2, y2));
            }
            pos = pos1;
            i++;
        }

        //多余删除
        if (i < rectLayer.getTotalShapes()) {
            for (int j = rectLayer.getTotalShapes() - 1; j >= i; j--) {
                cgShape shape = rectLayer.getShape(j);
                rectLayer.removeShape(shape);
            }
        }
        if (pos < colorBox.getMaxX()) {
            TickRectangle rectangle = new TickRectangle(pos, colorBox.getBottom(), colorBox.getMaxX(), colorBox.getTop());
            cgAttribute attr = getAttribute();
            //颜色
            if (manager.getPaints() != null && manager.getPaints().length > i && !manager.getColorMapType().equals(GradientMap.GRADIENT)) {
                Paint paint = manager.getPaints()[i];
                attr.setFillColor(paint);
            } else {
                attr.setFillColor(Util.getColors()[i]);
            }
            //绘制类型
            if (manager.getPaintTypes() != null && manager.getPaintTypes().length > i) {
                rectangle.setPatternType(manager.getPaintTypes()[i]);
            }
            //文本
            if (manager.getTexts() != null && manager.getTexts().length > i) {
                rectangle.setText(manager.getTexts()[i]);
            }
            rectangle.setAttribute(attr);
            rectLayer.addShape(rectangle);
        }
    }

    public double getTickValue(double x) {
        WeightedObservedPoints points = new WeightedObservedPoints();
        points.add(model.getBoundingBox().x, 0);
        points.add(model.getBoundingBox().getMaxX(), 255);
        double[] result = Util.fitting(points, 0, 1);
        return Util.calculateY(0, result, new double[]{x})[0];
    }

    //获取对应颜色的索引边界
    public int[] getColorIndexs() {
        double[] xx = new double[rectLayer.getTotalShapes()];
        for (int i = 0; i < rectLayer.getTotalShapes(); i++) {
            cgShape shape = rectLayer.getShape(i);
            cgRect bbox = shape.getBoundingBox(view.getTransformation());
            xx[i] = bbox.getMaxX();
        }
        return Util.getIndexs(model.getBoundingBox().x, model.getBoundingBox().getMaxX(), manager.getStart(), manager.getEnd(), xx, manager.getType());
    }

    //获取对应颜色的索引边界
    public int getColorIndexs(double x) {
        return Util.getIndexs(model.getBoundingBox().x, model.getBoundingBox().getMaxX(), manager.getStart(), manager.getEnd(), new double[]{x}, manager.getType())[0];
    }

    //间隔梯度
    public void intervalsGradient() {
        imageLayer.removeAllShapes();
        //循环次数
        if (rectLayer.getTotalShapes() <= 2) {
            return;
        }
        int[] idxs = new int[2];
        //无选择
        if (selectedShapes.isEmpty()) {
            idxs[0] = 0;
            idxs[idxs.length - 1] = rectLayer.getTotalShapes() - 1;
        } else if (selectedShapes.size() == 1) {//选择为一个
            int id = rectLayer.getShapeIndex(selectedShapes.get(0));
            if (id == 0 || id == rectLayer.getTotalShapes() - 1) {
                idxs[0] = 0;
                idxs[idxs.length - 1] = rectLayer.getTotalShapes() - 1;
            } else {
                idxs = new int[3];
                idxs[0] = 0;
                idxs[1] = id;
                idxs[idxs.length - 1] = rectLayer.getTotalShapes() - 1;
            }
        } else {
            idxs = new int[selectedShapes.size()];
            for (int i = 0; i < idxs.length; i++) {
                int id = rectLayer.getShapeIndex(selectedShapes.get(i));
                idxs[i] = id;
            }
        }
        Arrays.sort(idxs);
        for (int i = 0; i < idxs.length - 1; i++) {
            cgShape sp = rectLayer.getShape(idxs[i]);
            cgShape sp1 = rectLayer.getShape(idxs[i + 1]);
            cgAttribute a = sp.getAttribute();
            cgAttribute a1 = sp1.getAttribute();
            if (a.getFillColor() instanceof Color) {
                Color color = (Color) a.getFillColor();
                if (a1.getFillColor() instanceof Color) {
                    Color color1 = (Color) a1.getFillColor();
                    int cnt = Math.abs(idxs[i] - idxs[i + 1]);
                    Color[] clrs = Gradient.createGradient(color, color1, cnt);
                    for (int j = 0; j < cnt; j++) {
                        cgShape shape2 = rectLayer.getShape(j + idxs[i]);
                        cgGraphicAttribute duplicate = ((cgGraphicAttribute) shape2.getAttribute()).duplicate();
                        if (duplicate.getFillColor() instanceof Color) {
                            duplicate.setFillColor(clrs[j]);
                            shape2.setAttribute(duplicate);
                        }
                    }
                }
            }
        }
    }

    //梯度
    public void gradient() {
        if (rectLayer.getTotalShapes() <= 1) {
            return;
        }
        intervalsGradient();
        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < rectLayer.getTotalShapes(); i++) {
            cgShape shape = rectLayer.getShape(i);
            Paint fillColor = shape.getAttribute().getFillColor();
            if (fillColor instanceof Color) {
                colors.add((Color) fillColor);
            }
        }
        Color[] paints = colors.toArray(new Color[0]);
        Color[] gradient = Gradient.createMultiGradient(paints, 256);

        bufferedImage = new BufferedImage(256, 25, 1);
        for (int i = 0; i < gradient.length; i++) {
            for (int j = 0; j < 25; j++) {
                bufferedImage.setRGB(i, j, gradient[i].getRGB());
            }
        }
        imageLayer.removeAllShapes();
        image = new cgImage(0, 0, getColorBox().width, getColorBox().height, bufferedImage, true, true);
        image.setAttribute(new cgGraphicAttribute());
        imageLayer.addShape(image);
        resetView();
    }

    public Color[] getPaints() {
        if (imageLayer.getTotalShapes() > 0) {
            int width = bufferedImage.getWidth();
            Color[] clrs = new Color[width];
            for (int i = 0; i < width; i++) {
                int rgb = bufferedImage.getRGB(i, 5);
                clrs[i] = new Color(rgb);
            }
            return clrs;
        }
        return null;
    }

    public TickRectangle[] getTickRectangles() {
        TickRectangle[] rects = new TickRectangle[rectLayer.getTotalShapes()];
        for (int i = 0; i < rectLayer.getTotalShapes(); i++) {
            TickRectangle get = (TickRectangle) rectLayer.getShape(i);
            rects[i] = get;
        }
        return rects;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(500, 150));
        setPreferredSize(new java.awt.Dimension(500, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("rects")) {
            buildTickRectangles(getColorBox(), tg);
        }
        if (evt.getPropertyName().equals("tick")) {
            tickLayer.removeAllShapes();
            buildTick();
        }
        view.updateUI();
    }

    public void removeImage() {
        rectLayer.removeAllShapes();
        selectedLayer.removeAllShapes();
        selectedShapes.clear();
        imageLayer.removeAllShapes();
        buildTickRectangles(getColorBox(), tg);
    }

    public void setColors(Paint selectedColor, double x, double maxX) {
        double start = manager.getStart();
        double end = manager.getEnd();

        double factor = (bufferedImage.getWidth() - 1) / (end - start);
        int sIdx = (int) Math.round((x - start) * factor);
        int eIdx = (int) Math.round((maxX - start) * factor);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setPaint(selectedColor);
        g.fillRect(sIdx, 0, (eIdx - sIdx) + 1, bufferedImage.getHeight());
        g.dispose();
        imageLayer.removeAllShapes();
        image = new cgImage(0, 0, getColorBox().width, getColorBox().height, bufferedImage, true, true);
        image.setAttribute(new cgGraphicAttribute());
        imageLayer.addShape(image);
        resetView();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
