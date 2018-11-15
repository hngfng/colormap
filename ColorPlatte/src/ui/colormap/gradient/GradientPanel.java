/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.gradient;

import com.interactive.jcarnac2d.model.attributes.cgGraphicAttribute;
import com.interactive.jcarnac2d.model.interfaces.cgAttribute;
import com.interactive.jcarnac2d.model.interfaces.cgShape;
import com.interactive.jcarnac2d.model.shapes.cgRectangle;
import com.interactive.jcarnac2d.util.cgRect;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import ui.PlatteEditor;
import ui.colormap.ColorMap;
import ui.colormap.panel.PlattePanel;
import ui.colormap.MapType;
import ui.patterns.PatternModel;

/**
 *
 * @author allanlee
 */
public class GradientPanel extends PlattePanel {
    
    private GradientMap manager;
    private ColorMapPanel mapPnl;
    private AbstractColorChooserPanel colorPanel;
    private PatternModel patternModel;
//    private int type;//0--手动，1--梯度，2--间隔梯度
    private int index = 0;

    /**
     * Creates new form GradientPanel
     *
     * @param mg
     */
    public GradientPanel(ColorMap mg) {
        super(mg);
        if (mg instanceof GradientMap) {
            this.manager = (GradientMap) mg;
        } else if (mg == null || mg.getMapType().compareTo(MapType.Gradient) != 0) {
            this.manager = new GradientMap();
        }
        //色标面板
        mapPnl = new ColorMapPanel(manager);
        initComponents();
        this.jPanel3.add(mapPnl, BorderLayout.CENTER);
        manager.addColorMapListener(mapPnl);

        //颜色选择器
        JColorChooser chooser = new JColorChooser();
        AbstractColorChooserPanel[] chooserPanels = chooser.getChooserPanels();
        jPanel7.add(colorPanel = chooserPanels[3], BorderLayout.CENTER);
        //岩性
        patternTable.getColumnModel().getColumn(1).setCellRenderer(new PatternLabelRender());

        //设置图形的选择属性
        mapPnl.addMouseManipulator(new ColorMapManipulator());
        //颜色选择面板
        colorPanel.getColorSelectionModel().addChangeListener(new ColorChangeListener());
        //岩性选择
        patternTable.addMouseListener(new PatternMouseAdapter());
        
        lowerBoundarySpn.setValue(manager.getStart());
        supperBounderSpn.setValue(manager.getEnd());
        numSpn.setValue(manager.getIntervals());
        decimalsNmSpn.setValue(manager.getTail());
        scaleTypeCmb.setSelectedIndex(getScaleIdx());
    }
    
    public void setHistoryColor(Color selectedColor) {
        int size = historyPnl.getComponents().length;
        Color cc = historyPnl.getComponents()[index].getBackground();
        historyPnl.getComponents()[index].setBackground(new Color(selectedColor.getRGB()));
        for (int i = index + 1; i < historyPnl.getComponents().length; i++) {
            Component component1 = historyPnl.getComponents()[i];
            Color b = component1.getBackground();
            component1.setBackground(new Color(cc.getRGB()));
            cc = b;
        }
        index = index == size - 1 ? 0 : ++index;
    }
    
    private void setSelectedColor(Color selectedColor) {
        mapPnl.getSelectedShapes().forEach((shape) -> {
            cgRectangle re = (cgRectangle) shape;
            cgRect bbox = re.getRectangle();
            if (!manager.getColorMapType().equals(GradientMap.MANUAL)) {
                mapPnl.setColors(selectedColor, mapPnl.getTickValue(bbox.getX()), mapPnl.getTickValue(bbox.getMaxX()));
            } else {
                cgGraphicAttribute attr = ((cgGraphicAttribute) shape.getAttribute()).duplicate();
                attr.setFillColor(selectedColor);
                shape.setAttribute(attr);
            }
        });
    }
    
    @Override
    public JComponent[] getButtons() {
        return new JButton[]{manualBtn, gradientBtn, intervalGradientBtn, clearBtn};
    }
    
    public void save(String path) {
        manager.save(path, mapPnl.getTickRectangles(), mapPnl.getPaints());
    }

