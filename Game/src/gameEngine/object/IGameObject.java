package gameEngine.object;

import gameEngine.ICollider;
import gameEngine.shape.IShape;
import gameEngine.ITransform;
import gameEngine.behaviour.IBehaviour;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code game.IGameObject} interface stores information about
 * an object's position, layer, angle and scale.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 25, 2025
 */
public interface IGameObject {
    /**
     * Returns the name of the game.GameObject.
     * @return the name of the game.GameObject.
     */
    String name();

    /**
     * Returns the game.Transform of the game.GameObject.
     * @return the game.Transform of the game.GameObject.
     */
    ITransform transform();

    /**
     * Returns the game.Behaviour of the game.GameObject.
     * @return the game.Behaviour of the game.GameObject.
     */
    IBehaviour behaviour();

    /**
     * Returns the Collider of the game.GameObject.
     * <p>The centroid will always lie at {@code this.transform().position()}.
     * @return the Collider of the game.GameObject.
     */
    @Nullable ICollider collider();

    /**
     * Returns the Shape of the game.gameObject
     * @return the Shape of the game.gameObject
     */
    IShape shape();
}