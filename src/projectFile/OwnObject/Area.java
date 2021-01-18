package projectFile.OwnObject;

import java.awt.*;

/**
 *
 * @author Piotr Siwek
 */

public abstract class Area {

    private final int upSize;
    private final int sideSize;
    private final Point edgeA;
    private final Point edgeB;
    private final Point edgeC;
    private final Point edgeD;

    public Area (Point a,Point b,Point c,Point d){
        this.edgeA=a;
        this.edgeB=b;
        this.edgeC=c;
        this.edgeD=d;
        this.upSize = (edgeB.x -edgeA.x);
        this.sideSize= (edgeC.y-edgeA.y);
    }

    public int getUpSize() {
        return upSize;
    }

    public int getSideSize() {
        return sideSize;
    }

    public Point getEdgeA() {
        return edgeA;
    }

    public Point getEdgeB() {
        return edgeB;
    }

    public Point getEdgeC() {
        return edgeC;
    }

    public Point getEdgeD() {
        return edgeD;
    }

    @Override
    public String toString() {
        return "Area{" +
                "upSize=" + upSize +
                ", sideSize=" + sideSize +
                ", edgeA=" + edgeA +
                ", edgeB=" + edgeB +
                ", edgeC=" + edgeC +
                ", edgeD=" + edgeD +
                '}';
    }
}
