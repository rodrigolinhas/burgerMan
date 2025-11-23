package gameEngine.object;

import collisions.Point;
import collisions.Polygon;
import gameEngine.Transform;
import gameEngine.behaviour.SolidBehaviour;

/**
 * Responsive class to represent a Solid GameObject.
 * <p>
 * This class is responsible for creating solid objects in the game environment.
 * <p>
 * It extends the GameObject class and uses a SolidBehaviour to define its behavior.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version April 29, 2025
 */
public class Solid extends GameObject {
    /**
     * Constructs a solid object at the specified position with given dimensions.
     *
     * @param topleft    The top-left point coordinates of the solid object
     * @param horizontal The horizontal dimension (width) of the solid object
     * @param vertical   The vertical dimension (height) of the solid object
     */
    
    public Solid(Point topleft, double horizontal, double vertical) {
        super("Solid", Transform.simpleTransform(topleft), Polygon.simpleRectangle(topleft, horizontal, vertical),
                new SolidBehaviour(null), null);
        behaviour.gameObject(this);
    }
}
