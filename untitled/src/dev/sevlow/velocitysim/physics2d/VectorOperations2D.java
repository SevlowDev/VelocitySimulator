package dev.sevlow.velocitysim.physics2d;

public class VectorOperations2D {
    public static Vector2D sumVectors(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY(), Vector2D.Vector2DConstructor.XY);
    }

    public static Vector2D multiplyVectorByScalar(Vector2D v, double s) {
        return new Vector2D(v.getValue() * s, v.getDirection(), Vector2D.Vector2DConstructor.VD);
    }

    public static Vector2D reverseVector(Vector2D v) {
        return new Vector2D(-1 * v.getX(), -1 * v.getY(), Vector2D.Vector2DConstructor.XY);
    }
}
