package nbody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Nbody extends JPanel implements ActionListener {

    int frame = 0;
    int frameEnd;
    int fps;
    double gravConstant = -10;
    Body[] bodies;
    Timer timer = new Timer(33, this);

    public Nbody(int inputFrameEnd, Body[] inputBodies) {
        frameEnd = inputFrameEnd;
        bodies = inputBodies;
        timer.start();
    }

    //create jpanel window
    public static void createWindow(int x, int y, Body[] inputBodies) {
        JFrame window = new JFrame("Nbody");
        Nbody game = new Nbody(2500, inputBodies);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(game);
        window.pack();
        window.setVisible(true);
        window.setSize(2000, 1000);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
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
            repaint();
            frame++;
            System.out.println(frame);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int a = 0; a < bodies.length; a++) {
//            g2.drawLine((int) bodies[a].getX(), (int) bodies[a].getY(), (int) bodies[a].getX(), (int) bodies[a].getY());
            g2.drawOval((int) bodies[a].getX(), (int) bodies[a].getY(), (int) bodies[a].getMass(), (int) bodies[a].getMass());
            g2.drawString(bodies[a].getName(), (int) bodies[a].getX(), (int) bodies[a].getY() - 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Body[] inputBodies = new Body[2];
                //(x, y, mass, name, dir, force)
                inputBodies[0] = new Body(400.0, 400.0, 50.0, "planet1", 0.0, 0.0);
                inputBodies[1] = new Body(400.0, 300.0, 5.0, "meteor1", 0.0, 2.7);
//                inputBodies[2] = new Body(300.0, 300.0, 50.0, "meteor2", 0.0, 0.0);
                createWindow(2000, 1000, inputBodies);
            }
        });
    }
}
