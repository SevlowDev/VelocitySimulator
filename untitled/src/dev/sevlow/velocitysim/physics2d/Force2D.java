package dev.sevlow.velocitysim.physics2d;

public class Force2D extends Vector2D{
    /**
     * @param value_x     For VD constructor - value of the force in Newtons, for XY - x coordinate
     * @param direction_y For VD constructor - direction of the vector in angles relative to the X axis, for XY - y coordinate
     * @param type        The type of constructor you are using, for VD provide the vector's value and direction, for XY the x and y
     */
    public Force2D(double value_x, double direction_y, Vector2DConstructor type) {
        super(value_x, direction_y, type);
    }
    public Force2D(Vector2D vector) {
        super(vector.getValue(), vector.getDirection(), Vector2DConstructor.VD);
    }

    /**
     *
     * @return Returns the force's magnification.
     */
    public double getForce() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector2D getVector() {
        return (Vector2D) this;
    }
}
