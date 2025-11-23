package gameEngine.behaviour;

import gameEngine.Client;
import gameEngine.object.GameOver;
import gameEngine.object.IGameObject;
import gameEngine.object.Lives;
import gameEngine.shape.TextShape;

import java.awt.event.InputEvent;
import java.util.List;

/**
 * A responsive class to deal with the behavior of the lives board.
 * This class manages the player's lives and their display during gameplay.
 * <p>
 * Responsible for updating, decrementing, and initializing the number of lives.
 * 
 * @authors Ricardo Rodrigues,
 * @authors Ricardo Rodrigues,
 * @authors Ricardo Rodrigues,
 * @version May 11, 2025
 */
public class LivesBehaviour extends Behaviour {
    private int lives;

    /**
     * Constructor that associates a GameObject with this behavior.
     *
     * @param lives The lives object to be controlled by this behavior.
     */
    public LivesBehaviour(Lives lives) {
        super(lives);
    }

    /**
     * Retrieves the current number of lives.
     *
     * @return An integer representing the current number of lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Updates the state of the GameObject every frame.
     *
     * @param dT Time since the last frame in seconds.
     * @param ie User input events.
     */
    @Override
    public void onUpdate(double dT, InputEvent ie) {
        // No additional implementation for each frame.
    }

    /**
     * Handles collisions with other GameObjects.
     *
     * @param gameObjects A list of collided objects.
     */
    @Override
    public void onCollision(List<IGameObject> gameObjects) {
        // No additional collision behavior defined here.
    }

    /**
     * Initializes the behavior (called once at the beginning).
     * This sets the default number of lives to 3.
     */
    @Override
    public void onInit() {
        lives = 3;
    }

    /**
     * Decreases the number of lives by 1.
     * Prevents decrementing if lives are already at or below zero.
     */
    public void decreaseLives() {
        if (lives > 0) {
            lives--;
            updateLivesText();
        }
        if (lives == 0) {
            Client.ENGINE.add(new GameOver());
            Client.ENGINE.pauseGame();
        }
    }

    public void updateLivesText() {
        ((TextShape) gameObject().shape()).setText("Lives:" + lives);
    }

    /**
     * Executes when the GameObject is enabled.
     */
    @Override
    public void onEnabled() {
        onInit();
    }

    /**
     * Executes when the GameObject is disabled.
     */
    @Override
    public void onDisabled() {
        // No specific actions on disabling.
    }

    /**
     * Executes when the GameObject is destroyed.
     */
    @Override
    public void onDestroy() {
        // No specific actions on destruction.
    }
}