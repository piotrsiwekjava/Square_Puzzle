package projectFile.fileChanger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 *
 * @author Piotr Siwek
 */

public class ImageSpliter {

    private String pathImage;
    private int row;
    private int col;
    private int puzzelSize;
    private ArrayList<BufferedImage>imagesPiecesList=new ArrayList<>();

    public ImageSpliter (String pathImage, int row, int col, int puzzelSize) {
        super();
        this.pathImage=pathImage;
        this.row=row;
        this.col=col;
        this.puzzelSize=puzzelSize;
    }

    public void split (){
        try {
            BufferedImage originalImgage = ImageIO.read(new File(pathImage));

            //total width and total height of an image
            int oryginalWidth = originalImgage.getWidth();
            int oryginalHeight = originalImgage.getHeight();

            //width and height of each piece
            int pieceWidth = oryginalWidth / col;
            int pieceHeight = oryginalHeight / row;

            int x = 0;
            int y = 0;

            for (int i = 0; i < row; i++) {
                y = 0;
                for (int j = 0; j < col; j++) {
                    try {

                        BufferedImage SubImgage = originalImgage.getSubimage(y, x, pieceWidth, pieceHeight);
                        imagesPiecesList.add(SubImgage);

                        y += pieceWidth;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                x += pieceHeight;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<BufferedImage> getImagesPiecesList() {
        return imagesPiecesList;
    }
}

