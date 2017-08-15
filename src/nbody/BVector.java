package nbody;

public class BVector {

    //BVector contains 2 values, direction and force/speed, and represents which way and how fast Body moves
    private double dir, force;

    public double getDir() {
        return dir;
    }

    public void setDir(double inputDir) {
        dir = inputDir;
        setForceXY(getForceX(), getForceY());
    }

    public double getForce() {
        return force;
    }

    public void setForce(double inputForce) {
        force = inputForce;
        setForceXY(getForceX(), getForceY());
    }

    public double getForceX() {
        return Math.cos(dir) * force;
    }

    public void setDirForce(double inputDir, double inputForce) {
        setDir(inputDir);
        setForce(inputForce);
    }

    public void setForceX(double inputForceX) {
        double newDir = (Math.atan2(getForceY(), inputForceX));
        double newForce = (Math.sqrt(Math.pow(inputForceX, 2) + Math.pow(getForceY(), 2)));
        dir = newDir;
        force = newForce;
    }

    public void addForceX(double inputForceX) {
        setForceX(getForceX() + inputForceX);
    }

    public double getForceY() {
        return Math.sin(dir) * force;
    }

    public void setForceY(double inputForceY) {

        double newDir = (Math.atan2(inputForceY, getForceX()));
        double newForce = (Math.sqrt(Math.pow(getForceX(), 2) + Math.pow(inputForceY, 2)));
        dir = newDir;
        force = newForce;

    }

    public void addForceY(double inputForceY) {
        setForceY(getForceY() + inputForceY);
    }

    public void setForceXY(double inputForceX, double inputForceY) {
        setForceX(inputForceX);
        setForceY(inputForceY);
    }

    public void addForceXY(double inputForceX, double inputForceY) {
        addForceX(inputForceX);
        addForceY(inputForceY);
    }

    public void addBVector(BVector inputBVector) {
        addForceXY(inputBVector.getForceX(), inputBVector.getForceY());
    }

    public void setBVector(BVector inputBVector) {
        setDirForce(inputBVector.getDir(), inputBVector.getForce());
    }

    public BVector(double inputDir, double inputForce) {
        dir = inputDir;
        force = inputForce;
    }

    public BVector(BVector inputBVector1, BVector inputBVector2) {
        setForceX(inputBVector1.getForceX() + inputBVector2.getForceX());
        setForceY(inputBVector1.getForceY() + inputBVector2.getForceY());
    }
}
