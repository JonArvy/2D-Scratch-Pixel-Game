package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
    public static BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File("src/Images/" + filename));
        } catch (IOException ex) {
            System.exit(1);
        }
        return null;
    }
}
