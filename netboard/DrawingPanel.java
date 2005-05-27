/*
 * $Id: DrawingPanel.java,v 1.10 2005/05/27 13:19:33 golish Exp $
 *
 * Copyright (C) 2005  Marcin 'golish' Goliszewski <golish@niente.eu.org>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
 * USA.
 */

package netboard;

/**
 * A derivative of <CODE>javax.swing.JPanel</CODE> implementing drawing board capabilities
 * @author <a href="mailto:golish@niente.eu.org">Marcin 'golish' Goliszewski</a>
 */
public class DrawingPanel extends javax.swing.JPanel {
    /** Creates new DrawingPanel form */
    public DrawingPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);

        setBackground(java.awt.Color.white);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

    }
    // </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            lastButton = 1;
        } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            lastButton = 3;
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        Main.getGUI().setCoords(evt.getX(), evt.getY());
    }//GEN-LAST:event_formMouseMoved
    
    protected void paintComponent(java.awt.Graphics g) {
        if (drawing == null)
            initDrawing();
        
        g.drawImage(drawing, 0, 0, this);
    }
    
    /**
     * Initializes the drawing
     * @see netboard.DrawingPanel#drawing
     */
    private void initDrawing() {
        drawing = new java.awt.image.BufferedImage(getWidth(), getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D graphics = drawing.createGraphics();
        graphics.setBackground(new java.awt.Color(0.0f, 0.0f, 0.0f, 0.0f));
        graphics.clearRect(0, 0, getWidth(), getHeight());
        graphics.dispose();
    }
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        if (currentTool.equals("Pen")) {
            if (lastButton == 1) {
                graphics.setColor(currentOutlineColor);
            } else if (lastButton == 3) {
                graphics.setColor(currentFillColor);
            }
            
            graphics.drawLine(evt.getX(), evt.getY(), evt.getX(), evt.getY());
            resetCoords();
        } else if (currentTool.equals("Line")) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            } else {
                graphics.setColor(currentOutlineColor);
                graphics.drawLine(evt.getX(), evt.getY(), lastX, lastY);
                lastX = evt.getX();
                lastY = evt.getY();
            }
        } else if (currentTool.equals("Oval")) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            } else {
                int x = (evt.getX() - lastX > 0) ? lastX : evt.getX();
                int y = (evt.getY() - lastY > 0) ? lastY : evt.getY();
            
                if (outline == true) {
                    graphics.setColor(currentOutlineColor);
                    graphics.drawOval(x, y, Math.abs(evt.getX() - lastX), Math.abs(evt.getY() - lastY));                
                }
                
                if (fill == true) {
                    graphics.setColor(currentFillColor);                
                    graphics.fillOval(x + 1, y + 1, Math.abs(evt.getX() - lastX) - 2, Math.abs(evt.getY() - lastY) - 2);
                }
                
                resetCoords();
            }
        } else if (currentTool.equals("Rectangle")) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            } else {
                int x = (evt.getX() - lastX > 0) ? lastX : evt.getX();
                int y = (evt.getY() - lastY > 0) ? lastY : evt.getY();
            
                if (outline == true) {
                    graphics.setColor(currentOutlineColor);
                    graphics.drawRect(x, y, Math.abs(evt.getX() - lastX), Math.abs(evt.getY() - lastY));
                }

                if (fill == true) {                
                    graphics.setColor(currentFillColor);                
                    graphics.fillRect(x + 1, y + 1, Math.abs(evt.getX() - lastX) - 1, Math.abs(evt.getY() - lastY) - 1);
                }
                
                resetCoords();
            }
        } else if (currentTool.equals("Ereaser")) {
            if (lastButton == 1) {
                graphics.setColor(java.awt.Color.white);
            } else if (lastButton == 3) {
                graphics.setColor(currentFillColor);
            }
            
            graphics.fillRect(evt.getX(), evt.getY(), 25, 25);
        }
        
        graphics.dispose();
        repaint();
    }//GEN-LAST:event_formMouseClicked
    
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Main.getGUI().setCoords(evt.getX(), evt.getY());
        
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        if (currentTool.equals("Pen")) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            }

            if (lastButton == 1) {
                graphics.setColor(currentOutlineColor);
            } else if (lastButton == 3) {
                graphics.setColor(currentFillColor);
            }            

            graphics.drawLine(lastX, lastY, evt.getX(), evt.getY());
            lastX = evt.getX();
            lastY = evt.getY();
        } else if (currentTool.equals("Ereaser")) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            }
            
            if (lastButton == 1) {
                graphics.setColor(java.awt.Color.white);
            } else if (lastButton == 3) {
                graphics.setColor(currentFillColor);
            }            

            graphics.fillRect(evt.getX(), evt.getY(), 25, 25);
            lastX = evt.getX();
            lastY = evt.getY();
        }
        
        graphics.dispose();
        repaint();
    }//GEN-LAST:event_formMouseDragged
    
    /**
     * Sets the current tool used to draw on the panel
     * @param tool Tool to set the drawing tool to
     * @see netboard.DrawingPanel#currentTool
     */
    public void setCurrentTool(String tool) {
        currentTool = tool;
        resetCoords();
    }
    
    /**
     * Returns the current color used to draw the outline of the shapes drawn on the panel
     * @return Current outline drawing color
     * @see netboard.DrawingPanel#currentOutlineColor
     */
    public java.awt.Color getCurrentOutlineColor() {
        return currentOutlineColor;
    }
    
    /**
     * Returns the current color used to fill the shapes drawn on the panel
     * @return Current filling color
     * @see netboard.DrawingPanel#currentOutlineColor
     */
    public java.awt.Color getCurrentFillColor() {
        return currentFillColor;
    }    
    
    /**
     * Sets the current color used to draw the outline of the shapes drawn on the panel
     * @param color Color to set the current outline drawing color to
     * @see netboard.DrawingPanel#currentOutlineColor
     */
    public void setCurrentOutlineColor(java.awt.Color color) {
        currentOutlineColor = color;
        resetCoords();
    }
    
    /**
     * Sets the current color used to fill the shapes drawn on the panel
     * @param color Color to set the current filling color to
     * @see netboard.DrawingPanel#currentFillColor
     */
    public void setCurrentFillColor(java.awt.Color color) {
        currentFillColor = color;
        resetCoords();
    }    
    
    /**
     * Resets the saved coordinates (<CODE>lastX</CODE> and <CODE>lastY</CODE>) to <CODE>-1</CODE> (i.e. invalidates them)
     * @see netboard.DrawingPanel#lastX
     * @see netboard.DrawingPanel#lastY
     */
    public void resetCoords() {
        lastX = lastY = -1;
    }
    
    /**
     * Returns the image from the panel
     * @return Image from the panel
     * @see netboard.DrawingPanel#drawing
     */
    public java.awt.image.BufferedImage getImage() {
        return drawing;
    }
    
    /**
     * Sets the image on the panel
     * @param image New image to be shown on the panel
     * @see netboard.DrawingPanel#drawing
     */
    public void setImage(java.awt.image.BufferedImage image) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        repaint();
    }
    
    /**
     * Clears the current image (i.e. makes it completely empty)
     * @see netboard.DrawingPanel#image
     */
    public void clearImage() {
        drawing = null;
        System.gc();
        repaint();
    }
    
    /**
     * Sets the variable deciding if the shapes should be filled or not
     * @param f Tells if the shapes should be filled or not
     * @see netboard.DrawingPanel#fill
     */
    public void setFill(boolean f) {
        fill = f;
    }
    
    /**
     * Returns the variable deciding if the shapes should be filled or not
     * @return Tells if the shapes should be filled or not
     * @see netboard.DrawingPanel#fill
     */
    public boolean getFill() {
        return fill;
    }    
    
    /**
     * Sets the variable deciding if the shapes should be drawn with or without outline
     * @param o Tells if the shapes should be drawn with or without outline
     * @see netboard.DrawingPanel#outline
     */
    public void setOutline(boolean o) {
        outline = o;
    }    
    
    /**
     * Returns the variable deciding if the shapes should be drawn with or without outline
     * @return Tells if the shapes should be drawn with or without outline
     * @see netboard.DrawingPanel#outline
     */
    public boolean getOutline() {
        return outline;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    // My variables declaration
    /**
     * Current drawing tool
     * @see netboard.DrawingPanel#setCurrentTool(String)
     */
    private String currentTool = "Pen";
    /**
     * Current outline drawing color
     * @see netboard.DrawingPanel#setCurrentOutlineColor
     * @see netboard.DrawingPanel#getCurrentOutlineColor
     */
    private java.awt.Color currentOutlineColor = java.awt.Color.black;
    /**
     * Current filling color
     * @see netboard.DrawingPanel#setCurrentFillColor
     * @see netboard.DrawingPanel#getCurrentFillColor
     */    
    private java.awt.Color currentFillColor = java.awt.Color.white;    
    /**
     * Current drawing
     * @see netboard.DrawingPanel#setImage
     * @see netboard.DrawingPanel#getImage
     */
    private java.awt.image.BufferedImage drawing = null;
    /**
     * Last used X coordinate; valid if greater than <CODE>0</CODE>
     * @see netboard.DrawingPanel#resetCoords
     */
    private int lastX = -1;
    /**
     * Last used Y coordinate; valid if greater than <CODE>0</CODE>
     * @see netboard.DrawingPanel#resetCoords
     */
    private int lastY = -1;
    /**
     * Last pressed button number. Used to determine the color of drawn lines, ereaser filling etc.
     */
    private int lastButton = 1;
    /**
     * Tells if the shapes should be filled or not
     * @see netboard.DrawingPanel#setFill
     * @see netboard.DrawingPanel#getFill
     */
    private boolean fill = true;
    /**
     * Tells if the shapes should be drawn with or without outline
     * @see netboard.DrawingPanel#setOutline
     * @see netbonetboard.DrawingPanel#getOutline
     */    
    private boolean outline = true;    
    // End of my variables declaration
}