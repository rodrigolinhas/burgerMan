package gameEngine.behaviour;

import collisions.Point;
import gameEngine.object.IGameObject;
import gameEngine.object.Player;
import gameEngine.object.Score;
import gameEngine.shape.TextShape;

import java.awt.event.InputEvent;
import java.util.List;

/**
 * Responsive class to deal with score board behaviors.
 * <p>
 * This class manages the player's scoring system, including updating the score,
 * <p>
 * initializing it and handling relevant interactions.
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 *
 * @version May 11, 2025
 */
public class ScoreBehaviour extends Behaviour {
    private int totalScore;

    /**
     * Constructor that associates a GameObject with this score behavior.
     * 
     * @param score The Score object associated with this behavior.
     */
    public ScoreBehaviour(Score score) {
        super(score);
    }

    /**
     * Updates the state of the GameObject every frame.
     * 
     * @param dT Time since the last frame in seconds (Delta Time).
     * @param ie User input events.
     */
    @Override
    public void onUpdate(double dT, InputEvent ie) {
        // No specific implementation provided for each frame update.
    }

    /**
     * Handles collisions with other GameObjects.
     * 
     * @param gameObjects List of collided objects.
     */
    @Override
    public void onCollision(List<IGameObject> gameObjects) {
        // No specific collision handling implemented here.
    }

    /**
     * Initializes the behavior (called once at the beginning).
     * This method ensures the score starts at zero.
     */
    @Override
    public void onInit() {
        this.totalScore = 0;
    }

    /**
     * Executed when the GameObject is enabled.
     */
    @Override
    public void onEnabled() {
        onInit();
    }

    /**
     * Executed when the GameObject is disabled.
     */
    @Override
    public void onDisabled() {
        // No specific behavior is defined for disabling.
    }

    /**
     * Executed when the GameObject is destroyed.
     */
    @Override
    public void onDestroy() {
        // No specific behavior is defined for destruction.
    }

    /**
     * Increments the player's total score.
     * 
     * @param points The points to be added to the score.
     */
    public void incrementScore(int points) {
        if (points < 0) return; // Prevent negative score increments.
        totalScore += points;
        updateScoreText();
    }

    public void updateScoreText(){
        ((TextShape) gameObject().shape()).setText("Score: " + totalScore);
    }

    /**
     * Returns the player's current score.
     * 
     * @return The total score as an integer.
     */
    public int getScore() { 
        return totalScore; 
    }
}