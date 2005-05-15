/*
 * $Id: SocketConnection.java,v 1.14 2005/05/15 11:56:26 golish Exp $ 
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
     * Class to be thrown in the constructor when an object cannot be constructed
     * @see netboard.SocketConnection#SocketConnection
     */
    public static class Exception extends java.lang.Exception { }    
    
    /**
     * Class representing the actions performed by the timer (i.e. reading data from the socket and writing to it)
     * @see netboard.SocketConnection#communicationTask
     * @see netboard.SocketConnection#timer
     */
    private class CommunicationTask extends java.util.TimerTask {
        /**
         * Performes communication tasks (i.e. sending and receiving data)
         */
        public void run() {
            if (disconnect == true) {
                try {
                    timer.cancel();
                    Main.setConnected(false);

                    if (receivedPacketEnd == sentPacketEnd == foundEOF == false) {
                        out.writeInt(PACKET_END);
                        out.flush();
                        sentPacketEnd = true;

                        while (in.readInt() != PACKET_END_ACK) { }
                    }

                    socket.close();
                } catch (java.io.IOException e) {
                    if (sentPacketEnd == false) {
                        Main.getGUI().showError("Error while disconnecting: " +  e.getMessage());
                    } 
                    // FIXME: do something more sane...
                }

                Main.getGUI().setStatus("Disconnected (" + 
                        ((sentPacketEnd == true) ? "on your demand" : "on the other side's demand") + ")");
            } else {
                try {
                    netboard.SerializableImage image;
                    int packetType = PACKET_GREET;

                    if (firstPacket == true) {
                        out.writeInt(PACKET_GREET);
                        firstPacket = false;
                    } else {
                        packetType = in.readInt();
                    }

                    if (packetType != PACKET_END && socket.isOutputShutdown() == false && foundEOF == sentPacketEnd == receivedPacketEnd == false) {
                        out.writeInt(PACKET_IMG);
                        image = new netboard.SerializableImage(Main.getGUI().getImage());
                        out.writeObject(image);
                        out.flush();
                        image = null;
                        System.gc();
                    }

                    if (packetType == PACKET_GREET) {
                    } else if (packetType == PACKET_IMG && socket.isInputShutdown() == false && foundEOF == sentPacketEnd == receivedPacketEnd == false) {
                        try {
                            image = (netboard.SerializableImage)in.readUnshared();
                            Main.getGUI().setImage(image.getImage());
                            image = null;
                            System.gc();
                        } catch (java.io.OptionalDataException e) {
                            if (e.eof == true) {
                                foundEOF = true;
                                
                                Main.disconnect();
                                return;
                                // FIXME: do something more sane...
                            } else if (e.length > 0 && in.readInt() == PACKET_END) {
                                receivedPacketEnd = true;
                                out.writeInt(PACKET_END_ACK);
                                
                                Main.disconnect();
                                return;
                                // FIXME: do something more sane...
                            }
                        }
                    } else if (packetType == PACKET_END && foundEOF == sentPacketEnd == receivedPacketEnd == false) {
                        receivedPacketEnd = true;
                        out.writeInt(PACKET_END_ACK);
                        
                        Main.disconnect();
                        return;
                    } else if (packetType == PACKET_END_ACK && sentPacketEnd == true) {
                    } else {
                        throw new java.io.IOException("Unexpected stream content");
                    }
                } catch (java.net.SocketException e) {
                    if (sentPacketEnd == receivedPacketEnd == foundEOF == false) {
                        Main.getGUI().showError("Network error: " + e.getMessage());
                    }
                    
                    Main.disconnect();
                    return;
                    // FIXME: do something more sane...
                } catch (java.io.IOException e) {
                    if (sentPacketEnd == receivedPacketEnd == foundEOF == false) {                    
                        Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                    }
                    
                    Main.disconnect();
                    return;
                    // FIXME: do something more sane...
                } catch (java.lang.ClassNotFoundException e) {
                    // FIXME: do some error handling...
                }
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
     * @param mode Mode of the connection: <CODE>Main.SERVER_MODE</CODE> - server, <CODE>Main.CLIENT_MODE</CODE> - client
     * @throws netboard.SocketConnection.Exception When an object cannot be constructed (i.e. the connection cannot be established)
     */
    public SocketConnection(String destination, int mode) throws netboard.SocketConnection.Exception {
        if (mode == Main.SERVER_MODE) {
            Main.getGUI().setStatus("Working as a server: waiting for incoming connection...");

            try {
                serverSocket = new java.net.ServerSocket(Main.getPort());
                socket = serverSocket.accept();
                socket.setReuseAddress(false);
                socket.setSoTimeout(timeout);

                out = new java.io.ObjectOutputStream(socket.getOutputStream());
                in = new java.io.ObjectInputStream(socket.getInputStream());
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                throw new netboard.SocketConnection.Exception();
                // FIXME: do something more sane...
            } 
        } else if (mode == Main.CLIENT_MODE) {
            Main.getGUI().setStatus("Working as a client: connecting to " + destination + "...");
            
            try {
                socket = new java.net.Socket(destination, Main.getPort());
                socket.setReuseAddress(false);
                socket.setSoTimeout(timeout);                
                
                out = new java.io.ObjectOutputStream(socket.getOutputStream());
                in = new java.io.ObjectInputStream(socket.getInputStream());        
            } catch (java.net.UnknownHostException e) {
                Main.getGUI().showError("Unknown host: " + destination);
                throw new netboard.SocketConnection.Exception();                    
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " +  e.getMessage());
                throw new netboard.SocketConnection.Exception();                
                // FIXME: do something more sane...
            }  
        }   
        
        Main.setConnected(true);        
        Main.getGUI().setStatus("Connected to " + socket.getInetAddress().getHostName());           

        timer = new java.util.Timer("CommunicationThread", false);
        communicationTask = new CommunicationTask();
        
        timer.schedule(communicationTask, 0, communicationFreq);
    }

    /**
     * Provides that when next communication task is performed, the connection will be closed
     * @see netboard.SocketConnection#disconnect
     * @see netboard.SocketConnection.CommunicationTask#run()
     * @see netboard.SocketConnection#SocketConnection
     * @see netboard.Main#connected
     * @see netboard.Main#isConnected()
     * @see netboard.Main#setConnected(boolean)
     * @see netboard.Main#connect()
     * @see netboard.Main#disconnect()
     */
    public void disconnect() {
        if (Main.isConnected() == true) {
            disconnect = true;    
        }
    }
    
    /**
     * Sets everything like the connection was disconnected but does not touch the connection itself.
     * Useful for emergency situations - e.g. when an instance of SocketConnection couldn't be created and no connection exists.
     * @param reason The reason for the emergency disconnection. It is show to the user in the status bar.
     */
    public static void emergencyDisconnect(String reason) {
        Main.setConnected(false);            
        Main.getGUI().setStatus(reason);                  
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
     * @see netboard.SocketConnection#timer
     */
    private CommunicationTask communicationTask;
    /**
     * The frequency at which data should be written to the socket and read from it
     * @see netboard.SocketConnection#timer
     */
    private final int communicationFreq = 500;
    /**
     * Timeout to be set for the network connection
     */
    private final int timeout = 10000;
    /**
     * Indicates if a PACKET_END packet has been received
     */
    private boolean receivedPacketEnd = false;
    /**
     * Indicates if a PACKET_END packet has been sent
     */
    private boolean sentPacketEnd = false; 
    /**
     * Indicates if an EOF has been read from the stream
     */    
    private boolean foundEOF = false;
    /**
     * Indicates if current packet will be the first one send in this connection
     */
    private boolean firstPacket = true;
     /**
     * Indicates if the connection should be closed when next communication task is performed
     */
    private boolean disconnect = false;    
    /**
     * Constant representing greeting packet (send upon establishing the connection)
     */
    private final int PACKET_GREET = 0;
    /**
     * Constant representing a control packet send before sending an image
     */
    private final int PACKET_IMG = 1;
    /**
     * Constant representing a packet send before closing the connection
     */
    private final int PACKET_END = 2;
    /**
     * Constant representing a packet send to acknowledge closing of the connection
     */    
    private final int PACKET_END_ACK = 3;
    // End of my variables declaration
}