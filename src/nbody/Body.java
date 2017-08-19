package nbody;

public class Body {

    //Body represents a physical object in the sim, it has a mass and a name, and its movement is represented by BVector and its location by BPoint. added array of BPoints to represent trail
    private BPoint point;
    private double mass;
    private String name;
    private BVector vector;
    private BPoint[] trail;

    public double getX() {
        return point.getX();
    }

//    public void setX(double inputX) {
//        point.setX(inputX);
//    }

    public double getY() {
        return point.getY();
    }

//    public void setY(double inputY) {
//        point.setY(inputY);
//    }

    public double getMass() {
        return mass;
    }

    public void setMass(double inputMass) {
        mass = inputMass;
    }

    public String getName() {
        return name;
    }

    public void setName(String inputName) {
        name = inputName;
    }

    public BPoint getBPoint() {
        return point;
    }
    
    public void setBPoint(BPoint inputBPoint) {
        point = inputBPoint;
    }

    public void addBPoint(BPoint inputBPoint) {
        point.addBPoint(inputBPoint);
    }

    public BVector getBVector() {
        return vector;
    }

    public void setBVector(BVector inputBVector) {
        vector = inputBVector;
    }

    public void addBVector(BVector inputBVector) {
        vector.addBVector(inputBVector);
    }

//    public void setXY(double inputX, double inputY) {
//        setX(inputX);
//        setY(inputY);
//    }

    public void addXY(double inputX, double inputY) {
        point = point.addXY(inputX, inputY);
    }

    public double getDistanceXTo(Body inputBody) {
        return getX() - inputBody.getX();
    }

    public double getDistanceYTo(Body inputBody) {
        return getY() - inputBody.getY();
    }

    public double getDistanceTo(Body inputBody) {
        return Math.sqrt(Math.pow(getDistanceXTo(inputBody), 2) + Math.pow(getDistanceYTo(inputBody), 2));
    }

    public double getDirTo(Body inputBody) {
        return Math.atan2(getDistanceYTo(inputBody), getDistanceXTo(inputBody));
    }

    public BPoint[] getTrail() {
        return trail;
    }
    
    public void setTrail(BPoint[] inputTrail){
        trail = inputTrail;
    }

    //
    public boolean checkCollision(Body inputBody) {
        if ((this.getDistanceTo(inputBody) - (this.getMass()/2)) < inputBody.getMass()) {
            System.out.println("kaboom");
            return true;
        }
        return false;
    }

    public Body(double inputX, double inputY, double inputMass, String inputName, double inputDir, double inputSpeed, int inputTrailLength) {
        BPoint inputBPoint = new BPoint(inputX, inputY);
        setBPoint(inputBPoint);
        setMass(inputMass);
        setName(inputName);
        BVector inputBVector = new BVector(inputDir, inputSpeed);
        setBVector(inputBVector);
        trail = new BPoint[inputTrailLength];
    }
}
