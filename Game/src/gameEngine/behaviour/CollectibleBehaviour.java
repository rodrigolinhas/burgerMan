package gameEngine.behaviour;

import gameEngine.ICollider;
import gameEngine.object.*;
import gameEngine.Client;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.InputEvent;
import java.util.List;

/**
 * Responsive class to deal with the behavior of collectible items in the game.
 * Handles interactions such as collisions or state changes for collectibles.
 * 
 * A collectible is a type of object that players or other objects can interact with 
 * during gameplay.
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public class CollectibleBehaviour extends Behaviour {
    private boolean collected = false;
    private Type collectibleType;

    /**
     * Constructor that associates a GameObject with this behavior.
     * 
     * @param collectible The GameObject that will be controlled by this behavior.
     */
    public CollectibleBehaviour(@Nullable Collectible collectible) {
        super(collectible);
    }

    @Override
    public void gameObject(IGameObject gameObject) {
        super.gameObject(gameObject);
        this.collectibleType = ((Collectible) gameObject).getType();
    }

    /**
     * Updates the collectible's state every frame.
     *
     * @param dT The time elapsed since the last frame (in seconds).
     * @param ie The user's input event.
     */
    @Override
    public void onUpdate(double dT, InputEvent ie) {
        if (collected) {
            // Destroys the object when collected
            onDestroy();
            collected = false;
        }
    }

    /**
     * Handles collisions between this collectible and other objects.
     *
     * @param gameObjects List of objects that collided with this collectible.
     */
    @Override
    public void onCollision(List<IGameObject> gameObjects) {
        ICollider c1 = igameObject.collider(), c2;
        for (IGameObject obj : gameObjects) {
            if (isCollision(obj) && ((c2 = obj.collider())) != null && c2.isColliding(c1)) {
                collected = true;
                break;
            }
        }
    }

    /**
     * Helper method for {@link #onCollision(List)}.
     * 
     * @param obj The object that collided with this collectible.
     * @return true if the object collided with this collectible, false otherwise.
     */
    private boolean isCollision(IGameObject obj) {
        if (obj instanceof Player) return true;
        else return obj instanceof Enemy && collectibleType == Type.PICKLE;
    }

    /**
     * Executes when the behavior is initialized.
     */
    @Override
    public void onInit() {
        collected = false;
    }

    /**
     * Executes when the object is enabled.
     */
    @Override
    public void onEnabled() {}

    /**
     * Executes when the object is disabled.
     */
    @Override
    public void onDisabled() {}

    /**
     * Executes when the object is destroyed.
     */
    @Override
    public void onDestroy() {
        Client.ENGINE.destroy(igameObject);
    }

    // For tests
    public boolean isCollected() { return collected; }
}