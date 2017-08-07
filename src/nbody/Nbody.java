package nbody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Nbody extends JPanel implements ActionListener {

    int frame = 0;
    int frameEnd;
    BSim sim;
    Timer timer = new Timer(33, this);

    //creates BSim when called
    public Nbody(int inputFrameEnd, Body[] inputBodies) {
        frameEnd = inputFrameEnd;
        sim = new BSim(inputBodies, -10.0);
        timer.start();
    }

    //create jpanel window and initialize NBody
    public static void createWindow(int x, int y, Body[] inputBodies) {
        JFrame window = new JFrame("Nbody");
        Nbody game = new Nbody(2500, inputBodies);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(game);
        window.pack();
        window.setVisible(true);
        window.setSize(2000, 1000);
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
        Graphics2D g2 = (Graphics2D) g;
        for (int a = 0; a < sim.getBodies().length; a++) {
            g2.drawOval((int) sim.getBodies()[a].getX(), (int) sim.getBodies()[a].getY(), (int) sim.getBodies()[a].getMass(), (int) sim.getBodies()[a].getMass());
            g2.drawString(sim.getBodies()[a].getName(), (int) sim.getBodies()[a].getX(), (int) sim.getBodies()[a].getY() - 5);
        }
    }

    //this is called first when i run this program, this is also where i create the actual bodies to simulate
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Body[] inputBodies = new Body[3];
                //(x, y, mass, name, dir, force)
                inputBodies[0] = new Body(500.0, 500.0, 100.0, "planet1", 0.0, 0.0);
                inputBodies[1] = new Body(500.0, 200.0, 10.0, "moon1", 0.0, 2.2);
                inputBodies[2] = new Body(500.0, 150.0, 1.0, "meteor1", 0.0, 0.7);
                createWindow(2000, 1000, inputBodies);
            }
        });
    }
}
