package nbody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Nbody extends JPanel implements ActionListener {

    int frame = 0;
    BSim sim;
    Timer timer = new Timer(16, this);

    //creates BSim when called
    public Nbody(Body[] inputBodies, double inputGravConstant) {
        sim = new BSim(inputBodies, inputGravConstant);
        timer.start();
    }

    //create jpanel window and initialize NBody
    public static void createSim(int inputX, int inputY, Body[] inputBodies, double inputGravConstant) {
        JFrame window = new JFrame("Nbody");
        Nbody game = new Nbody(inputBodies, inputGravConstant);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setSize(inputX, inputY);
        window.getContentPane().add(game);
    }

    //this is called every frame
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            sim.nextTick();
            repaint();
            frame++;
            System.out.println(frame);
        }
    }

    //this draws the frame after im done calculating the positions and vectors, it draws an oval for each body as well as the name 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int a = 0; a < sim.getBodies().length; a++) {
            Body body = sim.getBodies()[a];
            String name = body.getName();
            int mass = (int) body.getMass();
            int diameter = (int) body.getMass() / 2;
            int x = (int) body.getX();
            int y = (int) body.getY();
            int trailLength = (int) body.getTrail().length;
            g.drawOval(x - diameter, y - diameter, mass, mass);
            g.drawString(name, x, y - diameter - 5);
            for (int b = 0; b < trailLength - 1; b++) {
                if (body.getTrail()[b] != null) {
                    int trailX = (int) body.getTrail()[b].getX();
                    int trailY = (int) body.getTrail()[b].getY();
                    int trailNextX = (int) body.getTrail()[b + 1].getX();
                    int trailNextY = (int) body.getTrail()[b + 1].getY();
                    g.drawLine(trailX, trailY, trailNextX, trailNextY);
                }
            }
        }
    }

    //this is called first when i run this program, this is also where i create the actual bodies to simulate
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Body[] inputBodies = new Body[3];
                //x, y, mass, name, dir, force, trail length
                inputBodies[0] = new Body(450.0, 450.0, 10.0, "sun", 0.0, 0.0, 100);
                inputBodies[1] = new Body(450.0, 50.0, 0.001, "planet", 0.0, 1.1, 10000);
                inputBodies[2] = new Body(450.0, 45.0, 0.00001, "moon", 0.0, 1.21, 100);
                createSim(1000, 1000, inputBodies, -50.0);
            }
        });
    }
}
