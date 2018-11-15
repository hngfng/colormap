/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.panel;

import com.interactive.jcarnac2d.axes.cgAxisShape;
import com.interactive.jcarnac2d.axes.renderers.cgAxisRenderer;
import com.interactive.jcarnac2d.model.attributes.cgGraphicAttribute;
import com.interactive.jcarnac2d.model.cgTransformable;
import com.interactive.jcarnac2d.model.interfaces.cgAttribute;
import com.interactive.jcarnac2d.util.cgRect;
import com.interactive.jcarnac2d.util.cgTransformation;
import com.interactive.util.axes.TickGenerator;
import java.awt.Paint;

/**
 *
 * @author allanlee
 */
public class ColorMapTick extends cgAxisShape implements cgTransformable {

    private Paint[] paints;
    private cgGraphicAttribute attr;
    private cgRect colorBox;

    public ColorMapTick(int orient, cgRect bbox, cgRect tickBox, TickGenerator tg, cgAxisRenderer ar, Paint[] paints) {
        super(orient, tickBox, tg, ar);
        this.colorBox = bbox;
        this.paints = paints;
        attr = new cgGraphicAttribute();
        attr.setFillStyle(cgAttribute.CG_FS_SOLID);
        attr.setLineStyle(cgAttribute.CG_LS_EMPTY);
    }

//    @Override
//    public void render(cgShapeRenderer sr, cgRect bbox) {
//        super.render(sr, bbox);
//        double modelStart = 0.0D;
//        double modelStop = 0.0D;
//        double min = 0.0D;
//        double max = 0.0D;
//        double scale = 0.0D;
//        modelStart = colorBox.getLeft();
//        modelStop = colorBox.getRight();
//        getBoundingBox(sr.getTransformation());
//        if (isVisible()) {
//            if (!colorBox.intersectsRect(bbox.x, bbox.y, bbox.width, bbox.height)) {
//                return;
//            }
//            cgRect invRect = colorBox.intersection(bbox);
//            min = invRect.getLeft();
//            max = invRect.getRight();
//            scale = sr.getTransformation().getScaleX();
//            TickGenerator tg = getTickGenerator();
//            switch (getOrientation()) {
//                case 2:
//                    tg.reset(modelStart, modelStop, min, max, scale);
//                    int i = 0;
//                    double pos = 0;
//                    if (tg.hasNext()) {
//                        tg.next();
//                        pos = tg.getTickPosition();
//                    }
//                    while (tg.hasNext()) {
//                        tg.next();
//                        double pos1 = tg.getTickPosition();
//                        double x1 = pos;
//                        double x2 = pos1;
//                        double y2 = colorBox.getTop();
//                        double y1 = colorBox.getBottom();
////                        if(i >=paints.length) return;
//                        cgRectangle rectangle = new cgRectangle(x1, y1, x2, y2);
//                        attr.setFillColor(paints[i]);
//                        rectangle.setAttribute(attr);
//                        sr.render(rectangle, bbox);
//                        pos = pos1;
//                        i++;
//                    }
//                    if (pos < colorBox.getMaxX()) {
//                        cgRectangle rectangle = new cgRectangle(pos, colorBox.getBottom(), colorBox.getMaxX(), colorBox.getTop());
//                        attr.setFillColor(paints[i]);
//                        rectangle.setAttribute(attr);
//                        sr.render(rectangle, bbox);
//                    }
//                    break;
//            }
//        }
//    }

    @Override
    public void applyTransformation(cgTransformation c) {

    }

    @Override
    public int getAllowedTransformationType() {
        return cgTransformable.TYPE_GENERAL_SCALE;
    }
}
