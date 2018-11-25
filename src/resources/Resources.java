package resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Resources {

    private Resources(){

    }

    /** Directory for image files: src/image in Eclipse. */
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
}
