/*
 * GUI.java
 *
 * Created on 18 march 2005, 12:59
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
 * Graphical User Interface for netboard
 * @author <a href="mailto:golish@niente.eu.org">Marcin 'golish' Goliszewski</a>
 */
public class GUI extends javax.swing.JFrame {
    
    /** Creates new GUI form */
    public GUI() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        colorChooser = new javax.swing.JColorChooser();
        toolsToolbar = new javax.swing.JToolBar();
        penButton = new javax.swing.JButton();
        lineButton = new javax.swing.JButton();
        circleButton = new javax.swing.JButton();
        rectangleButton = new javax.swing.JButton();
        ereaserButton = new javax.swing.JButton();
        colorChooserPanel = new javax.swing.JPanel();
        colorChooserButton = new javax.swing.JButton();
        drawingPanel = new netboard.DrawingPanel();
        statusBar = new javax.swing.JLabel();
        mainMenu = new javax.swing.JMenuBar();
        applicationMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        disconnectMenuItem = new javax.swing.JMenuItem();
        applicationSeparator = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        clearMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Main.getAppName() + " " + Main.getVersion());
        setName(Main.getAppName() + " " + Main.getVersion());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        toolsToolbar.setFloatable(false);
        toolsToolbar.setOrientation(1);
        toolsToolbar.setMaximumSize(new java.awt.Dimension(80, 32894));
        toolsToolbar.setMinimumSize(new java.awt.Dimension(80, 127));
        toolsToolbar.setPreferredSize(new java.awt.Dimension(80, 127));
        penButton.setText("Pen");
        penButton.setMaximumSize(new java.awt.Dimension(75, 27));
        penButton.setMinimumSize(new java.awt.Dimension(75, 27));
        penButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penButtonActionPerformed(evt);
            }
        });

        toolsToolbar.add(penButton);

        lineButton.setText("Line");
        lineButton.setMaximumSize(new java.awt.Dimension(75, 27));
        lineButton.setMinimumSize(new java.awt.Dimension(75, 27));
        lineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineButtonActionPerformed(evt);
            }
        });

        toolsToolbar.add(lineButton);

        circleButton.setText("Circle");
        circleButton.setMaximumSize(new java.awt.Dimension(75, 27));
        circleButton.setMinimumSize(new java.awt.Dimension(75, 27));
        circleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circleButtonActionPerformed(evt);
            }
        });

        toolsToolbar.add(circleButton);

        rectangleButton.setText("Rectangle");
        rectangleButton.setMaximumSize(new java.awt.Dimension(75, 27));
        rectangleButton.setMinimumSize(new java.awt.Dimension(75, 27));
        rectangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectangleButtonActionPerformed(evt);
            }
        });

        toolsToolbar.add(rectangleButton);

        ereaserButton.setText("Ereaser");
        ereaserButton.setMaximumSize(new java.awt.Dimension(75, 27));
        ereaserButton.setMinimumSize(new java.awt.Dimension(75, 27));
        ereaserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ereaserButtonActionPerformed(evt);
            }
        });

        toolsToolbar.add(ereaserButton);

        colorChooserPanel.setLayout(null);

        colorChooserButton.setBackground(drawingPanel.getCurrentColor());
        colorChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorChooserButtonActionPerformed(evt);
            }
        });

        colorChooserPanel.add(colorChooserButton);
        colorChooserButton.setBounds(10, 130, 50, 10);

        toolsToolbar.add(colorChooserPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipady = 153;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(toolsToolbar, gridBagConstraints);

        drawingPanel.setMinimumSize(new java.awt.Dimension(280, 200));
        drawingPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -220;
        gridBagConstraints.ipady = 80;
        getContentPane().add(drawingPanel, gridBagConstraints);

        statusBar.setText("Ready");
        statusBar.setMaximumSize(new java.awt.Dimension(2147483647, 15));
        statusBar.setMinimumSize(new java.awt.Dimension(280, 15));
        statusBar.setPreferredSize(new java.awt.Dimension(500, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 243;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        getContentPane().add(statusBar, gridBagConstraints);

        applicationMenu.setMnemonic('A');
        applicationMenu.setText("Application");
        connectMenuItem.setMnemonic('C');
        connectMenuItem.setText("Connect");
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerformed(evt);
            }
        });
        connectMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connectMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                connectMenuItemMouseExited(evt);
            }
        });

        applicationMenu.add(connectMenuItem);

        disconnectMenuItem.setMnemonic('D');
        disconnectMenuItem.setText("Disconnect");
        disconnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectMenuItemActionPerformed(evt);
            }
        });
        disconnectMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                disconnectMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                disconnectMenuItemMouseExited(evt);
            }
        });

        applicationMenu.add(disconnectMenuItem);

        applicationMenu.add(applicationSeparator);

        exitMenuItem.setMnemonic('X');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMenuItemMouseExited(evt);
            }
        });

        applicationMenu.add(exitMenuItem);

        mainMenu.add(applicationMenu);

        editMenu.setMnemonic('E');
        editMenu.setText("Edit");
        clearMenuItem.setMnemonic('C');
        clearMenuItem.setText("Clear");
        clearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMenuItemActionPerformed(evt);
            }
        });

        editMenu.add(clearMenuItem);

        mainMenu.add(editMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aboutMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aboutMenuItemMouseExited(evt);
            }
        });

        helpMenu.add(aboutMenuItem);

        mainMenu.add(helpMenu);

        setJMenuBar(mainMenu);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void clearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMenuItemActionPerformed
        drawingPanel.clearImage();
    }//GEN-LAST:event_clearMenuItemActionPerformed
    
    private void disconnectMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_disconnectMenuItemMouseExited
    
    private void disconnectMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectMenuItemMouseEntered
        setStatus("Close connection");
    }//GEN-LAST:event_disconnectMenuItemMouseEntered
    
    private void disconnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectMenuItemActionPerformed
        Main.getConnection().disconnect();
    }//GEN-LAST:event_disconnectMenuItemActionPerformed
    
    private void connectMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_connectMenuItemMouseExited
    
    private void connectMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectMenuItemMouseEntered
        setStatus("Open connection");
    }//GEN-LAST:event_connectMenuItemMouseEntered
    
    private void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        String options[] = {"Server", "Client"};
        Main.setMode(javax.swing.JOptionPane.showOptionDialog(this,
                "Would you like to be the server or the client of your network connection?",
                Main.getAppName() + " - Connection",
                javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]));
        
        if (Main.getMode() == 1) {
            Main.getConnection().setDestination((String)javax.swing.JOptionPane.showInputDialog(this,
                    "Enter the server's address:", Main.getAppName() + " - Open connection",
                    javax.swing.JOptionPane.QUESTION_MESSAGE, null, null, "localhost"));
        }
        
        Main.getConnection().interrupt();
    }//GEN-LAST:event_connectMenuItemActionPerformed
    
    private void aboutMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_aboutMenuItemMouseExited
    
    private void aboutMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuItemMouseEntered
        setStatus("About the application");
    }//GEN-LAST:event_aboutMenuItemMouseEntered
    
    private void exitMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_exitMenuItemMouseExited
    
    private void exitMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseEntered
        setStatus("Exit the application");
    }//GEN-LAST:event_exitMenuItemMouseEntered
    
    private void ereaserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ereaserButtonActionPerformed
        drawingPanel.setCurrentTool("Ereaser");
        drawingPanel.resetCoords();
    }//GEN-LAST:event_ereaserButtonActionPerformed
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        Main.close();
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this, Main.getAppName() + " v. " + Main.getVersionWithCodename() + "\n" +
                "A drawing board shared by two users over the network\n\n" +
                "Author: Marcin 'golish' Goliszewski <golish@niente.eu.org>\n\n" +
                "This program is free software; you can redistribute it and/or\n" +
                "modify it under the terms of the GNU General Public License\n" +
                "as published by the Free Software Foundation; either version 2\n" +
                "of the License, or (at your option) any later version.\n\n" +
                "This program is distributed in the hope that it will be useful,\n" +
                "but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
                "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
                "GNU General Public License for more details.",
                "About " + Main.getAppName() + " v. " + Main.getVersion(),
                javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
    }//GEN-LAST:event_aboutMenuItemActionPerformed
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Main.close();
    }//GEN-LAST:event_formWindowClosing
    
    private void colorChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorChooserButtonActionPerformed
        colorChooserDialog = javax.swing.JColorChooser.createDialog(
                this, Main.getAppName() + " - Choose a color", true, colorChooser,
                new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorChooserOkButtonActionPerformed(evt);
            }
        },
                null
                );
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                colorChooserDialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_colorChooserButtonActionPerformed
    
    private void colorChooserOkButtonActionPerformed(java.awt.event.ActionEvent evt) {
        changeColor(colorChooser.getColor());
        drawingPanel.resetCoords();
    }
    
    private void rectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleButtonActionPerformed
        drawingPanel.setCurrentTool("Rectangle");
        drawingPanel.resetCoords();
    }//GEN-LAST:event_rectangleButtonActionPerformed
    
    private void circleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circleButtonActionPerformed
        drawingPanel.setCurrentTool("Circle");
        drawingPanel.resetCoords();
    }//GEN-LAST:event_circleButtonActionPerformed
    
    private void lineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineButtonActionPerformed
        drawingPanel.setCurrentTool("Line");
        drawingPanel.resetCoords();
    }//GEN-LAST:event_lineButtonActionPerformed
    
    private void penButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penButtonActionPerformed
        drawingPanel.setCurrentTool("Pen");
        drawingPanel.resetCoords();
    }//GEN-LAST:event_penButtonActionPerformed
    
    /**
     * Does everything what has to be done after user changes the drawing color
     * @param color The new drawing color
     */
    private void changeColor(java.awt.Color color) {
        drawingPanel.setCurrentColor(color);
        colorChooserButton.setBackground(drawingPanel.getCurrentColor());
    }
    
    /**
     * Returns current image displayed on the drawing panel
     * @return Current image displayed on the drawing panel
     */
    public java.awt.image.BufferedImage getImage() {
        return drawingPanel.getImage();
    }
    
    /**
     * Sets the image to be displayed on the drawing panel
     * @param image New image to be displayed on the drawing panel
     */
    public void setImage(java.awt.image.BufferedImage image) {
        drawingPanel.setImage(image);
    }
    
    /**
     * Sets the text on the status bar
     * @param statusText Text to be shown on the status bar
     */
    public void setStatus(String statusText) {
        prevStatusText = statusBar.getText();
        statusBar.setText(statusText);
    }
    
    /**
     * Restores previous text on the status bar
     */
    public void restorePreviousStatus() {
        statusBar.setText(prevStatusText);
    }
    
    /**
     * Shows an error message
     * @param message The error message
     */
    public void showError(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, "An error occured!\n\n" + message,
                Main.getAppName() + " - Error!", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu applicationMenu;
    private javax.swing.JSeparator applicationSeparator;
    private javax.swing.JButton circleButton;
    private javax.swing.JMenuItem clearMenuItem;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JButton colorChooserButton;
    private javax.swing.JPanel colorChooserPanel;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JMenuItem disconnectMenuItem;
    private netboard.DrawingPanel drawingPanel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JButton ereaserButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton lineButton;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JButton penButton;
    private javax.swing.JButton rectangleButton;
    private javax.swing.JLabel statusBar;
    private javax.swing.JToolBar toolsToolbar;
    // End of variables declaration//GEN-END:variables
    
    // My variables declaration
    private javax.swing.JDialog colorChooserDialog;
    /**
     * Text which was previously displayed on the status bar
     */
    private String prevStatusText = null;
    // End of my variables declaration
    
}