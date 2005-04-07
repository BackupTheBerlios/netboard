/*
 * SerializableImage.java
 *
 * Created on 26 march 2005, 18:16
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
 * A wrapper around <CODE>java.awt.image.BufferedImage</CODE> which implements <CODE>java.io.Serializable</CODE>
 * @author <a href="mailto:golish@niente.eu.org">Marcin 'golish' Goliszewski</a>
 * @see java.awt.image.BufferedImage
 * @see java.io.Serializable
 */
public class SerializableImage implements java.io.Serializable {
    
    /**
     * Creates a new instance of SerializableImage
     * @param img The image to serialize
     */
    public SerializableImage(java.awt.image.BufferedImage img) {
        if (image == null)
            image = new java.awt.image.BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        java.awt.Graphics2D graphics = image.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();
    }
    
    /**
     * Returns the serialized image
     * @return The serialized image
     * @see netboard.SerializableImage#image
     */
    public java.awt.image.BufferedImage getImage() {
        return image;
    }
    
    /**
     * Writes the <CODE>netboard.SerializedImage</CODE> object to the output stream
     * @param out Stream to write to
     * @throws java.io.IOException If a read/write error occured
     */
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
        
        if(this.image != null) {
            javax.imageio.ImageWriter writer = (javax.imageio.ImageWriter)javax.imageio.ImageIO.getImageWritersByFormatName("png").next();
            javax.imageio.IIOImage iioImage = new javax.imageio.IIOImage(image, null, null);
            java.io.ByteArrayOutputStream outArray = new java.io.ByteArrayOutputStream();
            writer.setOutput(javax.imageio.ImageIO.createImageOutputStream(outArray));
            writer.write(null, iioImage, writer.getDefaultWriteParam());
            byte[] imageData = outArray.toByteArray();
            out.writeInt(imageData.length);
            out.write(imageData);
            outArray.close();
        } else {
            out.writeInt(0);
        }
    }
    
    /**
     * Reads the <CODE>netboard.SerializedImage</CODE> object from the input stream
     * @param in Stream to read from
     * @throws java.io.IOException If a read/write error occured
     * @throws java.lang.ClassNotFoundException If no definition of the received class has been found
     */
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        
        int imageSize = in.readInt();
        if(imageSize > 0) {
            byte[] imageData = new byte[imageSize];
            in.readFully(imageData);
            java.io.ByteArrayInputStream inArray = new java.io.ByteArrayInputStream(imageData);
            image = javax.imageio.ImageIO.read(inArray);
            inArray.close();
        }
    }
    
    // My variables declaration
    /**
     * The serialized image
     * @see netboard.SerializableImage#getImage()
     */
    private transient java.awt.image.BufferedImage image = null;
    // End of my variables declaration
}