package nbody;

public class BVector {

    private double dir;
    private double force;

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

    public void setForceX(double inputForceX) {
        if (getForceY() == 0 && inputForceX >= 0) {
            dir = 0;
            force = inputForceX;
        } else if (getForceY() == 0 && inputForceX < 0) {
            dir = Math.PI;
            force = inputForceX * -1;
        } else {
            double newDir = (Math.atan(inputForceX / getForceY()));
            double newForce = (Math.sqrt(Math.pow(inputForceX, 2) + Math.pow(getForceY(), 2)));
//        System.out.println("New VectorXY: " + inputForceX + " " + getForceY());
            if (newDir < 0 || (inputForceX < 0 && getForceY() < 0)) {
                newDir = Math.PI + newDir;
            }
            dir = newDir;
            force = newForce;
        }
    }

    public void addForceX(double inputForceX) {
        setForceX(getForceX() + inputForceX);
    }

    public double getForceY() {
        return Math.sin(dir) * force;
    }

    public void setForceY(double inputForceY) {
        if (getForceX() == 0 && inputForceY >= 0) {
            dir = Math.PI / 2;
            force = inputForceY;
        } else if (getForceX() == 0 && inputForceY < 0) {
            dir = 3 * (Math.PI / 2);
            force = inputForceY * -1;
        } else {
            double newDir = (Math.atan(getForceX() / inputForceY));
            double newForce = (Math.sqrt(Math.pow(getForceX(), 2) + Math.pow(inputForceY, 2)));
//        System.out.println("New VectorXY: " + getForceX() + " " + inputForceY);
            if (newDir < 0) {
                newDir = (Math.PI * 2) + newDir;
            }
            if (getForceX() < 0 && inputForceY < 0) {
                newDir = Math.PI + newDir;
            }
            dir = newDir;
            force = newForce;
        }
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

    public BVector(double inputDir, double inputForce) {
        dir = inputDir;
        force = inputForce;
    }
//    public BVector(double inputForceX, double inputForceY) {
//        setForceXY(inputForceX, inputForceY);
//    }

    public BVector(BVector inputBVector1, BVector inputBVector2) {
        setForceX(inputBVector1.getForceX() + inputBVector2.getForceX());
        setForceY(inputBVector1.getForceY() + inputBVector2.getForceY());
    }
}
