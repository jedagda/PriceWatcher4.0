package resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Resources {

    static Class source = Resources.class;

    public Resources(){

    }

    /** Directory for image files: src/image in Eclipse. */
    private final static String ICONS_DIR = "/";
    private final static String IMAGE_DIR = "/";


    /** Return the image stored in the given file. */
    public BufferedImage getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(ICONS_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageIcon createImageIcon(String path, String description){
        java.net.URL imgURL = getClass().getResource(IMAGE_DIR + path+".png");
        if (imgURL != null) {
            return new ImageIcon (imgURL, description);
        } else {
            System.err.println("Could't find file " + path);
            return null;
        }
    }


}
