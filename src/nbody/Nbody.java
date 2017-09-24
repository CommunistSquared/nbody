package nbody;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Nbody extends JPanel implements ActionListener, MouseListener {

    // Index of the body that camera is centered on
    boolean running;
    int cameraIndex = 0;
    double scale = 0.5;
    int frame = 0;
    BSim sim;
    int fps = 60;
    int accuracy = 5;
    int trailFactor = 1;
    double timeScale = 1;
    Timer timer = new Timer(1000 / fps, this);
    BPoint camera = new BPoint(0, 0);
    //x, y, mass, name, dir, speed, trail length   
    static Body[] bodies = new Body[]{
        new Body(200.0, 500.0, 100.0, "sun1", 0.0, 0.2, 1000),
        new Body(200.0, 200.0, 10.0, "planet1", 0.0, 2.3, 1000),
        new Body(200.0, 150.0, 1.0, "moon1", 0.0, 0.74, 1000)
    };
    static Dimension size = new Dimension(1000, 1000);

    //creates BSim when called
    public Nbody(Body[] inputBodies, double inputGravConstant) {
        sim = new BSim(inputBodies, inputGravConstant);
        JFrame window = new JFrame("Nbody");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setSize((int) size.getWidth(), (int) size.getHeight());
        window.getContentPane().add(this);
        window.addMouseListener(this);
        addMouseListener(this);
        timer.start();
    }

    //create jpanel window and initialize NBody
    public static void createSim(Body[] bodies, double grav) {
        Nbody game = new Nbody(bodies, grav);
    }

    //this is called every frame
    @Override
    public void actionPerformed(ActionEvent ev) {
        sim.nextTick(fps, timeScale, (accuracy));
        repaint();
        frame++;
//            System.out.println(frame);     
    }

    //this draws the frame after im done calculating the positions and vectors, it draws an oval for each body as well as the name 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int a = 0; a < sim.getBodies().length; a++) {
            camera = sim.getBodies()[cameraIndex].getBPoint();
            Body body = sim.getBodies()[a];
            String name = body.getName();
            int mass = (int) body.getMass();
            int diameter = (int) Math.sqrt(body.getMass());
            int camX = (int) camera.getX() - (int) ((size.getWidth() / 2) / scale);
            int camY = (int) camera.getY() - (int) ((size.getHeight() / 2) / scale);
            int x = (int) body.getX() - camX;
            int y = (int) body.getY() - camY;
            int trailLength = (int) body.getTrail().length;
            g.drawOval((int) ((x - diameter / 2) * scale), (int) ((y - diameter / 2) * scale), (int) (diameter * scale), (int) (diameter * scale));
            g.drawString(name, (int) (x * scale), (int) ((y - diameter - 5) * scale));
            if (a == 1) {
                drawTrailsRelative(g, body, sim.getBodies()[0], camX, camY);
            }
            else if (a == 2){
                drawTrailsRelative(g, body, sim.getBodies()[1], camX, camY);
            } else {
                drawTrailsAbsolute(g, body, camX, camY);
            }

        }
    }

    public void drawTrailsAbsolute(Graphics g, Body inputBody, int inputCamX, int inputCamY) {
        int trailLength = (int) inputBody.getTrail().length;
        for (int a = 0; a < trailLength - 1; a++) {
            if (inputBody.getTrail()[a] != null) {
                int trailX = (int) ((inputBody.getTrail()[a].getX() - inputCamX) * scale);
                int trailY = (int) ((inputBody.getTrail()[a].getY() - inputCamY) * scale);
                int trailNextX = (int) ((inputBody.getTrail()[a + 1].getX() - inputCamX) * scale);
                int trailNextY = (int) ((inputBody.getTrail()[a + 1].getY() - inputCamY) * scale);
                g.drawLine(trailX, trailY, trailNextX, trailNextY);
            }
        }
    }

    public void drawTrailsRelative(Graphics g, Body inputBody, Body body, int inputCamX, int inputCamY) {
        int trailLength = (int) inputBody.getTrail().length;
        for (int a = 0; a < trailLength - 1; a++) {
            if (inputBody.getTrail()[a] != null) {
                int trailX = (int) ((inputBody.getTrail()[a].getX() - (int) body.getTrail()[a].getDistanceXTo(body.getBPoint()) - inputCamX) * scale);
                int trailY = (int) ((inputBody.getTrail()[a].getY() - (int) body.getTrail()[a].getDistanceYTo(body.getBPoint()) - inputCamY) * scale);
                int trailNextX = (int) ((inputBody.getTrail()[a + 1].getX() - (int) body.getTrail()[a + 1].getDistanceXTo(body.getBPoint()) - inputCamX) * scale);
                int trailNextY = (int) ((inputBody.getTrail()[a + 1].getY() - (int) body.getTrail()[a + 1].getDistanceYTo(body.getBPoint()) - inputCamY) * scale);
                g.drawLine(trailX, trailY, trailNextX, trailNextY);
            }
        }
    }

    //this is called first when i run this program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createSim(bodies, -10.0);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < bodies.length; i++) {
            if (sim.getBodies()[i].getBPoint().mouseTouching(e, 100)) {
                System.out.println("inbounds");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//                if (timer.isRunning()) {
//                    timer.stop();
//                } else {
//                    timer.start();
//                }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
