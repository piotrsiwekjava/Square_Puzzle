package projectFile.frameAndGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Piotr Siwek
 */

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private int w=400;
    private int h=400;
    private int x=0;
    private int y=0;

    public ImagePanel(){
        super();
        redraw("C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\Puzzle_lv2\\src\\projectFile\\images\\puzzelmain.jpg",x,-50,w,h);
    }

    public void redraw (String pathImage,int x, int y, int w, int h){
        try {
            image=ImageIO.read(new File(pathImage));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.repaint();
    }
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,x,y,w,h,this);

    }
}
