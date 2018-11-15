/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import ui.colormap.ColorMap;
import ui.colormap.ColorMapBean;
import ui.colormap.ColorMapLoader;
import ui.colormap.gradient.GradientMap;
import ui.colormap.step.StepColorMap;

/**
 *
 * @author Dell
 */
public class Demo {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JDialog dialog = new JDialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setLayout(new BorderLayout());
//                String path = "D:\\test.cmp";
//                ColorMap mp = ColorMapLoader.load(path);
                ColorMap mp = new StepColorMap();
                List<ColorMapBean> list = new ArrayList<ColorMapBean>();
                for(int i = 0;i<10;i++){
                    Color clr  = new Color((int)(Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
                    list.add(new ColorMapBean(i, i+10, clr, "", -1));
                }
                mp.setBeans(list);
                //加载
                PlatteEditor panel = new PlatteEditor(mp) {
                    @Override
                    public void apply(ColorMap cmm) {
                        if (cmm instanceof GradientMap) {//梯度类型
                            GradientMap gm = (GradientMap) cmm;
                            for (Paint paint : gm.getPaints()) {
                                System.out.println(paint);
                            }
                        } else { //其它类型
                            cmm.getBeans().forEach((bean) -> {
                                System.out.println(bean);
                            });
                        }
                    }
                };
                dialog.add(panel, BorderLayout.CENTER);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }
}
