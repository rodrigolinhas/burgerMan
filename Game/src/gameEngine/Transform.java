package gameEngine;

import collisions.Point;

import java.util.Locale;

/**
 * This class represent the transform of a game.GameObject
 * It provides methods to manipulate and retrieve these transformation properties.
 * including position, layer, rotation, and scaling.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public class Transform implements ITransform
{
    private Point position;
    private int layer;
    private double angle;
    private double scale;

    /**
     * Constructs a game.Transform object with the specified initial properties.
     * @param position The initial position of the transformation as a collisions.Point.
     * @param layer The initial layer (z-index) of the transformation.
     * @param angle The initial rotation angle in degrees.
     * @param scale The initial scaling factor relative to the original size.
     */
    public Transform(Point position, int layer, double angle, double scale)
    {
        this.position = position;
        this.layer = layer;
        if (angle >= 0 || angle <= 360) this.angle = angle; //stored in degrees, because method angle of interface return angle in degrees
        this.scale = scale;
    }

    /**
     * Method that moves the transformation by adding a
     * delta to the current position and layer.
     * @param dPos The change in position (delta x and y) to apply.
     * @param dlayer The change in layer (z-index) to apply.
     */
    @Override
    public void move(Point dPos, int dlayer)
    {
        this.position.move(dPos); //mover ponto consoante dPos
        this.layer += dlayer; //aplicar o dlayer a layer
    }

    /**
     * Rotates the transformation by the specified delta angle (in degrees).
     * The resulting angle is kept within the 0-360 degree range.
     * @param dTheta The change in rotation angle (in degrees) to apply.
     */
    @Override
    public void rotate(double dTheta)
    {
        this.angle = (this.angle + dTheta) % 360;
        if (this.angle < 0) this.angle += 360;
    }

    /**
     * Modifies the scale by adding the specified delta scaling factor.
     * @param dScale The change in scaling factor to apply.
     */
    @Override
    public void scale(double dScale) { this.scale += dScale;}

    /**
     * Returns the current position of the transformation.
     * @return The current position as a collisions.Point.
     */
    @Override
    public Point position() { return position; }

    /**
     * Returns the current layer of the transformation.
     * @return The current layer.
     */
    @Override
    public int layer() { return this.layer; }

    /**
     * Returns the current rotation angle in degrees.
     * @return The current angle in degrees, normalized to 0-360.
     */
    @Override
    public double angle() { return this.angle; }

    /**
     * Returns the current scaling factor.
     * @return The current scale relative to the original size.
     */
    @Override
    public double scale() { return this.scale; }

    /**
     * Returns a string representation of the transformation in the format:
     * "position layer angle scale".
     * @return A string containing the position, layer, angle, and scale.
     */
    @Override
    public String toString()
    {
        return this.position.toString() + " " + this.layer + " " + String.format(Locale.US, "%.2f", this.angle) + " " + String.format(Locale.US, "%.2f", this.scale);
    }
    /**
     * Returns a simpletransform:
     * @return A simpletransform.
     */
    public static Transform simpleTransform(Point position) {
        return new Transform(position, 0, 0, 1);
    }

    /**
     * Returns a transform for the background of the game.
     * The background is positioned at (336, 384) with no rotation and a scale of -1.
     * @return A Transform object representing the background.
     */
    public static Transform backgroundTransform() {
        return new Transform(new Point(336, 384), -1, 0, 1);
    }
}
