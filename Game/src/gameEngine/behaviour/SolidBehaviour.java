package gameEngine.behaviour;

import collisions.Point;
import gameEngine.ICollider;
import gameEngine.object.GameObject;
import gameEngine.object.IGameObject;
import gameEngine.object.Solid;

import java.awt.event.InputEvent;
import java.util.List;

/**
 * A responsive class to handle the behavior of Solid objects (the walls).
 * <p>
 * This class ensures the walls interact correctly with other objects and handle
 * collisions, providing physical responses to prevent overlapping.
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * 
 * @version May 11, 2025
 */
public class SolidBehaviour extends Behaviour {
    private final boolean enemyWall;

    /**
     * Constructor that associates a GameObject with this behavior.
     * By default, the behavior is not for enemy-specific walls.
     * 
     * @param solid The GameObject controlled by this behavior.
     */
    public SolidBehaviour(Solid solid) {
        this(solid, false);
    }

    /**
     * Constructor that associates a GameObject with this behavior, specifying
     * if the wall is specifically for enemies.
     * 
     * @param solid The GameObject controlled by this behavior.
     * @param enemyWall Indicates if the wall behavior is for enemies.
     */
    public SolidBehaviour(Solid solid, boolean enemyWall) {
        super(solid);
        this.enemyWall = enemyWall;
    }

    /**
     * Checks if this wall is specific to enemies.
     * 
     * @return True if it is an enemy-specific wall, otherwise false.
     */
    public boolean enemyWall() {
        return enemyWall;
    }

    /**
     * Updates the state of the GameObject every frame.
     * 
     * @param dT Time since the last frame in seconds.
     * @param ie User input events.
     */
    @Override
    public void onUpdate(double dT, InputEvent ie) {
        // No additional per-frame implementation provided.
    }

    /**
     * Handles collisions with other GameObjects.
     * This method calculates collision responses and moves colliding objects
     * as appropriate to resolve overlaps.
     * 
     * @param gameObjects A list of objects that collided with this wall.
     */
    @Override
    public void onCollision(List<IGameObject> gameObjects) {
        for (IGameObject gameObject : gameObjects) {
            if (gameObject.transform() != null && gameObject.collider() != null) {
                Point collisionResponse = calculateCollisionResponse((GameObject) gameObject);
                ((GameObject) gameObject).move(collisionResponse, gameObject.transform().layer());
            }
        }
    }

    /**
     * Auxiliary method for the {@code onCollision } logic.
     * <p>
     * Calculates the collision response between this Solid object and another GameObject.
     * <p>
     * The response is a displacement vector indicating movement in the opposite direction
     * of the primary axis of collision (either X or Y).
     * 
     * @param gameObject The GameObject colliding with this Solid.
     * @return A Point object representing the collision response vector, or null
     *         if the GameObject has no collider.
     */
    private Point calculateCollisionResponse(GameObject gameObject) {
        ICollider c1 = igameObject.collider(), c2 = gameObject.collider();
        if (c1 == null || c2 == null) return null;
        Point solidCentroid = c1.centroid();
        Point gameObjectCentroid = c2.centroid();

        double dx = gameObjectCentroid.getX() - solidCentroid.getX();
        double dy = gameObjectCentroid.getY() - solidCentroid.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            return new Point(-dx, 0); // Colliding along the X-axis
        } else {
            return new Point(0, -dy); // Colliding along the Y-axis
        }
    }

    /**
     * Initializes the behavior (called once at the beginning).
     */
    @Override
    public void onInit() {
        // No specific initialization logic provided.
    }

    /**
     * Executed when the GameObject is enabled.
     */
    @Override
    public void onEnabled() {
        // No specific enable logic provided.
    }

    /**
     * Executed when the GameObject is disabled.
     */
    @Override
    public void onDisabled() {
        // No specific disable logic provided.
    }

    /**
     * Executed when the GameObject is destroyed.
     */
    @Override
    public void onDestroy() {
        // No specific destruction logic provided.
    }
}