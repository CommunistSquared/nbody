package nbody;

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
                    System.out.println(bodies[b].getName() + " influencing " + bodies[a].getName() + " by ForceX: " + gravBVector.getForceX() + " by ForceY: " + gravBVector.getForceY());
                }
            }
            bodies[a].addBVector(gravTotalBVector);
            bodies[a].addXY(bodies[a].getBVector().getForceX(), bodies[a].getBVector().getForceY());
            System.out.println(bodies[a].getName() + "'s vector - ForceX: " + bodies[a].getBVector().getForceX() + " ForceY: " + bodies[a].getBVector().getForceY());
        }
    }

    public BSim(Body[] inputBodies, double inputGravConstant) {
        setBodies(inputBodies);
        setGravConstant(inputGravConstant);
    }

}
