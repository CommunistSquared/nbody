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
            g.drawOval((int) sim.getBodies()[a].getX()-(int) sim.getBodies()[a].getMass()/2, (int) sim.getBodies()[a].getY()-(int) sim.getBodies()[a].getMass()/2, (int) sim.getBodies()[a].getMass(), (int) sim.getBodies()[a].getMass());
            g.drawString(sim.getBodies()[a].getName(), (int) sim.getBodies()[a].getX(), (int) sim.getBodies()[a].getY() - 5);
            for (int b = 0; b < sim.getBodies()[a].getTrail().length; b++) {
                if(sim.getBodies()[a].getTrail()[b] != null){
                g.drawLine((int) sim.getBodies()[a].getTrail()[b].getX(), (int) sim.getBodies()[a].getTrail()[b].getY(), (int) sim.getBodies()[a].getTrail()[b].getX(), (int) sim.getBodies()[a].getTrail()[b].getY());
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
                inputBodies[0] = new Body(500.0, 500.0, 100.0, "planet1", 0.0, 0.2, 1000);
                inputBodies[1] = new Body(500.0, 200.0, 10.0, "moon1", 0.0, 2.4, 1000);
                inputBodies[2] = new Body(500.0, 150.0, 1.0, "meteor1", 0.0, 0.9, 1000);
                createSim(2000, 1000, inputBodies, -10.0);
            }
        });
    }
}
