package gameEngine.object;

import collisions.Point;
import gameEngine.Transform;
import gameEngine.behaviour.ScoreBehaviour;
import gameEngine.shape.TextShape;

import java.awt.*;

/**
 * Responsive class to represent the Score GameObject.
 * <p>
 * This class is responsible for displaying the current score in the game.
 * <p>
 * It extends the GameObject class and uses a TextShape to render the score.
 *
 * @author Rodrigo Linhas
 * @author Ricardo Rodrigues
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class Score extends GameObject {

    /**
     * Constructs a Score GameObject that displays the player's current score.
     * <p>
     * The score is positioned at coordinates (125, 65) on the game screen.
     * <p>
     * It uses a white TextShape with font size 24 to render the score text.
     * <p>
     * The score starts at 0 and is updated through the ScoreBehaviour component.
     */
    public Score() {
        super("Score", Transform.simpleTransform(new Point(125, 65)), null, new ScoreBehaviour(null),
                new TextShape("Score: 0", Color.WHITE, 24)); //por enquanto deixar assim
        behaviour.gameObject(this);
        shape.gameObject(this);
    }
}
