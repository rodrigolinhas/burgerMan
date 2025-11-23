package gameEngine.object;

import collisions.Point;
import collisions.Polygon;
import gameEngine.Transform;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.shape.PlayerShape;
import org.jetbrains.annotations.NotNull;

/**
 * Responsive class to represent a Player GameObject.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version April 29, 2025
 */
public class Player extends GameObject {
    /**
     * Creates a new Player object with the specified initial position.
     * @param position Initial position of the player in the environment.
     */
    public Player(@NotNull Point position) {
        super("Player", Transform.simpleTransform(position), Polygon.simpleSquare(position, 1), new PlayerBehaviour(null), new PlayerShape(null));
        behaviour.gameObject(this);
        shape.gameObject(this);
    }
}
