/*
 * $Id: DrawingPanel.java,v 1.23 2005/07/11 15:59:49 schylek Exp $
 *
 * Copyright (C) 2005  Marcin 'golish' Goliszewski <golish@niente.eu.org>,
 *                     Slawomir 'schylek' Chylek <schylek@aster.pl>
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
 * @author <a href="mailto:schylek@aster.pl">Slawomir 'schylek' Chylek</a>
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

    }
    // </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        resetXorCoords();
        
        if (currentTool == LINE_TOOL && lastX >= 0 && lastY >= 0) {
            currentColor = currentOutlineColor;
            drawLine(evt.getX(), evt.getY(), lastX, lastY);
            
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3 && brokenLine == true) {
                lastX = evt.getX();
                lastY = evt.getY();
            }
        }
        
        int x = (evt.getX() - lastX > 0) ? lastX : evt.getX();
        int y = (evt.getY() - lastY > 0) ? lastY : evt.getY();
        
        if (currentTool == OVAL_TOOL && lastX >= 0 && lastY >= 0) {
            drawOval( x, y, Math.abs(lastX - evt.getX()), Math.abs(lastY - evt.getY()));
        }
        
        if (currentTool == RECT_TOOL && lastX >= 0 && lastY >= 0) {
            drawRect( x, y, Math.abs(lastX - evt.getX()), Math.abs(lastY - evt.getY()));
        }
        
        if (getCursor().getType() != java.awt.Cursor.DEFAULT_CURSOR && brokenLine == false) {
            java.awt.Cursor normalCursor = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
            setCursor(normalCursor);
        }
        
        if(brokenLine == false)
            resetCoords();
                        
        repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        java.awt.Cursor moveCursor = new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR);
        setCursor(moveCursor);
        
        if ((currentTool == LINE_TOOL || currentTool == OVAL_TOOL || currentTool == RECT_TOOL)
            && evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            if (brokenLine == false) {
                lastX = evt.getX();
                lastY = evt.getY();
            }
        }
        
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            lastButton = 1;
            brokenLine = false;
        } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            lastButton = 3;
        }
    }//GEN-LAST:event_formMousePressed
    
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        Main.getGUI().setCoords(evt.getX(), evt.getY());
        
        if (currentTool == LINE_TOOL && brokenLine == true) {
            if (drawXor == true) 
                drawXorLine(lastXorX, lastXorY, lastX, lastY);
            
            drawXorLine(evt.getX(),evt.getY(), lastX, lastY);
            
            drawXor = true;
            lastXorX = evt.getX();
            lastXorY = evt.getY();            
            
            repaint();
        } else if (currentTool != LINE_TOOL && brokenLine == true) {
            brokenLine = false;
            
            drawXorLine(lastXorX, lastXorY, lastX, lastY);
            repaint();
            
            resetCoords();
            resetXorCoords();
            
            java.awt.Cursor normalCursor = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);
            setCursor(normalCursor);
        }
    }//GEN-LAST:event_formMouseMoved
    
    protected void paintComponent(java.awt.Graphics g) {
        if (drawing == null) {
            initDrawing();
        }
            
        g.drawImage(drawing, 0, 0, this);
    }
    
    /**
     * Initializes the drawing
     * @see netboard.DrawingPanel#drawing
     */
    private void initDrawing() {
        drawing = new java.awt.image.BufferedImage(getWidth(), getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        graphics.setColor(java.awt.Color.white);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.dispose();        
        repaint();
    }
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (currentTool == PEN_TOOL) {
            if (lastButton == 1) {
                currentColor = currentOutlineColor;
            } else if (lastButton == 3) {
                currentColor = currentFillColor;
            }
            
            lastX = evt.getX();
            lastY = evt.getY();
            drawLine(evt.getX(), evt.getY(), lastX, lastY);
            
            resetCoords();
        } else if (currentTool == LINE_TOOL && evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                
                brokenLine = true;
                
                java.awt.Cursor moveCursor = new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR);
                setCursor(moveCursor);            
            }
        } else if (currentTool == LINE_TOOL && brokenLine == true) {
            drawLine(evt.getX(), evt.getY(), lastX, lastY);           
        } else if (currentTool == EREASER_TOOL) {
            if (lastButton == 1) {
                currentColor = java.awt.Color.white;
            } else if (lastButton == 3) {
                currentColor = currentFillColor;
            }
            drawErase(evt.getX() - 12, evt.getY() - 12, 25, 25);
        }
        repaint();
    }//GEN-LAST:event_formMouseClicked
    
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Main.getGUI().setCoords(evt.getX(), evt.getY());
        
        if (currentTool == PEN_TOOL) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            }
            
            if (lastButton == 1) {
                currentColor = currentOutlineColor;
            } else if (lastButton == 3) {
                currentColor = currentFillColor;
            }
            
            drawLine(evt.getX(), evt.getY(), lastX, lastY);
            lastX = evt.getX();
            lastY = evt.getY();
            repaint();
        } else if (currentTool == EREASER_TOOL) {
            if (lastX < 0 || lastY < 0) {
                lastX = evt.getX();
                lastY = evt.getY();
                return;
            }
            
            if (lastButton == 1) {
                currentColor = java.awt.Color.white;
            } else if (lastButton == 3) {
                currentColor = currentFillColor;
            }            

            drawErase(evt.getX() - 12, evt.getY() - 12, 25, 25);
            lastX = evt.getX();
            lastY = evt.getY();
            repaint();
        } else if (currentTool == RECT_TOOL) {
            int x = (evt.getX() - lastX > 0) ? lastX : evt.getX();
            int y = (evt.getY() - lastY > 0) ? lastY : evt.getY();

            if (drawXor == true)
                drawXorRect(lastXorX, lastXorY, lastXorW, lastXorH);
                
            drawXorRect(x, y, Math.abs(lastX - evt.getX()), Math.abs(lastY - evt.getY()));
            lastXorW = Math.abs(evt.getX() - lastX);
            lastXorH = Math.abs(evt.getY() - lastY);
            
            drawXor = true;
            lastXorX = x;
            lastXorY = y;
        } else if (currentTool == OVAL_TOOL) {
            int x = (evt.getX() - lastX > 0) ? lastX : evt.getX();
            int y = (evt.getY() - lastY > 0) ? lastY : evt.getY();

            if (drawXor == true)
                drawXorOval(lastXorX, lastXorY, lastXorW, lastXorH);
            
            drawXorOval(x, y, Math.abs(lastX - evt.getX()), Math.abs(lastY - evt.getY()));
            lastXorW = Math.abs(evt.getX() - lastX);
            lastXorH = Math.abs(evt.getY() - lastY);
            
            drawXor = true;
            lastXorX = x;
            lastXorY = y;
        } else if (currentTool == LINE_TOOL && lastButton == 1) {
            if (lastXorX >= 0 && lastXorY >= 0) {
                drawXorLine(lastXorX, lastXorY, lastX, lastY);
            }
                
            drawXorLine(evt.getX(),evt.getY(), lastX, lastY);
            
            drawXor = true;
            lastXorX = evt.getX();
            lastXorY = evt.getY();            
        } else if (currentTool == LINE_TOOL && brokenLine == true) {
            if (lastXorX >= 0 && lastXorY >= 0) {
                drawXorLine(lastXorX, lastXorY, lastX, lastY);
            }
                
            drawXorLine(evt.getX(),evt.getY(), lastX, lastY);
            
            lastXorX = evt.getX();
            lastXorY = evt.getY();
        }
        
        repaint();
    }//GEN-LAST:event_formMouseDragged
    
     /**
     * Draws a line.
     */
    private void drawLine(int x, int y, int x1, int y1) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        graphics.setColor(currentColor);
        graphics.drawLine(x, y, lastX, lastY);
    };
    
    /**
     * Draws a line in XOR mode.
     */
    private void drawXorLine(int x, int y, int x1, int y1) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        graphics.setXORMode(graphics.getBackground());
        
        if (currentColor.equals(java.awt.Color.black)) {
            graphics.setColor(new java.awt.Color(51, 0, 51));
        } else {
            graphics.setColor(currentColor);
        }
            
        graphics.drawLine(x, y, x1, y1);
        
        graphics.setPaintMode();
    };

    /**
     * Draws a rectangle.
     */
    private void drawRect(int x, int y, int w, int h) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        if (fill == true) {
            graphics.setColor(currentFillColor);                
            graphics.fillRect(x, y, w, h);
        }

        if (outline == true) {
            graphics.setColor(currentOutlineColor);
            graphics.drawRect(x, y, w, h);                
        }
    };
    
    /**
     * Draws a rectangle in XOR mode.
     */
    private void drawXorRect(int x, int y, int w, int h) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        graphics.setXORMode(graphics.getBackground());
        
        if (fill == true) {
            if (currentFillColor.equals(java.awt.Color.black)) {
                graphics.setColor(new java.awt.Color(51, 0, 51));
            } else {
                graphics.setColor(currentFillColor);
            }
            
            graphics.fillRect(x, y, w, h);
        }
        
        if (outline == true) {
            if (currentOutlineColor.equals(java.awt.Color.black)) {
                graphics.setColor(new java.awt.Color(51, 0, 51));
            } else {
                graphics.setColor(currentOutlineColor);
            }
            
            graphics.drawRect(x, y, w, h);                
        }
        
        graphics.setPaintMode();
    };

    /**
     * Draws an oval.
     */
    private void drawOval(int x, int y, int w, int h) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        if (fill == true) {
            graphics.setColor(currentFillColor);
            graphics.fillOval(x, y, w, h);
        }

        if (outline == true) {
            graphics.setColor(currentOutlineColor);
            graphics.drawOval(x, y, w, h);                
        }
    };
    
    /**
     * Draws an oval in XOR mode.
     */
    private void drawXorOval(int x, int y, int w, int h) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        graphics.setXORMode(graphics.getBackground());
        
        if (fill == true) {
            if (currentFillColor.equals(java.awt.Color.black)) {
                graphics.setColor(new java.awt.Color(51, 0, 51));
            } else {
                graphics.setColor(currentFillColor);
            }

            graphics.fillOval(x, y, w, h);
        }

        if (outline == true) {
            if (currentOutlineColor.equals(java.awt.Color.black)) {
                graphics.setColor(new java.awt.Color(51, 0, 51));
            } else {
                graphics.setColor(currentOutlineColor);
            }

            graphics.drawOval(x, y, w, h);                
        }
        
        graphics.setPaintMode();
    };   
    
    /**
     * Erases.
     */
    private void drawErase(int x, int y, int w, int h) {
        java.awt.Graphics2D graphics = drawing.createGraphics();
        
        graphics.setColor(currentColor);                
        graphics.fillRect(x, y, w, h);
    };
    
    /**
     * Sets the current tool used to draw on the panel
     * @param tool Tool to set the drawing tool to
     * @see netboard.DrawingPanel#currentTool
     */
    public void setCurrentTool(int tool) {
        currentTool = tool;
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
     * Resets the saved xor coordinates (<CODE>lastXorX</CODE> and <CODE>lastXorY</CODE>) to <CODE>-1</CODE> (i.e. invalidates them)
     * @see netboard.DrawingPanel#lastXorX
     * @see netboard.DrawingPanel#lastXorY
     */
    private void resetXorCoords() {
        lastXorX = lastXorY = -1;
        lastXorH = lastXorW = 0;
        drawXor = false;
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
     * @see netboard.DrawingPanel#drawing
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
    public static final int PEN_TOOL = 0;
    public static final int LINE_TOOL = 1;
    public static final int RECT_TOOL = 2;
    public static final int OVAL_TOOL = 3;
    public static final int EREASER_TOOL = 4;    
    /**
     * Current drawing tool
     * @see netboard.DrawingPanel#setCurrentTool(int)
     */
    private int currentTool = PEN_TOOL;
    /**
     * Current outline drawing color
     * @see netboard.DrawingPanel#setCurrentOutlineColor
     * @see netboard.DrawingPanel#getCurrentOutlineColor
     */
    private java.awt.Color currentOutlineColor = new java.awt.Color(51, 0, 51);
    /**
     * Current filling color
     * @see netboard.DrawingPanel#setCurrentFillColor
     * @see netboard.DrawingPanel#getCurrentFillColor
     */    
    private java.awt.Color currentFillColor = java.awt.Color.white;    
    /**
     * Current color
     */    
    private java.awt.Color currentColor = currentOutlineColor;
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
     * Last used X coordinate for xor drawing; valid if greater than <CODE>0</CODE>
     * @see netboard.DrawingPanel#resetXorCoords
     */
    private int lastXorX = -1;
    /**
     * Last used Y coordinate for xor drawing; valid if greater than <CODE>0</CODE>
     * @see netboard.DrawingPanel#resetXorCoords
     */
    private int lastXorY = -1;
    /**
     * Last height of xor drawing.
     */
    private int lastXorH = 0;
    /**
     * Last width of xor drawing.
     */
    private int lastXorW = 0;
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
     * @see netboard.DrawingPanel#getOutline
     */    
    private boolean outline = true;
    /**
     * Tells if we are in mode of drawing a broken line.
     */    
    private boolean brokenLine = false;
    /**
     * Tells if we have to redraw last XOR drawing (to make it disappear).
     */    
    private boolean drawXor = false;
    
    // End of my variables declaration
}