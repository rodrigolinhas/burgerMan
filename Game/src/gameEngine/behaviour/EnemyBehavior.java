package gameEngine.behaviour;

import collisions.Point;
import gameEngine.Client;
import gameEngine.ICollider;
import gameEngine.object.*;

import java.awt.event.InputEvent;
import java.util.List;
import java.util.Objects;

/**
 * Responsive class to deal with Enemy behavior.
 * Handles enemy states, movement logic, and interactions (e.g., collisions).
 * 
 * Enemies in the game can behave differently depending on their state.
 * Example states include "Scatter", "Chase", "Scared", and "Returning".
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public class EnemyBehavior extends Behaviour {
    public Point speed;
    private int state;
    private int buffer;
    private boolean currentInter;

    /**
     * Constructor that associates a GameObject with this behavior.
     *
     * @param enemy GameObject that will be controlled by this behavior.
     */
    public EnemyBehavior(Enemy enemy) {
        super(enemy);
        speed = new Point(0, 0);
        state = 0;
        buffer = 400;
        currentInter = false;
    }

    /**
     * Updates the enemy's state every frame based on its current behavior.
     * <p>
     * The behavior changes depending on the state of the enemy:
     * <p>
     * - Scatter State (state 0): Moves randomly.
     * <p>
     * - Chase State (state 1): Moves toward the player's last known direction.
     * <p>
     * - Scared State (state 2): Moves randomly but slower.
     * <p>
     * - Returning State (state 3): Moves back to a specific point.
     *
     * @param dT Time since the last frame (in seconds).
     * @param ie User input event.
     */
    @Override
    public void onUpdate(double dT, InputEvent ie) {
        Intersection i = Client.ENGINE.getInterAt(Objects.requireNonNull(igameObject.collider()).centroid());

        //System.out.println(i);

        if (i != null && !currentInter) {
            currentInter = true;
            switch (state) {
                case 0:
                    // SCATTER STATE
                    speed = rand(i);
                    if (--buffer <= 0) {
                        buffer = Client.RANDOM.nextInt(200) + 400;
                        state = 1;
                    }
                    break;
                case 1:
                    // CHASE STATE
                    Point last = i.getLastPlayerDir();
                    speed = last == null ? rand(i) : last;
                    if (--buffer <= 0) {
                        buffer = Client.RANDOM.nextInt(200) + 400;
                        state = 0;
                    }
                    break;
                case 2:
                    // SCARED STATE
                    speed = rand(i).scaleOrigin(0.5);
                    break;
                case 3:
                    // RETURNING STATE
                    speed = i.getReturnDir();
                    break;
            }
        } else if (i == null && currentInter)
            currentInter = false;

        ((GameObject) igameObject).move(speed, 0);
    }

    /**
     * Handles collisions between this enemy and other objects.
     * <p>
     * Responds to specific interactions:
     * <p>
     * - Pickle: Causes the player to slow down.
     * <p>
     * - Intersection: Adjusts the movement based on the current state.
     *
     * @param gameObjects List of objects that collided with this enemy.
     */
    @Override
    @SuppressWarnings("DataFlowIssue")
    public void onCollision(List<IGameObject> gameObjects) {
        ICollider c1 = igameObject.collider(), c2;
        for (IGameObject gameObject : gameObjects) {
            switch (gameObject.name()) {
                case "Pickle":
                    if ((c2 = gameObject.collider()) == null || !c2.isColliding(c1)) continue;
                    Player player;
                    if ((player = Client.ENGINE.getPlayerObject()) != null)
                        ((PlayerBehaviour) player.behaviour()).slowDown();
                    break;
                case "Inter":
                    if ((c2 = gameObject.collider()) == null || !c2.isColliding(c1) || currentInter) continue;
                    Intersection i = (Intersection) gameObject;
                    currentInter = true;
                    switch (state) {
                        case 3:
                            speed = i.getReturnDir();
                            break;
                        case 2:
                            speed = rand(i).scaleOrigin(0.8);
                            break;
                        case 1:
                            Point last = i.getLastPlayerDir();
                            speed = last == null ? rand(i) : last;
                            break;
                        case 0:
                            speed = rand(i);
                            break;
                    }
                    break;
            }
        }
    }

    /**
     * Generates a random direction for the enemy to move based on the provided intersection.
     *
     * @param i The intersection to calculate random movement from.
     * @return A Point representing the new random direction.
     */
    private Point rand(Intersection i) {
        return i.randomDir(Client.RANDOM, speed);
    }

    /**
     * Updates the enemy's state (e.g., 0 = Scatter, 1 = Chase, etc.).
     *
     * @param state The new state for the enemy.
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Executes when the behavior is initialized.
     * Sets the initial direction of movement (default: downward).
     */
    @Override
    public void onInit() {
        ICollider c = igameObject.collider();
        if (c == null) return;
        speed = new Point(0, -0.5);
        buffer = 400;
    }

    /**
     * Executes when the object is enabled.
     */
    @Override
    public void onEnabled() {
        onInit();
        Client.ENGINE.enable(gameObject());
    }

    /**
     * Executes when the object is disabled.
     */
    @Override
    public void onDisabled() {
        Client.ENGINE.disable(gameObject());
    }

    /**
     * Destroys the enemy when called, removing it from the game engine.
     */
    @Override
    public void onDestroy() {
        Client.ENGINE.destroy(igameObject);
    }
}