/*
 * $Id: GUI.java,v 1.13 2005/05/19 15:22:53 golish Exp $
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
    private class PNGFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(java.io.File file) {
            return file.getName().toLowerCase().endsWith(".png") || file.isDirectory();
        }
        
        public String getDescription() {
            return "PNG image (*.png)";
        }
    }
    
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
        ovalButton = new javax.swing.JButton();
        rectangleButton = new javax.swing.JButton();
        ereaserButton = new javax.swing.JButton();
        colorChooserPanel = new javax.swing.JPanel();
        outlineLabel = new javax.swing.JLabel();
        outlineColorChooserButton = new javax.swing.JButton();
        fillLabel = new javax.swing.JLabel();
        fillColorChooserButton = new javax.swing.JButton();
        fillCheckBox = new javax.swing.JCheckBox();
        statusBar = new javax.swing.JLabel();
        backgroundPanel = new javax.swing.JPanel();
        drawingPanel = new netboard.DrawingPanel();
        mainMenu = new javax.swing.JMenuBar();
        applicationMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        disconnectMenuItem = new javax.swing.JMenuItem();
        applicationSeparator = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        imageMenu = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        imageSeparator = new javax.swing.JSeparator();
        clearMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Main.getAppName());
        setName(Main.getAppName() + " " + Main.getVersion());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        toolsToolbar.setFloatable(false);
        toolsToolbar.setOrientation(1);
        toolsToolbar.setMaximumSize(new java.awt.Dimension(80, 127));
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

        ovalButton.setText("Oval");
        ovalButton.setMaximumSize(new java.awt.Dimension(75, 27));
        ovalButton.setMinimumSize(new java.awt.Dimension(75, 27));
        ovalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ovalButtonActionPerformed(evt);
            }
        });

        toolsToolbar.add(ovalButton);

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

        outlineLabel.setText("Outline:");
        colorChooserPanel.add(outlineLabel);
        outlineLabel.setBounds(0, 50, 70, 15);

        outlineColorChooserButton.setBackground(drawingPanel.getCurrentOutlineColor());
        outlineColorChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outlineColorChooserButtonActionPerformed(evt);
            }
        });

        colorChooserPanel.add(outlineColorChooserButton);
        outlineColorChooserButton.setBounds(10, 80, 50, 10);

        fillLabel.setText("Fill:");
        colorChooserPanel.add(fillLabel);
        fillLabel.setBounds(0, 100, 30, 15);

        fillColorChooserButton.setBackground(drawingPanel.getCurrentFillColor());
        fillColorChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillColorChooserButtonActionPerformed(evt);
            }
        });

        colorChooserPanel.add(fillColorChooserButton);
        fillColorChooserButton.setBounds(10, 130, 50, 10);

        fillCheckBox.setSelected(true);
        fillCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillCheckBoxActionPerformed(evt);
            }
        });

        colorChooserPanel.add(fillCheckBox);
        fillCheckBox.setBounds(30, 100, 98, 23);

        toolsToolbar.add(colorChooserPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipady = 400;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(toolsToolbar, gridBagConstraints);

        statusBar.setText("Ready");
        statusBar.setMaximumSize(new java.awt.Dimension(2147483647, 15));
        statusBar.setMinimumSize(new java.awt.Dimension(280, 15));
        statusBar.setPreferredSize(new java.awt.Dimension(500, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 243;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        getContentPane().add(statusBar, gridBagConstraints);

        backgroundPanel.setLayout(new java.awt.GridBagLayout());

        backgroundPanel.setBackground(java.awt.Color.white);
        backgroundPanel.setFocusable(false);
        backgroundPanel.setMinimumSize(new java.awt.Dimension(500, 500));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        drawingPanel.setLayout(new java.awt.BorderLayout());

        drawingPanel.setFocusable(false);
        drawingPanel.setMinimumSize(new java.awt.Dimension(500, 500));
        drawingPanel.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        backgroundPanel.add(drawingPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(backgroundPanel, gridBagConstraints);

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

        imageMenu.setMnemonic('I');
        imageMenu.setText("Image");
        loadMenuItem.setMnemonic('L');
        loadMenuItem.setText("Load...");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        loadMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loadMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loadMenuItemMouseExited(evt);
            }
        });

        imageMenu.add(loadMenuItem);

        saveMenuItem.setMnemonic('S');
        saveMenuItem.setText("Save...");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        saveMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveMenuItemMouseExited(evt);
            }
        });

        imageMenu.add(saveMenuItem);

        imageMenu.add(imageSeparator);

        clearMenuItem.setMnemonic('C');
        clearMenuItem.setText("Clear");
        clearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMenuItemActionPerformed(evt);
            }
        });
        clearMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearMenuItemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearMenuItemMouseExited(evt);
            }
        });

        imageMenu.add(clearMenuItem);

        mainMenu.add(imageMenu);

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

    private void fillCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillCheckBoxActionPerformed
        drawingPanel.setFill(fillCheckBox.isSelected());
    }//GEN-LAST:event_fillCheckBoxActionPerformed

    private void fillColorChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillColorChooserButtonActionPerformed
        colorChooserType = "fill";
        
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
    }//GEN-LAST:event_fillColorChooserButtonActionPerformed

    private void saveMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_saveMenuItemMouseExited

    private void saveMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMenuItemMouseEntered
        setStatus("Save the image to file");
    }//GEN-LAST:event_saveMenuItemMouseEntered

    private void loadMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_loadMenuItemMouseExited

    private void loadMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadMenuItemMouseEntered
        setStatus("Load an image from file");
    }//GEN-LAST:event_loadMenuItemMouseEntered

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        restorePreviousStatus();
        
        javax.swing.JFileChooser fileChooserDialog = new javax.swing.JFileChooser();
        
        fileChooserDialog.setFileFilter(new PNGFilter());
        fileChooserDialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        fileChooserDialog.setDialogTitle("Save the image to file");
        int result = fileChooserDialog.showSaveDialog(this);
       
        if (result == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
        } else if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooserDialog.getSelectedFile();
            
            if (file.getName().toLowerCase().endsWith(".png") != true) {
                file = new java.io.File(file.getAbsolutePath() + ".png");
            }
            
            if (file.exists()) {
                int response = javax.swing.JOptionPane.showConfirmDialog (null, 
                        "File " + file.getName() + " already exists. Overwrite?","Confirm Overwrite",
                        javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
            
                if (response == javax.swing.JOptionPane.CANCEL_OPTION) { 
                    return;    
                }
            }
        
            try {
                java.awt.image.BufferedImage img = getImage();
                java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(img.getWidth(), img.getHeight(), img.getType());
                java.awt.Graphics2D graphics = image.createGraphics();              
                
                graphics.setBackground(java.awt.Color.white);
                graphics.clearRect(0, 0, image.getWidth(), image.getHeight());                
                graphics.drawImage(img, 0, 0, null);
                graphics.dispose();
                
                javax.imageio.ImageIO.write(image, "png", file);
            } catch (java.io.IOException e) {
                showError("Error saving the image: " + e.getMessage());
            }
        }        
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        restorePreviousStatus();
                
        javax.swing.JFileChooser fileChooserDialog = new javax.swing.JFileChooser();

        fileChooserDialog.setFileFilter(new PNGFilter());        
        fileChooserDialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);        
        fileChooserDialog.setDialogTitle("Open an image file");        
        int result = fileChooserDialog.showOpenDialog(this);
        
        if (result == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
        } else if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooserDialog.getSelectedFile();
            
            try {
                setImage(javax.imageio.ImageIO.read(file));
            } catch (java.io.IOException e) {
                showError("Error loading the image: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void clearMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_clearMenuItemMouseExited

    private void clearMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMenuItemMouseEntered
        setStatus("Clear the drawing area");
    }//GEN-LAST:event_clearMenuItemMouseEntered

    private void clearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMenuItemActionPerformed
        restorePreviousStatus();
        
        drawingPanel.clearImage();
    }//GEN-LAST:event_clearMenuItemActionPerformed
    
    private void disconnectMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_disconnectMenuItemMouseExited
    
    private void disconnectMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectMenuItemMouseEntered
        setStatus("Close connection");
    }//GEN-LAST:event_disconnectMenuItemMouseEntered
    
    private void disconnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectMenuItemActionPerformed
        restorePreviousStatus();
                
        Main.disconnect();
    }//GEN-LAST:event_disconnectMenuItemActionPerformed
    
    private void connectMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectMenuItemMouseExited
        restorePreviousStatus();
    }//GEN-LAST:event_connectMenuItemMouseExited
    
    private void connectMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectMenuItemMouseEntered
        setStatus("Open connection");
    }//GEN-LAST:event_connectMenuItemMouseEntered
    
    private void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        restorePreviousStatus();
                
        String options[] = {"Server", "Client"};
        Main.setMode(javax.swing.JOptionPane.showOptionDialog(this,
                "Would you like to be the server or the client of your network connection?",
                Main.getAppName() + " - Connection",
                javax.swing.JOptionPane.DEFAULT_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]));
        
        if (Main.getMode() == Main.CLIENT_MODE) {
            Main.setDestination((String)javax.swing.JOptionPane.showInputDialog(this,
                    "Enter the server's address:", Main.getAppName() + " - Open connection",
                    javax.swing.JOptionPane.QUESTION_MESSAGE, null, null, "localhost"));
        }
        
        Main.connect();
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
    }//GEN-LAST:event_ereaserButtonActionPerformed
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        restorePreviousStatus();
                
        Main.close();
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        restorePreviousStatus();
                
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
    
    private void outlineColorChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outlineColorChooserButtonActionPerformed
        colorChooserType = "outline";
        
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
    }//GEN-LAST:event_outlineColorChooserButtonActionPerformed

    private void colorChooserOkButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (colorChooserType.equals("outline")) {
            changeOutlineColor(colorChooser.getColor());
        } else if (colorChooserType.equals("fill")) {
            changeFillColor(colorChooser.getColor());
        }
        
        colorChooserType = "";
    }
    
    private void rectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleButtonActionPerformed
        drawingPanel.setCurrentTool("Rectangle");
    }//GEN-LAST:event_rectangleButtonActionPerformed
    
    private void ovalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovalButtonActionPerformed
        drawingPanel.setCurrentTool("Oval");
    }//GEN-LAST:event_ovalButtonActionPerformed
    
    private void lineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineButtonActionPerformed
        drawingPanel.setCurrentTool("Line");
    }//GEN-LAST:event_lineButtonActionPerformed
    
    private void penButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penButtonActionPerformed
        drawingPanel.setCurrentTool("Pen");
    }//GEN-LAST:event_penButtonActionPerformed
    
    /**
     * Does everything that has to be done after user changes the outline drawing color
     * @param color The new outline drawing color
     */
    private void changeOutlineColor(java.awt.Color color) {
        drawingPanel.setCurrentOutlineColor(color);
        outlineColorChooserButton.setBackground(drawingPanel.getCurrentOutlineColor());
    }
    
    /**
     * Does everything that has to be done after user changes the filing color
     * @param color The new filling color
     */
    private void changeFillColor(java.awt.Color color) {
        drawingPanel.setCurrentFillColor(color);
        fillColorChooserButton.setBackground(drawingPanel.getCurrentFillColor());
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
    
    /**
     * Shows an informational message
     * @param message The informational message
     */
    public void showInfo(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message,
                Main.getAppName() + " - Notice", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu applicationMenu;
    private javax.swing.JSeparator applicationSeparator;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JMenuItem clearMenuItem;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JPanel colorChooserPanel;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JMenuItem disconnectMenuItem;
    private netboard.DrawingPanel drawingPanel;
    private javax.swing.JButton ereaserButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JCheckBox fillCheckBox;
    private javax.swing.JButton fillColorChooserButton;
    private javax.swing.JLabel fillLabel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu imageMenu;
    private javax.swing.JSeparator imageSeparator;
    private javax.swing.JButton lineButton;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JButton outlineColorChooserButton;
    private javax.swing.JLabel outlineLabel;
    private javax.swing.JButton ovalButton;
    private javax.swing.JButton penButton;
    private javax.swing.JButton rectangleButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JLabel statusBar;
    private javax.swing.JToolBar toolsToolbar;
    // End of variables declaration//GEN-END:variables
    
    // My variables declaration
    private javax.swing.JDialog colorChooserDialog;
    private String colorChooserType = "";
    /**
     * Text which was previously displayed on the status bar
     */
    private String prevStatusText = null;
    // End of my variables declaration
    
}