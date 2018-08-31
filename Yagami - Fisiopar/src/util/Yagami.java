/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Container;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author UNOPAR
 */
public class Yagami {
    public static Date stringToDate(String text) throws ParseException {
        String startDate = text;
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
          
        return sqlStartDate;
    }
    
    public static void resizeColumns(JTable tabela, float[] columnWidthPercentage) {
        int tW = tabela.getWidth();
        TableColumn column;
        TableColumnModel jTableColumnModel = tabela.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }
    
    // Exibir
    public static void exibirTela(JInternalFrame frame, Container parent, boolean maximized) {
        //parent.add(frame);
        frame.setVisible(true);
        frame.requestFocusInWindow();
        parent.add(frame);
        try {
            frame.setMaximum(maximized);
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            
        }
        //parent.getComponent(parent.getComponentCount() - 1).setVisible(true);
        //Yagami.createFrame(parent, frame);
        
    }
    
    // Método para criar vizualização do InternalFrame
    /*
    public static void createFrame(Container parent) {
        JInternalFrame frame = new JInternalFrame();
        frame.setVisible(true);
        frame.requestFocusInWindow();
        parent.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) { }
    }
    */
}
