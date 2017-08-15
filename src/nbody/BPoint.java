package nbody;

public class BPoint {

    //BPoint contains 2 values, x and y, represents the coordinate position of Body and trails
    private double x, y;

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

    public BPoint(double inputX, double inputY) {
        x = inputX;
        y = inputY;
    }

    public String toString() {
        return "BPoint (x=" + x + ", y=" + y + ")";
    }
}
