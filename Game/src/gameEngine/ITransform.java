package gameEngine;

import collisions.Point;

/**
 * The {@code game.ITransform} interface stores information about
 * an object's position, movement, rotation, layer, angle an so for.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public interface ITransform {
    /**
     * Move this game.ITransform by dPos.x(), dPos.y() and dlayer
     * @param dPos the 2D differential to move
     * @param dlayer the layer differential
     */
    void move(Point dPos, int dlayer);

    /**
     * Rotate this game.ITransform from current orientation by dTheta
     * @param dTheta
     * pos: 0 <= this.angle() < 360
     */
    void rotate(double dTheta);

    /**
     * increment the game.ITransform scale by dscale
     * @param dScale the scale increment
     */
    void scale(double dScale);

    /**
     * @return the (x,y) coordinates
     */
    Point position();

    /**
     * @return the layer
     */
    int layer();

    /**
     * @return the angle in degrees
     */
    double angle();

    /**
     * @return the current scale factor
     */
    double scale();
}