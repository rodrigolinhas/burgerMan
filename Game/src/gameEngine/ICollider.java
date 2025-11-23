package gameEngine;

import collisions.Circle;
import collisions.Point;
import collisions.Polygon;

/**
 * The {@code game.ICollider} interface stores information about
 * an object's colision.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public interface ICollider
{
    /**
     * Returns the centroid of the collider.
     * @return the centroid of the collider.
     */
    Point centroid();

    /**
     * Move the centroid of a collider consonant the point param.
     * @param point
     */
    void move(Point point);

    /**
     * Rotate the collider consonant the angle param.
     * @param angle
     */
    void rotate(double angle);

    /**
     * Scale the collider consonant the scale param passed.
     * @param scale
     */
    void scale(double scale);


    /**
     * Verifica se colide com um ICOllider
     * @param other ICollider
     * @return boolean true se está a colidir
     *                 false cc
     */
    boolean isColliding(ICollider other);

    /**
     * Verifica se colide com um Poligono
     * @param other Poligono
     * @return boolean true se está a colidir
     *                 false cc
     */
    boolean isColliding (Polygon other);

    /**
     * Verifica se colide com um Circulo
     * @param other Circulo
     * @return boolean true se está a colidir
     *                 false cc
     */
    boolean isColliding (Circle other);
}