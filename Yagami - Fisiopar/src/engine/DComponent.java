/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author Juan
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import util.Yagami;

public class DComponent extends JLabel {

    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    
    public DComponent(int x, int y, JInternalFrame tela, Icon icon) {
        // Definições padrão
        setBorder(new LineBorder(Color.BLACK, 0));
        setBackground(Color.BLUE);
        setBounds(x, y, 48, 68);
        setOpaque(false);
        setIcon(icon);
        
        // Ao clicar duas vezes...
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    try {
                        e.consume();
                        // Adicionar tela ao Container Parent
                        try {
                            getParent().add(tela);
                        } catch(Exception q) {
                            JOptionPane.showMessageDialog(null, "Não é possível abrir duas janelas iguais!\n" + q);
                        }

                        try {
                            tela.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) { }
                        getParent().getComponent(getParent().getComponentCount() - 1).setVisible(true);
                        //Yagami.createFrame(tela);
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, error);
                    }
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
    }
    
    // Método para criar vizualização do InternalFrame
    /*
    protected void createFrame() {
        JInternalFrame frame = new JInternalFrame();
        frame.setVisible(true);
        getParent().add(frame);
        
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            
        }
    }
    */
    

}
