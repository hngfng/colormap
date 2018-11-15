/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.colormap.panel;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author allanlee
 */
public class ColorMapEvent extends  PropertyChangeEvent{
    
    public ColorMapEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        super(source, propertyName, oldValue, newValue);
    }
    
}
