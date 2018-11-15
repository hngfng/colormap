/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.panel;

import ui.colormap.gradient.ColorMapPanel;
import com.interactive.jcarnac2d.model.interfaces.cgLayer;
import com.interactive.jcarnac2d.model.interfaces.cgShape;
import com.interactive.jcarnac2d.selection.cgDeviceSelection;
import com.interactive.jcarnac2d.selection.cgSelectorCallback;
import com.interactive.jcarnac2d.view.cgPlotView;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author allanlee
 */
public class MouseManipulator extends MouseInputAdapter {

    private cgPlotView _view;
    private cgLayer rectLayer;
    private ColorMapPanel mapPanel;

    public MouseManipulator(cgPlotView view, cgLayer rectLayer,ColorMapPanel mapPanel) {
        this._view = view;
        this.rectLayer = rectLayer;
        this.mapPanel = mapPanel;
    }

    public void mouseClicked(MouseEvent me) {
        Point p = _view.getViewPosition();
        int mx = me.getX() + p.x;
        int my = me.getY() + p.y;
        int type = 0;
        if (me.isShiftDown()) {
            type = 1;
        } else if (me.isControlDown()) {
            type = 2;
        } else {
            mapPanel.clearSelectedAttr();
        }
        //initiate picking here
        cgDeviceSelection.selectByPoint(rectLayer, _view.getTransformation(), mx, my, 1, 1, new Selector(type), null);
    }

//    @Override
//    public void mouseMoved(MouseEvent me) {
////        super.mouseMoved(me); 
//        ImageIcon image = new ImageIcon(this.getClass().getResource("/ui/colorpanel/triangle.png").getPath());
//        Image image1 = image.getImage();
////        System.out.println(image1);
////        System.out.println(me.getPoint());
////        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image1, me.getLocationOnScreen(), me.getX()+"");
////        view.setCursor(cursor);
//    }
    cgShape _lastSelected;

    private class Selector implements cgSelectorCallback {

        private int type;

        public Selector(int type) {
            this.type = type;
        }

        public boolean shapeSelected(cgLayer shape) {
            return true;
        }

        public boolean shapeSelected(Shape shape) {
            return true;
        }

        public boolean shapeSelected(cgShape shape) {
            if (shape == null) {
                if (_lastSelected != null) {
                    shape = _lastSelected;
                    mapPanel.addSelectedShape(shape);
//                    mapPanel.selectedAttr();
                    _lastSelected = null;
                    return false;
                }
                return false;
            }
            _lastSelected = shape;
            return true;
        }
    }
}
