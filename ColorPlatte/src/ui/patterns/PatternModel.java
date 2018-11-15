/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.patterns;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author allanlee
 */
public class PatternModel extends  AbstractTableModel{
    private List<PatternBean> beans = new ArrayList<>();
    private String [] columNames= new String[]{"名字","图案"};
    private String path;
    {
        path = PatternModel.class.getResource("/ui/patterns/rock/").getPath();
        File file = new File(path);
        if(file.exists())
        {
            File[] listFiles = file.listFiles();
            int i = 0;
            for (File fl : listFiles) {
                String name = fl.getName().substring(0,fl.getName().indexOf("."));
                beans.add(new PatternBean(i,name,fl.getPath()));
            }
        }
    }
    @Override
    public int getRowCount() {
        return beans.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
            return String.class;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      switch(columnIndex)
      {
          case 0:
              return beans.get(rowIndex).getName();
          case 1: 
              return beans.get(rowIndex).getPath();
      }
      return null;
    }
}
