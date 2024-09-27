package dev.sevlow.velocitysim.physics2d;

public class Vector2D {
    public enum Vector2DConstructor {
        VD, XY
    }

    double value;
    double direction;
    double x;
    double y;


    /**
     *
     * @param value_x For VD constructor - value of the vector, for XY - x coordinate
     * @param direction_y For VD constructor - direction of the vector in angles relative to the X axis, for XY - y coordinate
     * @param type The type of constructor you are using, for VD provide the vector's value and direction, for XY the x and y
     */
    public Vector2D(double value_x, double direction_y, Vector2DConstructor type) {
        if(type == Vector2DConstructor.VD) {
            this.value = value_x;
            this.direction = direction_y;
            updateXY();
        } else {
            this.x = value_x;
            this.y = direction_y;
            updateVD();
        }
    }

    void updateXY() {
        double radians = Math.toRadians(this.direction);
        this.x = this.value * Math.cos(radians);
        this.y = this.value * Math.sin(radians);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
        updateVD();
    }

    void updateVD() {
        double radians = Math.atan2(this.y, this.x);
        this.direction = Math.toDegrees(radians);
        this.value = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public double getValue() {
        return this.value;
    }

    public double getDirection() {
        return this.direction;
    }

    public void setValue(int value) {
        this.value = value;
        updateXY();
    }

    public void setDirection(int direction) {
        this.direction = direction;
        updateXY();
    }

    public void changeValue(int valueModifier) {
        this.value += valueModifier;
        updateXY();
    }
}
