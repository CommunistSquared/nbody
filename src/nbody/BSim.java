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

    public void nextTick(int fps, double timeScale, int accuracy) {
        int ticks = (int)((accuracy*fps)/timeScale);
        for (int i = 0; i < bodies.length; i++) {
            for(int j = 0; j < ticks; j++){
            bodies[i].update(bodies, grav, ticks/timeScale);
            }
        }
    }

    public BSim(Body[] bodies, double grav) {
        setBodies(bodies);
        setGrav(grav);
    }

}
