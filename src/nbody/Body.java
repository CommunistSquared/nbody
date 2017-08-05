package nbody;

public class Body {
    private double x;
    private double y;
    private double mass;
    private String name;
    private BVector vector;
    
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
    public double getMass(){
        return mass;
    }
    public void setMass(double inputMass){
        mass = inputMass;
    }
    public String getName(){
        return name;
    }
    public void setName(String inputName){
        name = inputName;
    }
    public BVector getBVector(){
        return vector;
    }
    public void setBVector(BVector inputBVector){
        vector = inputBVector;
    }
    public void addBVector(BVector inputBVector){
        vector.addBVector(inputBVector);
    }
    
    public void setXY(double inputX, double inputY){
        setX(inputX);
        setY(inputY);
    }
    public void addXY(double inputX, double inputY){
        setXY(getX() + inputX, getY() + inputY);
    }
    public double getDistanceXTo(Body inputBody){
        return getX() - inputBody.getX();
    }
    public double getDistanceYTo(Body inputBody){
        return getY() - inputBody.getY();
    }
    public double getDistanceTo(Body inputBody){
        return Math.sqrt(Math.pow(getDistanceXTo(inputBody),2) + Math.pow(getDistanceYTo(inputBody),2));
    }
    public double getDirTo(Body inputBody){
        return Math.atan2(getDistanceYTo(inputBody), getDistanceXTo(inputBody));
    }
    public Body(double inputX, double inputY, double inputMass, String inputName, double inputDir, double inputSpeed){
        setX(inputX);
        setY(inputY);
        setMass(inputMass);
        setName(inputName);
        BVector inputBVector = new BVector(inputDir, inputSpeed);
        setBVector(inputBVector);
    }
}
