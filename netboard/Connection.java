/*
 * Connection.java
 *
 * Created on 21 march 2005, 13:04
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
 * Class handling the connection with another instance of netboard
 * @author <a href="mailto:golish@niente.eu.org">Marcin 'golish' Goliszewski</a>
 */
public class Connection extends Thread {
    
    /**
     * Creates a new instance of Connection and starts the connection thread
     * @see netboard.Connection#run()
     * @see netboard.Connection#disconnect()
     */
    public Connection() {
        setDaemon(true);
        start();
    }
    
    /**
     * Set the destination address of the connection
     * @param dest Address to which the destination address of the connection should be set
     * @see netboard.Connection#destination
     */
    public void setDestination(String dest) {
        destination = dest;
    }
    
    /**
     * Disconnects the network connection and makes sure that the application is ready for another connection
     * @see netboard.Connection#Connection()
     * @see netboard.Connection#run()
     * @see netboard.Main#connected
     * @see netboard.Main#isConnected()
     * @see netboard.Main#setConnected(boolean)
     */
    public void disconnect() {
        if (Main.isConnected() == true) {
            Main.setConnected(false);
            Main.getGUI().setStatus("Disconnected");
            stop();
        }
    }
    
    /**
     * Runs the connection thread, i.e. connects to the peer and handles sending/receiving data
     * @see netboard.Connection#Connection()
     * @see netboard.Connection#disconnect()
     * @see netboard.Main#getMode()
     * @see netboard.Main#setMode(int)
     */
    
    public void run() {
        synchronized(this) {
            try {
                this.wait();
            } catch (InterruptedException e) { }
        }
        
        if (Main.getMode() == 0) {
            Main.getGUI().setStatus("Working as a server: waiting for incoming connection...");
            
            try {
                java.net.ServerSocket serverSocket = null;
                java.net.Socket socket = null;
                serverSocket = new java.net.ServerSocket(Main.getPort());
                socket = serverSocket.accept();
                
                Main.setConnected(true);
                Main.getGUI().setStatus("Connected to " + socket.getInetAddress().getHostName());
                
                final java.io.ObjectOutput out = new java.io.ObjectOutputStream(socket.getOutputStream());
                final java.io.ObjectInput in = new java.io.ObjectInputStream(socket.getInputStream());
                
                error = false;

                java.awt.event.ActionListener timerActionListener = new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            out.writeObject(new netboard.SerializableImage(Main.getGUI().getImage())); // FIXME: not, fuckin', working ://
                        } catch (java.io.IOException e) {
                            Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                            error = true;
                            // FIXME: do something more sane...
                        }
                        connectionWrote = true;
                    }
                };
                new javax.swing.Timer(sendingFreq, timerActionListener).start();                
                
                while (error == false) {
                    connectionWrote = false;
                    
                    while (connectionWrote == false) {
                        try {
                            Main.getGUI().setImage(((netboard.SerializableImage)in.readObject()).getImage()); // FIXME: not, fuckin', working ://
                        } catch (ClassNotFoundException e) {
                            error = true;
                            break;
                            // FIXME: do some error handling...
                        }
                    }
                }
                
                in.close();
                out.close();
                disconnect();
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                // FIXME: do something more sane...
            }
            //FIXME: make the server work ://
        } else if (Main.getMode() == 1) {
            Main.getGUI().setStatus("Working as a client: connecting to " + destination + "...");
            
            try {
                java.net.Socket socket = null;
                try {
                    socket = new java.net.Socket(destination, Main.getPort());
                } catch (java.net.UnknownHostException e) {
                    Main.getGUI().showError("Unknown host: " + destination);
                }
                
                Main.setConnected(true);
                Main.getGUI().setStatus("Connected to " + destination);
                
                final java.io.ObjectOutput out = new java.io.ObjectOutputStream(socket.getOutputStream());
                final java.io.ObjectInput in = new java.io.ObjectInputStream(socket.getInputStream());
                
                error = false;
                    
                java.awt.event.ActionListener timerActionListener = new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            out.writeObject(new netboard.SerializableImage(Main.getGUI().getImage())); // FIXME: not, fuckin', working ://
                        } catch (java.io.IOException e) {
                            Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                            error = true;
                            // FIXME: do something more sane...
                        }
                        connectionWrote = true;
                    }
                };
                new javax.swing.Timer(sendingFreq, timerActionListener).start();
                    
                while (error == false) {
                    connectionWrote = false;
                    
                    while (connectionWrote == false) {
                        try {
                            Main.getGUI().setImage(((netboard.SerializableImage)in.readObject()).getImage()); // FIXME: not, fuckin', working ://
                        } catch (ClassNotFoundException e) {
                            error = true;
                            break;
                            // FIXME: do some error handling...
                        }
                    }
                }
                
                in.close();
                out.close();
                disconnect();
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " +  e.getMessage());
                // FIXME: do something more sane...
            }
            // FIXME: make the client work ://
        }
    }
    
    // My variables declaration
    /**
     * The destination address of the connection
     * @see netboard.Connection#setDestination(String)
     */
    private String destination = null;
    /**
     * This connection's write status - if it has written data to the socket since last check this is <CODE>true</CODE> and <CODE>false</CODE> otherwise
     */
    private boolean connectionWrote = false; // FIXME: This one _really_ doesn't belong here!
    /**
     * The connection's error status - if an error occured this is <CODE>true</CODE> and <CODE>false</CODE> otherwise
     */
    private boolean error = false; // FIXME: This one _really_ doesn't belong here!
    /**
     * The frequency at which data should be written to the socket
     */
    private final int sendingFreq = 750;
    // End of my variables declaration
}