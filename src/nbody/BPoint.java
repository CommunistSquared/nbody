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

    public BPoint addBPoint(BPoint point) {
        return new BPoint(point.getX() + getX(), point.getY() + getY());
    }

    public BPoint addXY(double x, double y) {
        return new BPoint(getX() + x, getY() + y);
    }

    public double getDistanceXTo(BPoint point) {
        return getX() - point.getX();
    }

    public double getDistanceYTo(BPoint point) {
        return getY() - point.getY();
    }

    public double getDistanceTo(BPoint point) {
        return Math.sqrt(Math.pow(getDistanceXTo(point), 2) + Math.pow(getDistanceYTo(point), 2));
    }

    public Boolean mouseTouching(MouseEvent e, int bounds) {
        if ((Math.abs(e.getX() - getX()) < bounds) && (Math.abs(e.getY() - getY()) < bounds)) {
            return true;
        } else {
            return false;
        }
    }
}
