package projectFile.OwnObject;


import java.awt.image.BufferedImage;

import java.util.Random;

/**
 *
 * @author Piotr Siwek
 */

public class OnePuzzle {

    private BufferedImage image;
    private int rotationAngle;
    private int myX;
    private int myY;
    private int mySize; //size of a one side
    private int [] myReservedPoints = new int [4]; //edges' coordinates - [x, x+pazzelsize,y, y+pazzelsize
    private int number; // puzzle's own number
    private int [] posibleAngle = {0,90,180,270};

    public OnePuzzle (BufferedImage img, int mySize,int number) {
        super();
        this.image=img;
        this.number=number;
        this.mySize=mySize;
        drawStartAngle();

    }
    private void drawStartAngle (){
        this.rotationAngle = posibleAngle[new Random().nextInt(4)];
    }
    /**
     *refillPoints - restores the occupied space taken by a puzzle
     */
    public void refillPoints (){

        int width = myX+mySize;
        int hight = myY+mySize;
        myReservedPoints [0]=myX;
        myReservedPoints [1]=width;
        myReservedPoints [2]=myY;
        myReservedPoints [3]=hight;
    }

    public int [] getMyReservedPoints() {
        return myReservedPoints;
    }

    public void setMyX(int myX) {
        this.myX = myX;
    }

    public void setMyY(int myY) {
        this.myY = myY;
    }

    public int getMyX(){
        return myX;
    }
    public int getMyY(){
        return myY;
    }

    public int getNumber() {
        return number;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void turn (){
        rotationAngle +=90;
        if (rotationAngle ==360) rotationAngle =0;
    }

    public int getRotationAngle() {
        return rotationAngle;
    }

    public int getMySize() {
        return mySize;
    }

    @Override
    public String toString() {
        return "OnePuzzle{" +
                "number=" + number +" angle+"+rotationAngle+
                '}';
    }
}