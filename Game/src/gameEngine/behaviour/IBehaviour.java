package gameEngine.behaviour;

import gameEngine.object.IGameObject;

import java.awt.event.InputEvent;
import java.util.List;

/**
 * The {@link  gameEngine.behaviour.Behaviour} interface defines the behavior of objects in the game.
 * It provides methods for managing interactions with GameObjects, updating their state,
 * and applying physics or game logic.
 * 
 * This interface allows behaviors to be associated with specific GameObjects, supporting
 * updates, collision events, and lifecycle methods (init, enable, disable, destroy).
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public interface IBehaviour {

    /**
     * Returns the GameObject associated with this behavior.
     *
     * @return The GameObject linked to the behavior.
     */
    IGameObject gameObject();

    /**
     * Sets the GameObject controlled by this behavior.
     *
     * @param gameObject New GameObject to be controlled.
     */
    void gameObject(IGameObject gameObject);

    /**
     * Updates the state of the GameObject every frame.
     * This method is called by the game engine during each frame update.
     *
     * @param dT Time since the last frame in seconds.
     * @param ie User input events for the current frame.
     */
    void onUpdate(double dT, InputEvent ie);

    /**
     * Handles collisions with other GameObjects.
     * 
     * @param gameObjects List of objects that have collided with this GameObject.
     */
    void onCollision(List<IGameObject> gameObjects);

    /**
     * Initializes the behavior (called once at the beginning).
     */
    void onInit();

    /**
     * Executes when the GameObject is enabled.
     */
    void onEnabled();

    /**
     * Executes when the GameObject is disabled.
     */
    void onDisabled();

    /**
     * Executes when the GameObject is destroyed.
     */
    void onDestroy();
}