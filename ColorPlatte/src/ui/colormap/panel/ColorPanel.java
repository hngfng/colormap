/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.panel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Dell
 */
public class ColorPanel extends javax.swing.JPanel {

    /**
     * Creates new form ColorPanel
     */
    private AbstractColorChooserPanel colorPanel;
    private int index = 0;
    private Color selectedColor;
    protected EventListenerList listenerList = new EventListenerList();

    public ColorPanel() {
        initComponents();
        JColorChooser chooser = new JColorChooser();
        AbstractColorChooserPanel[] chooserPanels = chooser.getChooserPanels();
        clrPnl.add(colorPanel = chooserPanels[3], BorderLayout.CENTER);
        //颜色选择面板
        colorPanel.getColorSelectionModel().addChangeListener(new ColorChangeListener());
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

        clrPnl = new javax.swing.JPanel();
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

        clrPnl.setLayout(new java.awt.BorderLayout());

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
        jPanel4.add(standsPnl, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clrPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(clrPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents
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

    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    protected void fireStateChanged() {
        ChangeEvent changeEvent = null;
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(selectedColor);
                }
                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }

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
                selectedColor = lbl.getBackground();
                fireStateChanged();
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
                selectedColor = lbl.getBackground();
                fireStateChanged();
                break;
            }
        }
    }//GEN-LAST:event_standsPnlMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel clrPnl;
    private javax.swing.JPanel historyPnl;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel standsPnl;
    // End of variables declaration//GEN-END:variables
//    public static void main(String[] args) {
//        JDialog dlg = new JDialog();
//        ColorPanel colorPanel1 = new ColorPanel();
//        colorPanel1.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println(e.getSource());
//            }
//        });
//        dlg.add(colorPanel1, BorderLayout.CENTER);
//        dlg.setVisible(true);
//    }

    private class ColorChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            selectedColor = colorPanel.getColorSelectionModel().getSelectedColor();
            setHistoryColor(selectedColor);
            fireStateChanged();
//            setSelectedColor(selectedColor);
//            for (cgShape shape : mapPnl.getSelectedShapes()) {
//                TickRectangle re = (TickRectangle) shape;
//                cgRect bbox = re.getRectangle();
//                if (manager.getColorMapType() == 1) {
//                    mapPnl.setColors(selectedColor, mapPnl.getTickValue(bbox.getX()), mapPnl.getTickValue(bbox.getMaxX()));
//                } else {
//                    cgGraphicAttribute attr = ((cgGraphicAttribute) shape.getAttribute()).duplicate();
//                    attr.setFillColor(selectedColor);
//                    shape.setAttribute(attr);
//                    re.setPatternType(-1);
//                    setHistoryColor(selectedColor);
//                }
//            }
        }
    }
}
