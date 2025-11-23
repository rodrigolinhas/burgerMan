package collisions;

import gameEngine.ICollider;

/**
 * This class represent the collider
 * <p>The collider is necessary to determine if the
 * given object collides with another one
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 26, 2025
 */
public abstract class Colisor implements ICollider
{
    protected Point centroid; //center of a collider

    /**
     * Simple method to check if collide with another collider
     * @return boolean depending on the situation
     *         true if it happens
     *         false otherwise
     */

    // Métodos para Double Dispatch
    public abstract boolean isColliding(ICollider other);
    public abstract boolean isColliding(Circle circle);
    public abstract boolean isColliding(Polygon polygon);
    public abstract boolean contains(Point point);

    /**
     * Method to move the given object
     * @param point
     */
    public abstract void move(Point point);

    /**
     * Method toString
     * @return specific string format
     *         depending on the invoker
     */
    public abstract String toString();

}
