/*
 * $Id: SocketConnection.java,v 1.1 2005/04/30 10:30:50 golish Exp $ 
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
public class SocketConnection extends Thread {
    
    /**
     * Creates a new instance of SocketConnection and starts the connection thread
     * @see netboard.SocketConnection#run()
     * @see netboard.SocketConnection#disconnect()
     */
    public SocketConnection() {
        setDaemon(true);
        setPriority(Thread.NORM_PRIORITY/4);
        Main.setConnected(true);
        start();
    }
    
    /**
     * Set the destination address of the connection
     * @param dest Address to which the destination address of the connection should be set
     * @see netboard.SocketConnection#destination
     */
    public void setDestination(String dest) {
        destination = dest;
    }
    
    /**
     * Disconnects the network connection and makes sure that the application is ready for another connection
     * @see netboard.SocketConnection#SocketConnection()
     * @see netboard.SocketConnection#run()
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
     * @see netboard.SocketConnection#SocketConnection()
     * @see netboard.SocketConnection#disconnect()
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
                
                Main.getGUI().setStatus("Connected to " + socket.getInetAddress().getHostName());
                
                final java.io.ObjectOutput out = new java.io.ObjectOutputStream(socket.getOutputStream());
                final java.io.ObjectInput in = new java.io.ObjectInputStream(socket.getInputStream());
                
                error = false;

                java.awt.event.ActionListener timerActionListener = new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            netboard.SerializableImage image;
                            
                            image = new netboard.SerializableImage(Main.getGUI().getImage());
                            out.writeObject(image); // FIXME: not, fuckin', working ://
                            image = null;
//                            yield();                            
                            
                            image = (netboard.SerializableImage)in.readObject();
                            Main.getGUI().setImage(image.getImage()); // FIXME: not, fuckin', working ://                            
                            image = null;                            
//                            yield();                            
                        } catch (java.io.IOException e) {
                            Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                            error = true;
                            // FIXME: do something more sane...
                        } catch (java.lang.ClassNotFoundException e) {
                            // FIXME: do some error handling...
                        }
                    }
                };
                new javax.swing.Timer(actionFreq, timerActionListener).start();                
                
                try {
                    while (error == false) { 
//                        yield();                        
                        Thread.sleep(actionFreq/2);
                    }
                } catch (InterruptedException e) { }
                
                in.close();
                out.close();
                disconnect();
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                // FIXME: do something more sane...
            }
        } else if (Main.getMode() == 1) {
            Main.getGUI().setStatus("Working as a client: connecting to " + destination + "...");
            
            try {
                java.net.Socket socket = null;
                try {
                    socket = new java.net.Socket(destination, Main.getPort());
                } catch (java.net.UnknownHostException e) {
                    Main.getGUI().showError("Unknown host: " + destination);
                }
                
                Main.getGUI().setStatus("Connected to " + destination);
                
                final java.io.ObjectOutput out = new java.io.ObjectOutputStream(socket.getOutputStream());
                final java.io.ObjectInput in = new java.io.ObjectInputStream(socket.getInputStream());
                
                error = false;
                    
                java.awt.event.ActionListener timerActionListener = new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            netboard.SerializableImage image;
                            
                            image = new netboard.SerializableImage(Main.getGUI().getImage());
                            out.writeObject(image); // FIXME: not, fuckin', working ://
                            image = null;
//                            yield();
                            
                            image = (netboard.SerializableImage)in.readObject();
                            Main.getGUI().setImage(image.getImage()); // FIXME: not, fuckin', working ://                            
                            image = null;
//                            yield();
                        } catch (java.io.IOException e) {
                            Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                            error = true;
                            // FIXME: do something more sane...
                        } catch (java.lang.ClassNotFoundException e) {
                            // FIXME: do some error handling...
                        }
                    }
                };
                new javax.swing.Timer(actionFreq, timerActionListener).start();
                
                try {
                    while (error == false) { 
//                        yield();                        
                        Thread.sleep(actionFreq/2);
                    }
                } catch (InterruptedException e) { }
                    
                in.close();
                out.close();
                disconnect();
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " +  e.getMessage());
                // FIXME: do something more sane...
            }
        }
    }
    
    // My variables declaration
    /**
     * The destination address of the connection
     * @see netboard.SocketConnection#setDestination(String)
     */
    private String destination = null;
    /**
     * The connection's error status - if an error occured this is <CODE>true</CODE> and <CODE>false</CODE> otherwise
     */
    private boolean error = false; // FIXME: This one _really_ doesn't belong here!
    /**
     * The frequency at which data should be written to the socket and read from it
     */
    private final int actionFreq = 750;
    // End of my variables declaration
}