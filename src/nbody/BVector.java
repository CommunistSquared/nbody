package nbody;

public class BVector {

    //BVector contains 2 values, direction and force/speed, and represents which way and how fast Body moves
    private double dir, force;

    public BVector(double dir, double force) {
        this.dir = dir;
        this.force = force;
    }

    public double getDir() {
        return dir;
    }

    public void setDir(double dir) {
        this.dir = dir;
        setForceXY(getForceX(), getForceY());
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
        setForceXY(getForceX(), getForceY());
    }

    public double getForceX() {
        return Math.cos(dir) * force;
    }

    public void setDirForce(double dir, double force) {
        setDir(dir);
        setForce(force);
    }

    public void setForceX(double force) {
        double newDir = (Math.atan2(getForceY(), force));
        double newForce = (Math.sqrt(Math.pow(force, 2) + Math.pow(getForceY(), 2)));
        dir = newDir;
        this.force = newForce;
    }

    public void addForceX(double force) {
        setForceX(getForceX() + force);
    }

    public double getForceY() {
        return Math.sin(dir) * force;
    }

    public void setForceY(double force) {

        double newDir = (Math.atan2(force, getForceX()));
        double newForce = (Math.sqrt(Math.pow(getForceX(), 2) + Math.pow(force, 2)));
        dir = newDir;
        this.force = newForce;

    }

    public void addForceY(double force) {
        setForceY(getForceY() + force);
    }

    public void setForceXY(double forceX, double forceY) {
        setForceX(forceX);
        setForceY(forceY);
    }

    public void addForceXY(double forceX, double forceY) {
        addForceX(forceX);
        addForceY(forceY);
    }

    public void addBVector(BVector vector) {
        addForceXY(vector.getForceX(), vector.getForceY());
    }

    public void setBVector(BVector vector) {
        setDirForce(vector.getDir(), vector.getForce());
    }
}