    @Override
    public ColorMap getColorMap() {
        manager.setPaints(mapPnl.getTickRectangles(), mapPnl.getPaints());
        return manager;
    }
    private int getScaleIdx()
    {
        return manager.getType().equals(GradientMap.LINER)?0:manager.getType().equals(GradientMap.LOG)?1:2;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        manualBtn = new javax.swing.JButton();
        gradientBtn = new javax.swing.JButton();
        intervalGradientBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lowerBoundarySpn = new javax.swing.JSpinner();
        supperBounderSpn = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        numSpn = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        decimalsNmSpn = new javax.swing.JSpinner();
        scaleTypeCmb = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        clowerBoundarySpn = new javax.swing.JSpinner();
        cupperBoundarySpn = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        textTxt = new javax.swing.JTextField();
        areaFilePnl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patternTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        historyPnl = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        standsPnl = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        manualBtn.setText("手动");
        manualBtn.setToolTipText("");
        manualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualBtnActionPerformed(evt);
            }
        });

        gradientBtn.setText("梯度");
        gradientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradientBtnActionPerformed(evt);
            }
        });

        intervalGradientBtn.setText("间隔");
        intervalGradientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalGradientBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("清除");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jPanel3.setMinimumSize(new java.awt.Dimension(500, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 200));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("常规"));

        jLabel1.setText("下边界：");

        jLabel2.setText("上边界：");

        lowerBoundarySpn.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 255.0d, 1.0d));
        lowerBoundarySpn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lowerBoundarySpnStateChanged(evt);
            }
        });

        supperBounderSpn.setModel(new javax.swing.SpinnerNumberModel(255.0d, 0.0d, 10000.0d, 1.0d));
        supperBounderSpn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                supperBounderSpnStateChanged(evt);
            }
        });

        jLabel3.setText("间隔数：");

        numSpn.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
        numSpn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numSpnStateChanged(evt);
            }
        });

        jLabel4.setText("刻度：");

        jLabel6.setText("小数位数：");

        decimalsNmSpn.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        decimalsNmSpn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalsNmSpnStateChanged(evt);
            }
        });

        scaleTypeCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "线性", "对数" }));
        scaleTypeCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleTypeCmbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decimalsNmSpn))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowerBoundarySpn))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supperBounderSpn))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numSpn))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scaleTypeCmb, 0, 86, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lowerBoundarySpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(supperBounderSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(scaleTypeCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(decimalsNmSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("当前间隔"));

        jLabel7.setText("下边界：");

        jLabel8.setText("上边界：");

        clowerBoundarySpn.setModel(new javax.swing.SpinnerNumberModel());
        clowerBoundarySpn.setEnabled(false);

        cupperBoundarySpn.setModel(new javax.swing.SpinnerNumberModel());
        cupperBoundarySpn.setEnabled(false);

        jLabel9.setText("文本：");

        textTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textTxtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cupperBoundarySpn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(textTxt)
                    .addComponent(clowerBoundarySpn)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(clowerBoundarySpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cupperBoundarySpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        areaFilePnl.setBorder(javax.swing.BorderFactory.createTitledBorder("填充区域"));
        areaFilePnl.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 100));

        patternTable.setModel(patternModel = new PatternModel());
        jScrollPane1.setViewportView(patternTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        areaFilePnl.add(jScrollPane1, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        areaFilePnl.add(jPanel7, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        historyPnl.setBorder(javax.swing.BorderFactory.createTitledBorder("历史颜色"));
        historyPnl.setPreferredSize(new java.awt.Dimension(105, 80));
        historyPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyPnlMouseClicked(evt);
            }
        });
        historyPnl.setLayout(new java.awt.GridBagLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel5.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel5.setOpaque(true);
        jLabel5.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel5, gridBagConstraints);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel10.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel10, gridBagConstraints);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel11.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel11, gridBagConstraints);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel12.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel12, gridBagConstraints);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel13.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel13, gridBagConstraints);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel14.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel14, gridBagConstraints);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel15.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel15.setOpaque(true);
        jLabel15.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel15, gridBagConstraints);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel16.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel16, gridBagConstraints);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel17.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel17, gridBagConstraints);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel19.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel19.setOpaque(true);
        jLabel19.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel19, gridBagConstraints);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel22.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel22.setOpaque(true);
        jLabel22.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel22, gridBagConstraints);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel20.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel20.setOpaque(true);
        jLabel20.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel20, gridBagConstraints);

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel33.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel33.setOpaque(true);
        jLabel33.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel33, gridBagConstraints);

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel34.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel34.setOpaque(true);
        jLabel34.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel34, gridBagConstraints);

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel35.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel35.setOpaque(true);
        jLabel35.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        historyPnl.add(jLabel35, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel4.add(historyPnl, gridBagConstraints);

        standsPnl.setBackground(new java.awt.Color(255, 255, 0));
        standsPnl.setBorder(javax.swing.BorderFactory.createTitledBorder("标准颜色"));
        standsPnl.setOpaque(false);
        standsPnl.setPreferredSize(new java.awt.Dimension(105, 80));
        standsPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                standsPnlMouseClicked(evt);
            }
        });
        standsPnl.setLayout(new java.awt.GridBagLayout());

        jLabel18.setBackground(new java.awt.Color(255, 255, 0));
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel18.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel18, gridBagConstraints);

        jLabel21.setBackground(new java.awt.Color(0, 0, 255));
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel21.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel21.setOpaque(true);
        jLabel21.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel21, gridBagConstraints);

        jLabel23.setBackground(new java.awt.Color(0, 0, 102));
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel23.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel23.setOpaque(true);
        jLabel23.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel23, gridBagConstraints);

        jLabel24.setBackground(new java.awt.Color(51, 255, 0));
        jLabel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel24.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel24.setOpaque(true);
        jLabel24.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel24, gridBagConstraints);

        jLabel25.setBackground(new java.awt.Color(0, 255, 255));
        jLabel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel25.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel25.setOpaque(true);
        jLabel25.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel25, gridBagConstraints);

        jLabel26.setBackground(new java.awt.Color(0, 153, 0));
        jLabel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel26.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel26.setOpaque(true);
        jLabel26.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel26, gridBagConstraints);

        jLabel27.setBackground(new java.awt.Color(255, 0, 0));
        jLabel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel27.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel27.setOpaque(true);
        jLabel27.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel27, gridBagConstraints);

        jLabel28.setBackground(new java.awt.Color(102, 102, 102));
        jLabel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel28.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel28.setOpaque(true);
        jLabel28.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel28, gridBagConstraints);

        jLabel29.setBackground(new java.awt.Color(153, 0, 153));
        jLabel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel29.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel29.setOpaque(true);
        jLabel29.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel29, gridBagConstraints);

        jLabel30.setBackground(new java.awt.Color(153, 153, 0));
        jLabel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel30.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel30.setOpaque(true);
        jLabel30.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel30, gridBagConstraints);

        jLabel31.setBackground(new java.awt.Color(255, 0, 255));
        jLabel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel31.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel31.setOpaque(true);
        jLabel31.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel31, gridBagConstraints);

        jLabel32.setBackground(new java.awt.Color(0, 102, 102));
        jLabel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel32.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel32.setOpaque(true);
        jLabel32.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel32, gridBagConstraints);

        jLabel36.setBackground(new java.awt.Color(102, 0, 102));
        jLabel36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel36.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel36.setOpaque(true);
        jLabel36.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel36, gridBagConstraints);

        jLabel37.setBackground(new java.awt.Color(204, 204, 204));
        jLabel37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel37.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel37.setOpaque(true);
        jLabel37.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel37, gridBagConstraints);

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel38.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel38.setOpaque(true);
        jLabel38.setPreferredSize(new java.awt.Dimension(15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        standsPnl.add(jLabel38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel4.add(standsPnl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        areaFilePnl.add(jPanel4, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(areaFilePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(areaFilePnl, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lowerBoundarySpnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lowerBoundarySpnStateChanged
        double num = Double.parseDouble(lowerBoundarySpn.getValue().toString());
        manager.setStart(num);
        this.updateUI();
    }//GEN-LAST:event_lowerBoundarySpnStateChanged

    private void supperBounderSpnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_supperBounderSpnStateChanged
        double num = Double.parseDouble(supperBounderSpn.getValue().toString());
        manager.setEnd(num);
        this.updateUI();
    }//GEN-LAST:event_supperBounderSpnStateChanged

    private void numSpnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numSpnStateChanged
        int num = Integer.parseInt(numSpn.getValue().toString());
        manager.setIntervals(num);
        if (!manager.getColorMapType().equals(GradientMap.MANUAL)) {
            mapPnl.removeImage();
        }
        this.updateUI();
    }//GEN-LAST:event_numSpnStateChanged

    private void decimalsNmSpnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalsNmSpnStateChanged
        int tail = Integer.parseInt(decimalsNmSpn.getValue().toString());
        manager.setTail(tail);
        this.updateUI();
    }//GEN-LAST:event_decimalsNmSpnStateChanged

    private void scaleTypeCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleTypeCmbActionPerformed
        int selectedIndex = scaleTypeCmb.getSelectedIndex();
        manager.setType(selectedIndex == 0?GradientMap.LINER:selectedIndex==1?GradientMap.LOG:GradientMap.CUSTOM);
        if (!manager.getColorMapType().equals(GradientMap.MANUAL)) {
            mapPnl.removeImage();
        }
        numSpn.setEnabled(selectedIndex == 0);
        this.updateUI();
    }//GEN-LAST:event_scaleTypeCmbActionPerformed

    private void textTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textTxtKeyPressed
        if (mapPnl.getSelectedShapes().size() > 0) {
            TickRectangle get = (TickRectangle) mapPnl.getSelectedShapes().get(0);
            get.setText(textTxt.getText());
        }
    }//GEN-LAST:event_textTxtKeyPressed

    private void historyPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyPnlMouseClicked
        Graphics graphics = historyPnl.getGraphics();
        historyPnl.paintComponents(graphics);
        graphics.dispose();
        for (Component component : historyPnl.getComponents()) {
            JLabel lbl = (JLabel) component;
            if (component.getBounds().contains(evt.getPoint())) {
                Graphics2D g2d = (Graphics2D) lbl.getGraphics();
                BasicStroke stroke = new BasicStroke(4f);
                g2d.setStroke(stroke);
                g2d.setColor(Color.red);
                Rectangle bb = lbl.getBounds();
                g2d.drawRect(0, 0, bb.width, bb.height);
                g2d.dispose();
                setSelectedColor(lbl.getBackground());
                break;
            }
        }
    }//GEN-LAST:event_historyPnlMouseClicked

    private void standsPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_standsPnlMouseClicked
        Graphics graphics = standsPnl.getGraphics();
        standsPnl.paintComponents(graphics);
        graphics.dispose();
        for (Component component : standsPnl.getComponents()) {
            JLabel lbl = (JLabel) component;
            if (component.getBounds().contains(evt.getPoint())) {
                Graphics2D g2d = (Graphics2D) lbl.getGraphics();
                BasicStroke stroke = new BasicStroke(4f);
                g2d.setStroke(stroke);
                g2d.setColor(Color.red);
                Rectangle bb = lbl.getBounds();
                g2d.drawRect(0, 0, bb.width, bb.height);
                g2d.dispose();
                setHistoryColor(lbl.getBackground());
                setSelectedColor(lbl.getBackground());
                break;
            }
        }
    }//GEN-LAST:event_standsPnlMouseClicked

    private void manualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualBtnActionPerformed
        if (!manager.getColorMapType().equals(GradientMap.MANUAL)) {
            mapPnl.removeImage();
        }
        manager.setColorMapType(GradientMap.MANUAL);
    }//GEN-LAST:event_manualBtnActionPerformed

    private void gradientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradientBtnActionPerformed
        manager.setColorMapType(GradientMap.GRADIENT);
        mapPnl.gradient();
    }//GEN-LAST:event_gradientBtnActionPerformed

    private void intervalGradientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalGradientBtnActionPerformed
        manager.setColorMapType(GradientMap.INTERVAL);
        mapPnl.intervalsGradient();
    }//GEN-LAST:event_intervalGradientBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        this.manager.setType(GradientMap.LINER);
        numSpn.setValue(1);
        supperBounderSpn.setValue(255.0);
        manager.setEnd(255);
        lowerBoundarySpn.setValue(0.0);
        manager.setStart(0);
        scaleTypeCmb.setSelectedIndex(0);
        manager.clear();
    }//GEN-LAST:event_clearBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel areaFilePnl;
    private javax.swing.JButton clearBtn;
    private javax.swing.JSpinner clowerBoundarySpn;
    private javax.swing.JSpinner cupperBoundarySpn;
    private javax.swing.JSpinner decimalsNmSpn;
    private javax.swing.JButton gradientBtn;
    private javax.swing.JPanel historyPnl;
    private javax.swing.JButton intervalGradientBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner lowerBoundarySpn;
    private javax.swing.JButton manualBtn;
    private javax.swing.JSpinner numSpn;
    private javax.swing.JTable patternTable;
    private javax.swing.JComboBox<String> scaleTypeCmb;
    private javax.swing.JPanel standsPnl;
    private javax.swing.JSpinner supperBounderSpn;
    private javax.swing.JTextField textTxt;
    // End of variables declaration//GEN-END:variables
  //初始化颜色面板
    private class ColorMapManipulator extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (mapPnl.getSelectedShapes().size() <= 0) {
                return;
            }
            cgShape shape = GradientPanel.this.mapPnl.getSelectedShapes().get(0);
            Paint fp = shape.getAttribute().getFillColor();
            if (fp instanceof Color && manager.getColorMapType().equals(GradientMap.MANUAL)) {
                colorPanel.getColorSelectionModel().setSelectedColor((Color) fp);
                TickRectangle rect = (TickRectangle) shape;
                double x = rect.getRectangle().x;
                double maxX = rect.getRectangle().getMaxX();
                clowerBoundarySpn.setValue(mapPnl.getTickValue(x));
                cupperBoundarySpn.setValue(mapPnl.getTickValue(maxX));
                textTxt.setText(rect.getText());
            }
        }
    }
    
    private class ColorChangeListener implements ChangeListener {
        
        @Override
        public void stateChanged(ChangeEvent e) {
            Color selectedColor = colorPanel.getColorSelectionModel().getSelectedColor();
            for (cgShape shape : mapPnl.getSelectedShapes()) {
                TickRectangle re = (TickRectangle) shape;
                cgRect bbox = re.getRectangle();
                if (manager.getColorMapType().equals(GradientMap.GRADIENT)) {
                    mapPnl.setColors(selectedColor, mapPnl.getTickValue(bbox.getX()), mapPnl.getTickValue(bbox.getMaxX()));
                } else {
                    cgGraphicAttribute attr = ((cgGraphicAttribute) shape.getAttribute()).duplicate();
                    attr.setFillColor(selectedColor);
                    shape.setAttribute(attr);
                    re.setPatternType(-1);
                    setHistoryColor(selectedColor);
                }
            }
        }
        
    }
    
    private class PatternMouseAdapter extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                int selectedRow = patternTable.getSelectedRow();
                String path = patternModel.getValueAt(selectedRow, 1).toString();
                BufferedImage image = ImageIO.read(new FileInputStream(path));
