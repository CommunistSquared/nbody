package nbody;

import java.util.Arrays;

public class BSim {

    //gravity here
    Body[] bodies;
    double gravConstant;

    public Body[] getBodies() {
        return bodies;
    }

    public void setBodies(Body[] inputBodies) {
        bodies = inputBodies;
    }

    public double getGravConstant() {
        return gravConstant;
    }

    public void setGravConstant(double inputGravConstant) {
        gravConstant = inputGravConstant;
    }

    public void nextTick() {
        for (int a = 0; a < bodies.length; a++) {
            BVector gravTotalBVector = new BVector(0.0, 0.0);
            for (int b = 0; b < bodies.length; b++) {
                if (b != a) {
                    BVector gravBVector = new BVector(bodies[a].getDirTo(bodies[b]), gravConstant * bodies[b].getMass() / Math.pow(bodies[a].getDistanceTo(bodies[b]), 2));
                    gravTotalBVector.addBVector(gravBVector);
                    bodies[a].checkCollision(bodies[b]);
                }
            }
            bodies[a].addBVector(gravTotalBVector);
            bodies[a].addXY(bodies[a].getBVector().getForceX(), bodies[a].getBVector().getForceY());
            bodies[a].setTrail(calculateTrail(bodies[a]));
        }
    }

    public BPoint[] calculateTrail(Body inputBody) {
        BPoint[] newTrail = new BPoint[inputBody.getTrail().length];
        for (int a = 1; a < inputBody.getTrail().length; a++) {
            newTrail[a - 1] = inputBody.getTrail()[a];
        }
        newTrail[(inputBody.getTrail().length) - 1] = inputBody.getBPoint();
        return newTrail;
    }

    public BSim(Body[] inputBodies, double inputGravConstant) {
        setBodies(inputBodies);
        setGravConstant(inputGravConstant);
    }

}
