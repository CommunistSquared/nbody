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

    //this is called every frame, i calculate the separate vectors from a body to the rest of the bodies and then combine them into a single vector which i then add to the body. 
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

    //this draws the frame after im done calculating the positions and vectors, it draws an oval for each body as well as the name 
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

    //this is called first when i run this program, this is also where i create the actual bodies to simulate
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Body[] inputBodies = new Body[3];
                //(x, y, mass, name, dir, force)
                inputBodies[0] = new Body(500.0, 500.0, 100.0, "planet1", 0.0, 0.0);
                inputBodies[1] = new Body(500.0, 200.0, 10.0, "moon1", 0.0, 2.0);
                inputBodies[2] = new Body(500.0, 150.0, 1.0, "meteor1", 0.0, 0.5);
                createWindow(2000, 1000, inputBodies);
            }
        });
    }
}
