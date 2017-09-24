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

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BPoint getBPoint() {
        return point;
    }

    public void setBPoint(BPoint point) {
        this.point = point;
    }

    public void addBPoint(BPoint point) {
        this.point.addBPoint(point);
    }

    public BVector getBVector() {
        return vector;
    }

    public void setBVector(BVector vector) {
        this.vector = vector;
    }

    public void addBVector(BVector vector) {
        this.vector.addBVector(vector);
    }

//    public void setXY(double inputX, double inputY) {
//        setX(inputX);
//        setY(inputY);
//    }
    public void addXY(double x, double y) {
        this.point = this.point.addXY(x, y);
    }

    public double getDistanceXTo(BPoint point) {
        return getBPoint().getDistanceXTo(point);
    }

    public double getDistanceXTo(Body body) {
        return getDistanceXTo(body.getBPoint());
    }

    public double getDistanceYTo(BPoint point) {
        return getBPoint().getDistanceYTo(point);
    }

    public double getDistanceYTo(Body body) {
        return getDistanceYTo(body.getBPoint());
    }

    public double getDistanceTo(BPoint point) {
        return getBPoint().getDistanceTo(point);
    }

    public double getDistanceTo(Body body) {
        return getDistanceTo(body.getBPoint());
    }

    public double getDirTo(Body body) {
        return Math.atan2(getDistanceYTo(body), getDistanceXTo(body));
    }

    public BPoint[] getTrail() {
        return trail;
    }

    public void setTrail(BPoint[] trail) {
        this.trail = trail;
    }

    int counter = 0;

    public void update(Body[] bodies, double grav, double accuracy) {
        BVector gravTotalBVector = new BVector(0.0, 0.0);
        double scale = 1 / accuracy;
        for (int i = 0; i < bodies.length; i++) {
            Body other = bodies[i];
            if (other != this) {
                BVector gravBVector = new BVector(getDirTo(other), (grav * other.getMass() / Math.pow(getDistanceTo(other), 2)) * scale);
                gravTotalBVector.addBVector(gravBVector);
            }
        }
        addBVector(gravTotalBVector);
        addXY((getBVector().getForceX() * scale), (getBVector().getForceY() * scale));
        if (counter == accuracy) {
            setTrail(calculateTrail());
            counter = 0;
        } else {
            counter++;
        }
    }

    public Body getClosestBody(Body[] bodies) {
        Body closest = new Body(Double.MAX_VALUE, Double.MAX_VALUE, 0.0, "", 0.0, 0.0, 0);
        for (int i = 0; i < bodies.length; i++) {
            Body other = bodies[i];
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
