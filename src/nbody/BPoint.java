package nbody;

import java.awt.event.MouseEvent;

public class BPoint {

    //BPoint contains 2 values, x and y, represents the coordinate position of Body and trails
    private double x, y;

    public BPoint(double inputX, double inputY) {
        x = inputX;
        y = inputY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public BPoint addBPoint(BPoint inputBPoint) {
        return new BPoint(inputBPoint.getX() + getX(), inputBPoint.getY() + getY());
    }

    public BPoint addXY(double inputX, double inputY) {
        return new BPoint(getX() + inputX, getY() + inputY);
    }

    public String toString() {
        return "BPoint (x=" + x + ", y=" + y + ")";
    }

    public double getDistanceXTo(BPoint inputBPoint) {
        return getX() - inputBPoint.getX();
    }

    public double getDistanceYTo(BPoint inputBPoint) {
        return getY() - inputBPoint.getY();
    }

    public double getDistanceTo(BPoint inputBPoint) {
        return Math.sqrt(Math.pow(getDistanceXTo(inputBPoint), 2) + Math.pow(getDistanceYTo(inputBPoint), 2));
    }

    public Boolean mouseTouching(MouseEvent e, int bounds) {
        if ((Math.abs(e.getX() - getX()) < bounds) && (Math.abs(e.getY() - getY()) < bounds)) {
            return true;
        } else {
            return false;
        }
    }
}
