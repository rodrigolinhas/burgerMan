package gameEngine.object;

import collisions.Circle;
import collisions.Point;
import gameEngine.Transform;
import gameEngine.behaviour.CollectibleBehaviour;
import gameEngine.shape.CollectibleShape;
import org.jetbrains.annotations.NotNull;

/**
 * Responsive class to represent a Collectible GameObject.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version April 29, 2025
 */
public class Collectible extends GameObject {
    private final Type type;

    /**
     * Creates a new Collectible object with the specified type and position.
     *
     * @param type Type of the collectible (e.g., TOMATO, CHEESE).
     * @param position Initial position of the collectible in the environment.
     */
    public Collectible(@NotNull Type type, @NotNull Point position) {
        super(type.name, Transform.simpleTransform(position), new Circle(position, 0.5), new CollectibleBehaviour(null), null);
        this.type = type;
        behaviour.gameObject(this);
        shape = new CollectibleShape(type, this);
    }

    /**
     * Returns the type of the collectible.
     *
     * @return the type of the collectible
     */
    public Type getType() {
        return type;
    }
}
