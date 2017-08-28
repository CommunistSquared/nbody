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
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].update(bodies, gravConstant);
        }
    }

    public BSim(Body[] inputBodies, double inputGravConstant) {
        setBodies(inputBodies);
        setGravConstant(inputGravConstant);
    }

}
