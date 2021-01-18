package projectFile.fileChanger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Piotr Siwek
 */

public class ImagesCopier {
    private String pathFolder;

    public ImagesCopier (String pathFolder){
        super();
        this.pathFolder=pathFolder;
    }
    public void copyImage (String pathImage){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathImage));
            File outputfile = new File(pathFolder+"\\" +getImageName(pathImage));
            ImageIO.write(image, "jpg", outputfile);
            JOptionPane.showMessageDialog(null,"The image has been copied successfully");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public String getImageName (String pathImage){
        return pathImage.substring(pathImage.lastIndexOf("\\")+1);
    }
}