//                BufferedImage desImage = new BufferedImage(2, 2, image.getType());
//                AffineTransform transform = new AffineTransform();
//                transform.setToScale(2d / image.getWidth(), 2d / image.getHeight());
//                AffineTransformOp imageOp = new AffineTransformOp(transform, null);
//                imageOp.filter(image, desImage);

                for (cgShape shape : mapPnl.getSelectedShapes()) {
                    TickRectangle re = (TickRectangle) shape;
                    cgRect bbox = re.getRectangle();
                    TexturePaint paint = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
                    cgGraphicAttribute attr = ((cgGraphicAttribute) shape.getAttribute()).duplicate();
                    attr.setFillStyle(cgAttribute.CG_FS_SOLID);
                    attr.setFillColor(paint);
                    shape.setAttribute(attr);
                    TickRectangle tr = (TickRectangle) shape;
                    tr.setPatternType(selectedRow);
                    if (manager.getColorMapType().equals(GradientMap.GRADIENT)) {
                        
                        mapPnl.setColors(paint, mapPnl.getTickValue(bbox.getX()), mapPnl.getTickValue(bbox.getMaxX()));
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PlatteEditor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PlatteEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class PatternLabelRender implements TableCellRenderer {
        
        private JLabel label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                
                super.paintComponent(g);
                String path = getName();
                if (path != null) {
                    try {
                        BufferedImage image = ImageIO.read(new FileInputStream(path));
                        TexturePaint paint = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setPaint(paint);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        g2d.dispose();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PlatteEditor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(PlatteEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        };
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (column == 1) {
                label.setName(String.valueOf(value));
                return label;
            }
            return null;
        }
        
    }
}
