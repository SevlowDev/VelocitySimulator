package dev.sevlow.velocitysim;

import dev.sevlow.velocitysim.physics2d.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Create the main frame for the app
        JFrame frame = new JFrame("Simulation Parameters");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));

        // Add labels and text fields for the parameters
        JTextField gravityField = new JTextField("9.8");
        JTextField constXForceField = new JTextField("0");
        JTextField constYForceField = new JTextField("0");
        JTextField massField = new JTextField("1");
        JTextField simSpeedField = new JTextField("1");
        JTextField initXVelField = new JTextField("0");
        JTextField initYVelField = new JTextField("0");
        JTextField initXDistField = new JTextField("0");
        JTextField initYHeightField = new JTextField("0");

        panel.add(new JLabel("Gravity Acceleration (m/s^2):"));
        panel.add(gravityField);

        panel.add(new JLabel("Additional Constant X Forces(N):"));
        panel.add(constXForceField);

        panel.add(new JLabel("Additional Constant Y Forces(N):"));
        panel.add(constYForceField);

        panel.add(new JLabel("Object's Mass(kg):"));
        panel.add(massField);

        panel.add(new JLabel("Simulation Speed(Simulation seconds per real second):"));
        panel.add(simSpeedField);

        panel.add(new JLabel("Initial X Velocity(m/s):"));
        panel.add(initXVelField);

        panel.add(new JLabel("Initial Y Velocity(m/s):"));
        panel.add(initYVelField);

        panel.add(new JLabel("Initial X Distance(m):"));
        panel.add(initXDistField);

        panel.add(new JLabel("Initial Y Height(m):"));
        panel.add(initYHeightField);

        // Add a run button
        JButton runButton = new JButton("Run Simulation");
        panel.add(runButton);
        panel.add(new JLabel());  // Placeholder

        // Set up the main frame
        frame.add(panel);
        frame.setVisible(true);

        // Action for run button to switch to the simulation screen
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double gravity = Double.parseDouble(gravityField.getText());
                double constXForce = Double.parseDouble(constXForceField.getText());
                double constYForce = Double.parseDouble(constYForceField.getText());
                double mass = Double.parseDouble(massField.getText());
                double simSpeed = Double.parseDouble(simSpeedField.getText());
                double initXVel = Double.parseDouble(initXVelField.getText());
                double initYVel = Double.parseDouble(initYVelField.getText());
                double initXDist = Double.parseDouble(initXDistField.getText());
                double initYHeight = Double.parseDouble(initYHeightField.getText());

                // Open the simulation screen
                JFrame simFrame = new JFrame("Simulation Screen");
                simFrame.setSize(600, 400);
                simFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Simulation screen panel
                SimulationPanel simPanel = new SimulationPanel(gravity, constXForce, constYForce, mass, simSpeed, initXVel, initYVel, initXDist, initYHeight);
                simFrame.add(simPanel);
                simFrame.setVisible(true);
                simPanel.startSimulation();
            }
        });
    }
}

// Simulation Panel that shows the parameters
class SimulationPanel extends JPanel {
    private double gravity, constXForce, constYForce, mass, simSpeed, xVelocity, yVelocity, xDistance, yHeight;
    private double simTime = 0;

    private JLabel xVelLabel = new JLabel();
    private JLabel yVelLabel = new JLabel();
    private JLabel heightLabel = new JLabel();
    private JLabel distLabel = new JLabel();
    private JLabel simTimeLabel = new JLabel();
    private int scale = 200;
    private boolean objectCentered = true;
    private PhysicsObject2D obj;

    public SimulationPanel(double gravity, double constXForce, double constYForce, double mass, double simSpeed,
                           double initXVel, double initYVel, double initXDist, double initYHeight) {
        this.gravity = gravity;
        this.constXForce = constXForce;
        this.constYForce = constYForce;
        this.mass = mass;
        this.simSpeed = simSpeed;
        this.xVelocity = initXVel;
        this.yVelocity = initYVel;
        this.xDistance = initXDist;
        this.yHeight = initYHeight;
        this.scale = 200;
        this.objectCentered = true;
        this.obj = new PhysicsObject2D(this.xDistance, this.yHeight, this.xVelocity, this.yVelocity, this.mass);

        this.obj.addForce(new Force2D(VectorOperations2D.multiplyVectorByScalar(UnitVectors2D.i, constXForce)));
        this.obj.addForce(new Force2D(VectorOperations2D.multiplyVectorByScalar(UnitVectors2D.j, constYForce)));
        this.obj.setPosition(this.xDistance, this.yHeight);
        this.obj.setxVelocity(this.xVelocity);
        this.obj.setyVelocity(this.yVelocity);
        this.obj.setMass(this.mass);


        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 1));

        infoPanel.add(new JLabel("Simulation Stats:"));
        infoPanel.add(xVelLabel);
        infoPanel.add(yVelLabel);
        infoPanel.add(heightLabel);
        infoPanel.add(distLabel);
        infoPanel.add(simTimeLabel);

        add(infoPanel, BorderLayout.NORTH);
    }

    // Method to start the simulation
    public void startSimulation() {
        Timer timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update simulation time
                simTime += (1.0 / 60.0) * simSpeed;

                // Placeholder for dev.sevlow.velocitysim.physics calculations
                // Update the velocity and positions based on the values (you'll implement this part)
                // You can update xVelocity, yVelocity, xDistance, yHeight here
                Force2D rf = obj.calculateForces();
                Vector2D a = obj.calculateAcceleration(rf);
                a = VectorOperations2D.sumVectors(a, new Vector2D(0, -1 * gravity, Vector2D.Vector2DConstructor.XY));
                obj.updateVelocities(a);
                obj.updatePosition(simTime);
                xDistance = obj.getX();
                yHeight = obj.getY();
                xVelocity = obj.getxVelocity();
                yVelocity = obj.getYvelocity();

                // Update UI labels
                xVelLabel.setText("X Velocity: " + String.format("%.2f", xVelocity) + " m/s");
                yVelLabel.setText("Y Velocity: " + String.format("%.2f", yVelocity) + " m/s");
                heightLabel.setText("Height: " + String.format("%.2f", yHeight) + " m");
                distLabel.setText("Distance (X): " + String.format("%.2f", xDistance) + " m");
                simTimeLabel.setText("Simulation Time: " + String.format("%.2f", simTime) + " s");

                // Repaint the panel to show the ball movement (you will implement the drawing part)
                repaint();
            }
        });
        timer.start();
    }

    // Override paintComponent to draw the ball (placeholder)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        // Get the dimensions of the screen
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // Calculate the center of the screen
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        int axisy0 = (int) (centerY + (((double) screenHeight / scale) * (this.yHeight)));
        g.drawLine(0, axisy0, screenWidth, axisy0);

        // Offset the ball's position by its distance from the center
        int ballScreenX = centerX;
        int ballScreenY = centerY;  // yHeight is inverted because the y-axis in Graphics goes downwards

        // Draw the ball centered in the screen
        g.fillOval(ballScreenX - 10, ballScreenY - 10, 20, 20);
    }
}
