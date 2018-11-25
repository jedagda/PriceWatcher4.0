package resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Resources {

    public Resources(){

    }

    /** Directory for image files: src/image in Eclipse. */
    private final static String ICONS_DIR = "/icons/";
    private final static String IMAGE_DIR = "/icons/";


    /** Return the image stored in the given file. */
    public BufferedImage getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageIcon createImageIcon(String path, String description){
        java.net.URL imgURL = getClass().getResource(ICONS_DIR + path);
        if (imgURL != null) {
            return new ImageIcon (imgURL, description);
        } else {
            System.err.println("Could't find file " + path);
            return null;
        }
    }
}
