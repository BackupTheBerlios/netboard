/*
 * $Id: SocketConnection.java,v 1.6 2005/05/02 11:29:47 golish Exp $ 
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
 * Class handling the connection with another instance of netboard (via TCP/IP sockets)
 * @author <a href="mailto:golish@niente.eu.org">Marcin 'golish' Goliszewski</a>
 */
public class SocketConnection {
    /**
     * Class representing the actions performed by the timer (i.e. reading data from the socket and writing to it)
     * @see netboard.SocketConnection#communicationTask
     * @see netboard.SocketConnection#timer
     */
    private class CommunicationTask extends java.util.TimerTask {
        public void run() {
            try {
                netboard.SerializableImage image;                

                out.writeInt(PACKET_IMG);                
                image = new netboard.SerializableImage(Main.getGUI().getImage());
                out.writeObject(image); 
                image = null;
                
                int packetType = in.readInt();

                if (packetType == PACKET_IMG) {                    
                    image = (netboard.SerializableImage)in.readUnshared();                
                    Main.getGUI().setImage(image.getImage()); 
                    image = null;                
                } else if (packetType == PACKET_END) {
                    disconnect();
                } else {
                    throw new java.io.IOException("unexpected stream content");
                }
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                disconnect();
                // FIXME: do something more sane...
            } catch (java.lang.ClassNotFoundException e) {
                // FIXME: do some error handling...
            }            
        }
    }
    
    /**
     * Creates a new instance of SocketConnection and starts the connection thread
     * @see netboard.SocketConnection#disconnect()
     * @see netboard.Main#connected
     * @see netboard.Main#isConnected()
     * @see netboard.Main#setConnected(boolean)
     * @see netboard.Main#connect()
     * @see netboard.Main#disconnect()
     * @param destination Address of the destination host
     * @param mode Mode of the connection: 0 - server, 1 - client
     */
    public SocketConnection(String destination, int mode) {        
        if (mode == 0) {
            Main.getGUI().setStatus("Working as a server: waiting for incoming connection...");
            
            try {
                serverSocket = new java.net.ServerSocket(Main.getPort());
                socket = serverSocket.accept();
                
                out = new java.io.ObjectOutputStream(socket.getOutputStream());
                in = new java.io.ObjectInputStream(socket.getInputStream());
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                // FIXME: do something more sane...
            }
        } else if (mode == 1) {
            Main.getGUI().setStatus("Working as a client: connecting to " + destination + "...");
            
            try {
                try {
                    socket = new java.net.Socket(destination, Main.getPort());
                } catch (java.net.UnknownHostException e) {
                    Main.getGUI().showError("Unknown host: " + destination);
                }
                
                out = new java.io.ObjectOutputStream(socket.getOutputStream());
                in = new java.io.ObjectInputStream(socket.getInputStream());        
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " +  e.getMessage());
                // FIXME: do something more sane...
            }  
        }   
        
        Main.setConnected(true);        
        Main.getGUI().setStatus("Connected to " + socket.getInetAddress().getHostName());           
        
        timer = new java.util.Timer();
        communicationTask = new CommunicationTask();
        
        timer.schedule(communicationTask, communicationFreq, communicationFreq);
    }
    

    /**
     * Disconnects the network connection and makes sure that the application is ready for another connection
     * @see netboard.SocketConnection#SocketConnection()
     * @see netboard.Main#connected
     * @see netboard.Main#isConnected()
     * @see netboard.Main#setConnected(boolean)
     * @see netboard.Main#connect()
     * @see netboard.Main#disconnect()
     */
    public void disconnect() {
        if (Main.isConnected() == true) {
            try {
                timer.cancel();
                
                out.writeInt(PACKET_END);
                
                in.close();
                in = null;
                out.close();
                out = null;
                
                socket.close();
                socket = null;
                if (serverSocket != null) {
                    serverSocket.close();
                    serverSocket = null;
                }
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error while disconnecting: " +  e.getMessage());                
            }
            
            Main.setConnected(false);
            Main.getGUI().setStatus("Disconnected");
        }
    }
        
    // My variables declaration
    /**
     * Object representing the connection socket
     */
    private java.net.Socket socket = null;
    /**
     * Object representing the listening socket for the server mode
     */
    private java.net.ServerSocket serverSocket = null;
    /**
     * Object representing the output stream
     */
    private java.io.ObjectOutputStream out;
    /**
     * Object representing the input stream
     */
    private java.io.ObjectInputStream in;    
    /**
     * Timer which handles communication tasks
     * @see netboard.SocketConnection.CommunicationTask
     * @see netboard.SocketConnection#communicationTask
     * @see netboard.SocketConnection#communicationFreq
     */
    private java.util.Timer timer;
    /**
     * Object representing the tasks which are performed by the timer
     * @see netboard.SocketConnection.CommunicationTask
     * @see netboard.SocketConnecion#timer
     */
    private CommunicationTask communicationTask;
    /**
     * The frequency at which data should be written to the socket and read from it
     * @see netboard.SocketConnection#timer
     */
    private final int communicationFreq = 750;
    private final int PACKET_IMG = 0;
    private final int PACKET_END = 1;
    // End of my variables declaration
}