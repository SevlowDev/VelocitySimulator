package dev.sevlow.velocitysim.physics2d;

import java.util.ArrayList;
import java.util.List;

public class PhysicsObject2D {
    double x, y;
    double xVelocity, yVelocity;
    double mass;
    public List<Force2D> forces;
    //public Object aerodynamicModel2D; - When adding areodynamics - this will be the object's aerodynamics model.

    /**
     *
     * @param initialX Initial X position.
     * @param initialY Initial Y position.
     * @param initialXVelocity Initial X velocity.
     * @param initialYVelocity Initial Y velocity.
     * @param mass Object's mass.
     */
    public PhysicsObject2D(double initialX, double initialY, double initialXVelocity, double initialYVelocity, double mass) {
        forces = new ArrayList<Force2D>();
        this.x = initialX;
        this.y = initialY;
        this.xVelocity = initialXVelocity;
        this.yVelocity = initialYVelocity;
        this.mass = mass;
    }

    /**
     *
     * @return Returns the resultant force of the object based on the forces list.
     */
    public Force2D calculateForces() {
        double x = 0;
        double y = 0;
        for(Force2D f : forces) {
            x += f.getX();
            y += f.getY();
        }
        return new Force2D(x, y, Vector2D.Vector2DConstructor.XY);
    }

    /**
     *
     * @param resultantForce The resultantForce used in the equation to calculate acceleration.
     * @return Calculates the acceleration based on the resultantForce param.
     */
    public Vector2D calculateAcceleration(Force2D resultantForce) {
        return new Vector2D(resultantForce.getForce() / mass, resultantForce.direction, Vector2D.Vector2DConstructor.VD);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMass() {
        return mass;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getYvelocity() {
        return yVelocity;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public List<Force2D> getForces() {
        return forces;
    }

    public void setForces(List<Force2D> forces) {
        this.forces = forces;
    }

    public void addForce(Force2D force) {
        this.forces.add(force);
    }

    public void removeForce(Force2D force) {
        this.forces.remove(force);
    }

    public void removeForce(int forceIndex) {
        if(this.forces.size() - 1 < forceIndex) {
            this.forces.remove(forceIndex);
        }
    }

    public void replaceForce(int forceIndex, Force2D force) {
        if(this.forces.size() - 1 < forceIndex) {
            this.forces.set(forceIndex, force);
        }
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void updateVelocities(Vector2D acceleration, double simulationTime) {
        this.xVelocity += (acceleration.getX() * simulationTime);
        this.yVelocity += (acceleration.getY() * simulationTime);
    }

    /**
     *
     * @param simulationTime Time multiplier, set 1/60 for 60 FPS if you want to see values with m/s by actual seconds.
     */
    public void updatePosition(double simulationTime) {
        this.x += (this.xVelocity * simulationTime);
        this.y += (this.yVelocity * simulationTime);
    }
}
