package nbody;

import java.util.Arrays;

public class BSim {

    //gravity here
    Body[] bodies;
    double grav;

    public Body[] getBodies() {
        return bodies;
    }

    public void setBodies(Body[] bodies) {
        this.bodies = bodies;
    }

    public double getGrav() {
        return grav;
    }

    public void setGrav(double grav) {
        this.grav = grav;
    }

    public void nextTick() {
        for (int i = 0; i < bodies.length; i++) {
            bodies[i].update(bodies, grav);
        }
    }

    public BSim(Body[] bodies, double grav) {
        setBodies(bodies);
        setGrav(grav);
    }

}
