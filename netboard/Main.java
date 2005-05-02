/*
 * $Id: Main.java,v 1.7 2005/05/02 14:02:29 golish Exp $
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
 * netboard - the main module <br/>
 * netboard is a drawing board which is shared by two users over the network
 * @author <a href="mailto:golish@niente.eu.org">Marcin 'golish' Goliszewski</a>
 * @version 0.1
 */
public class Main {
    
    /**
     * Initializes the application
     */
    static void init() {
        String lookAndFeel = javax.swing.UIManager.getCrossPlatformLookAndFeelClassName();
        try {
            javax.swing.UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) { 
            javax.swing.JOptionPane.showMessageDialog(null, "Fatal error occured!\n\nLook&Feel could not be set: " + e.getMessage(),
                    getAppName() + " - Error!", javax.swing.JOptionPane.ERROR_MESSAGE);
            close();
        }
    }
    
    /**
     * Closes the application doing all the necessary operations before
     */
    static void close() {
        if (isConnected()) {
            disconnect();
        }
        
        System.exit(0);
    }
    
    /**
     * Returns the name of this application
     * @return Name of the application
     * @see netboard.Main#appName
     * @see netboard.Main#getVersion()
     * @see netboard.Main#getVersionWithCodename()
     */
    public static String getAppName() {
        return appName;
    }
    
    /**
     * Returns current version of this application
     * @return Current version of this application
     * @see netboard.Main#version
     * @see netboard.Main#getAppName()
     * @see netboard.Main#getVersionWithCodename()
     */
    public static String getVersion() {
        return version;
    }
    
    /**
     * Returns current version of this application and the codename of this version
     * @return Current version of this application with the codename of this version appended
     * @see netboard.Main#codename
     * @see netboard.Main#getAppName()
     * @see netboard.Main#getVersion()
     */
    public static String getVersionWithCodename() {
        return version + " (" + codename + ")";
    }
    
    /**
     * Returns number of the port used for the network connections
     * @return Number of the port used for the network connections
     * @see netboard.Main#port
     */
    public static int getPort() {
        return port;
    }
    
    /**
     * Returns the current mode of network operations
     * @return Current mode of network operations: <CODE>0</CODE> - server, <CODE>1</CODE> - client
     * @see netboard.Main#mode
     * @see netboard.Main#setMode(int)
     */
    public static int getMode() {
        return mode;
    }
    /**
     * Sets the new mode of network operations
     * @param m New mode of network operations: <CODE>0</CODE> - server, <CODE>1</CODE> - client
     * @see netboard.Main#mode
     * @see netboard.Main#getMode()
     */
    static void setMode(int m) {
        mode = m;
    }
    
    /**
     * Returns the object representing GUI of the current application
     * @return The object representing GUI of the current application
     * @see netboard.Main#gui
     * @see netboard.GUI
     */
    static netboard.GUI getGUI() {
        return gui;
    }
    
    /**
     * Check if the application is connected to another instance of netboard
     * @return <CODE>true</CODE> if the application is connected to another instance of netboard, <CODE>false</CODE> otherwise
     * @see netboard.Main#connected
     * @see netboard.Main#setConnected(boolean)
     * @see netboard.SocketConnection#disconnect()
     */
    public static boolean isConnected() {
        return connected;
    }
    
    /**
     * Set the connection state: <CODE>true</CODE> if the application is connected to another instance of netboard, false otherwisetrue if the application is connected to another instance of netboard, <CODE>false</CODE> otherwise
     * @param c Value of the new connection state
     * @see netboard.Main#connected
     * @see netboard.Main#isConnected()
     * @see netboard.SocketConnection#disconnect()
     */
    static void setConnected(boolean c) {
        connected = c;
    }

    /**
     * Returns the address of the destination host
     * @return Address of the destination host
     * @see netboard.Main#destination
     */
    public static String getDestination() {
        return destination;
    }    

    /**
     * Set the destination address of the connection
     * @param dest Address to which the destination address of the connection should be set
     * @see netboard.Main#destination
     */
    static void setDestination(String dest) {
        destination = dest;
    }   
    
    static void connect() {
        if (connection != null) {
            disconnect();
        }

        connection = new netboard.SocketConnection(destination, mode);
    }
    
    static void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
        
        connection = null;
    }
    
    /**
     * Main method of this application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        init();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui = new GUI();
                gui.setVisible(true);
            }
        });
    }
    
    // My variables declaration
    /**
     * The name of this application
     * @see netboard.Main#version
     * @see netboard.Main#codename
     * @see netboard.Main#getAppName()
     * @see netboard.Main#getVersionWithCodename()
     * @see netboard.Main#getVersion()
     */
    private static final String appName = "netboard";
    /**
     * Current version of this application
     * @see netboard.Main#appName
     * @see netboard.Main#codename
     * @see netboard.Main#getVersion()
     * @see netboard.Main#getVersionWithCodename()
     * @see netboard.Main#getAppName()
     */
    private static final String version = "0.1";
    /**
     * Codename of the current version of this application
     * @see netboard.Main#appName
     * @see netboard.Main#version
     * @see netboard.Main#getVersionWithCodename()
     * @see netboard.Main#getVersion()
     * @see netboard.Main#getAppName()
     */
    private static final String codename = "Die, die, die my darling...";
    /**
     * Number of the port used for the netwosk connections
     * @see netboard.Main#getPort()
     */
    private static int port = 39832;
    /**
     * Current mode of network operations: 0 - server, 1 - client
     * @see netboard.Main#getMode()
     * @see netboard.Main#setMode(int)
     */
    private static int mode;
    public static final int SERVER_MODE = 0;
    public static final int CLIENT_MODE = 1;    
    /**
     * Object representing current connection
     * @see netboard.Main#getConnection()
     * @see netboard.SocketConnection
     */
    private static netboard.SocketConnection connection;
    /**
     * Object representing GUI of the current application
     * @see netboard.Main#getGUI()
     * @see netboard.GUI
     */
    private static netboard.GUI gui;
    /**
     * The connection state: <CODE>true</CODE> if the application is connected to another instance of netboard, <CODE>false</CODE> otherwise
     * @see netboard.Main#isConnected()
     * @see netboard.Main#setConnected(boolean)
     * @see netboard.SocketConnection#disconnect()
     */
    private static boolean connected = false;
    /**
     * The destination address of the connection
     * @see netboard.Main#getDestination()
     * @see netboard.Main#setDestination(String)
     */
    private static String destination = null;    
    // End of my variables declaration
}