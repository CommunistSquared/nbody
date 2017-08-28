package nbody;

public class Body {

    //Body represents a physical object in the sim, it has a mass and a name, and its movement is represented by BVector and its location by BPoint. added array of BPoints to represent trail
    private BPoint point;
    private double mass;
    private String name;
    private BVector vector;
    private BPoint[] trail;

    public Body(double inputX, double inputY, double inputMass, String inputName, double inputDir, double inputSpeed, int inputTrailLength) {
        BPoint inputBPoint = new BPoint(inputX, inputY);
        setBPoint(inputBPoint);
        setMass(inputMass);
        setName(inputName);
        BVector inputBVector = new BVector(inputDir, inputSpeed);
        setBVector(inputBVector);
        trail = new BPoint[inputTrailLength];
    }

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

    public double getDistanceXTo(BPoint inputBPoint) {
        return getBPoint().getDistanceXTo(inputBPoint);
    }

    public double getDistanceXTo(Body inputBody) {
        return getDistanceXTo(inputBody.getBPoint());
    }

    public double getDistanceYTo(BPoint inputBPoint) {
        return getBPoint().getDistanceYTo(inputBPoint);
    }

    public double getDistanceYTo(Body inputBody) {
        return getDistanceYTo(inputBody.getBPoint());
    }

    public double getDistanceTo(BPoint inputBPoint) {
        return getBPoint().getDistanceTo(inputBPoint);
    }

    public double getDistanceTo(Body inputBody) {
        return getDistanceTo(inputBody.getBPoint());
    }

    public double getDirTo(Body inputBody) {
        return Math.atan2(getDistanceYTo(inputBody), getDistanceXTo(inputBody));
    }

    public BPoint[] getTrail() {
        return trail;
    }

    public void setTrail(BPoint[] inputTrail) {
        trail = inputTrail;
    }

    public void update(Body[] inputBodies, double inputGravConstant) {
        BVector gravTotalBVector = new BVector(0.0, 0.0);
        for (int i = 0; i < inputBodies.length; i++) {
            Body other = inputBodies[i];
            if (other != this) {
                BVector gravBVector = new BVector(getDirTo(other), inputGravConstant * other.getMass() / Math.pow(getDistanceTo(other), 2));
                gravTotalBVector.addBVector(gravBVector);
            }
        }
        addBVector(gravTotalBVector);
        addXY(getBVector().getForceX(), getBVector().getForceY());
        setTrail(calculateTrail());
        System.out.println(getName() + " is closest to " + getClosestBody(inputBodies).getName());
    }

    public Body getClosestBody(Body[] inputBodies) {
        Body closest = new Body(0.0, 0.0, 0.0, "", 0.0, 0.0, 0);
        for (int i = 0; i < inputBodies.length; i++) {
            Body other = inputBodies[i];
            if (other != this) {
                if (getDistanceTo(other) < getDistanceTo(closest)) {
                    closest = other;
                }
            }
        }
        return closest;
    }

    public BPoint[] calculateTrail() {
        BPoint[] newTrail = new BPoint[getTrail().length];
        for (int a = 1; a < getTrail().length; a++) {
            newTrail[a - 1] = getTrail()[a];
        }
        newTrail[(getTrail().length) - 1] = getBPoint();
        return newTrail;
    }
}
