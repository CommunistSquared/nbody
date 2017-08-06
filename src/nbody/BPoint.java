package nbody;

public class BPoint {
    
    //BPoint contains 2 values, x and y, represents the coordinate position of Body
    
    private double x;
    private double y;
    
    public double getX(){
        return x;
    }
    public void setX(double inputX){
        x = inputX;
    }
    public double getY(){
        return y;
    }
    public void setY(double inputY){
        y = inputY;
    }
    public void addX(double inputX){
        setX(getX()+inputX);
    }
    public void addY(double inputY){
        setY(getY()+inputY);
    }
    public void addXY(double inputX, double inputY){
        addX(inputX);
        addY(inputY);
    }
    public void setXY(double inputX, double inputY){
        setX(inputX);
        setY(inputY);
    }
    public void addBPoint(BPoint inputBPoint){
        addXY(inputBPoint.getX(), inputBPoint.getY());
    }
    public BPoint(double inputX, double inputY){
        setX(inputX);
        setY(inputY);
    }
}
