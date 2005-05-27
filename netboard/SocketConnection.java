/*
 * $Id: SocketConnection.java,v 1.16 2005/05/27 13:19:33 golish Exp $ 
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
     * Class representing the writing actions performed by the timer
     * @see netboard.SocketConnection#writingTimer
     */
    private class WritingTask extends java.util.TimerTask {
        /**
         * Performes the writing tasks
         */
        public void run() {
            if (disconnect == true) {
                try {
                    writingTimer.cancel();
                    Main.setConnected(false);

                    if (receivedPacketEnd == sentPacketEnd == foundEOF == false) {
                        out.writeInt(PACKET_END);
                        out.flush();
                        sentPacketEnd = true;
                        
                        while (in.readInt() != PACKET_END_ACK) { }                        
                    }

                    writingSocket.close();
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

                    if (firstPacket == true) {
                        out.writeInt(PACKET_GREET);
                        firstPacket = false;
                    }

                    if (writingSocket.isOutputShutdown() == false && foundEOF == sentPacketEnd == receivedPacketEnd == false) {                        
                        out.writeInt(PACKET_IMG);
                        image = new netboard.SerializableImage(Main.getGUI().getImage());
                        out.writeObject(image);
                        out.flush();
                        image = null;
                        System.gc();
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
                }
            }
        }
    }

    /**
     * Class representing the reading actions performed by the timer
     * @see netboard.SocketConnection#readingTimer
     */
    private class ReadingTask extends java.util.TimerTask {
        /**
         * Performes communication tasks (i.e. sending and receiving data)
         */
        public void run() {
            if (disconnect == true) {
                try {
                    readingTimer.cancel();
                    Main.setConnected(false);

                    readingSocket.close();
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

                    if (firstPacket == false) {                    
                        packetType = in.readInt();
                    }

                    if (packetType == PACKET_GREET) {
                    } else if (packetType == PACKET_IMG && readingSocket.isInputShutdown() == false && foundEOF == sentPacketEnd == receivedPacketEnd == false) {
                        image = (netboard.SerializableImage)in.readUnshared();
                        Main.getGUI().setImage(image.getImage());
                        image = null;
                        System.gc();
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
                writingServerSocket = new java.net.ServerSocket(Main.getWritingPort());
                readingServerSocket = new java.net.ServerSocket(Main.getReadingPort());
                writingSocket = writingServerSocket.accept();
                writingSocket.setReuseAddress(false);
                writingSocket.setSoTimeout(timeout);
                readingSocket = readingServerSocket.accept();
                readingSocket.setReuseAddress(false);
                readingSocket.setSoTimeout(timeout);

                out = new java.io.ObjectOutputStream(writingSocket.getOutputStream());
                in = new java.io.ObjectInputStream(readingSocket.getInputStream());
            } catch (java.io.IOException e) {
                Main.getGUI().showError("Error communicating with peer: " + e.getMessage());
                throw new netboard.SocketConnection.Exception();
                // FIXME: do something more sane...
            } 
        } else if (mode == Main.CLIENT_MODE) {
            Main.getGUI().setStatus("Working as a client: connecting to " + destination + "...");
            
            try {
                writingSocket = new java.net.Socket(destination, Main.getReadingPort());
                readingSocket = new java.net.Socket(destination, Main.getWritingPort());
                writingSocket.setReuseAddress(false);
                writingSocket.setSoTimeout(timeout);                
                readingSocket.setReuseAddress(false);
                readingSocket.setSoTimeout(timeout);                
                
                out = new java.io.ObjectOutputStream(writingSocket.getOutputStream());
                in = new java.io.ObjectInputStream(readingSocket.getInputStream());        
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
        Main.getGUI().setStatus("Connected to " + writingSocket.getInetAddress().getHostName());           

        writingTimer = new java.util.Timer("WritingThread", true);
        readingTimer = new java.util.Timer("ReadingThread", true);        
        
        writingTimer.schedule(new WritingTask(), 0, communicationFreq);
        readingTimer.schedule(new ReadingTask(), 0, communicationFreq);        
    }

    /**
     * Provides that when next communication task is performed, the connection will be closed
     * @see netboard.SocketConnection#disconnect
     * @see netboard.SocketConnection.WritingTask#run()
     * @see netboard.SocketConnection.ReadingTask#run()
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
     * Object representing the writing socket
     */
    private java.net.Socket writingSocket = null;
    /**
     * Object representing the reading socket
     */
    private java.net.Socket readingSocket = null;    
    /**
     * Object representing the socket listening on the writing port (for the server mode)
     */
    private java.net.ServerSocket writingServerSocket = null;
    /**
     * Object representing the socket listening on the reading port (for the server mode)
     */
    private java.net.ServerSocket readingServerSocket = null;    
    /**
     * Object representing the output stream
     */
    private java.io.ObjectOutputStream out;
    /**
     * Object representing the input stream
     */
    private java.io.ObjectInputStream in;    
    /**
     * Timer which handles writing tasks
     * @see netboard.SocketConnection.WritingTask
     * @see netboard.SocketConnection#communicationFreq
     */
    private java.util.Timer writingTimer;
    /**
     * Timer which handles reading tasks
     * @see netboard.SocketConnection.ReadingTask
     * @see netboard.SocketConnection#communicationFreq
     */
    private java.util.Timer readingTimer;    
    /**
     * The frequency at which data should be written to the socket and read from it
     * @see netboard.SocketConnection#writingTimer
     * @see netboard.SocketConnection#readingTimer
     */
    private final int communicationFreq = 500;
    /**
     * Timeout to be set for the network connection
     */
    private final int timeout = 50000;
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