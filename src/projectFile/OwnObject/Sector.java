package projectFile.OwnObject;

import java.awt.*;

/**
 *
 * @author Piotr Siwek
 */

public class Sector extends Area{
    public boolean hasPuzzle;
    private int numberPuzzle;

    public Sector(Point a, Point b, Point c, Point d) {
        super(a, b, c, d);
    }

    public void setNumberPuzzle (int numberPuzzle){
        this.numberPuzzle=numberPuzzle;
    }
    public boolean setHasPuzzle(){
        if (hasPuzzle) return hasPuzzle=false;
        return hasPuzzle=true;
    }

    public int getNumberPuzzle() {
        return numberPuzzle;
    }
}
