package projectFile.OwnObject;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Piotr Siwek
 */

public class ArrangingField extends Area {

    private int howManyAreasOnSide;
    private ArrayList<Sector> sectorsList = new ArrayList<>();

    public ArrangingField(Point a, Point b, Point c, Point d, int puzzleSize) {
        super(a, b, c, d);
        divideThePlayingFieldToSector(puzzleSize);
    }

    public void divideThePlayingFieldToSector(int puzzlesSize) {
        howManyAreasOnSide = (int) Math.sqrt(getSideSize() * getSideSize() / (puzzlesSize * puzzlesSize));


        int y=this.getEdgeA().y;
        for (int i = 0; i< howManyAreasOnSide; i++){
            int x=this.getEdgeA().x;
            for (int k = 0; k< howManyAreasOnSide; k++) {
                sectorsList.add(new Sector(new Point(x,y),new Point(x+puzzlesSize,y),new Point(x,y+puzzlesSize),new Point(x+puzzlesSize,y+puzzlesSize)));
                x+=puzzlesSize;
            }
            y+=puzzlesSize;
        }

    }

    public int getHowManyAreasOnSide() {
        return howManyAreasOnSide;
    }

    public ArrayList<Sector> getSectorsList() {
        return sectorsList;
    }
}