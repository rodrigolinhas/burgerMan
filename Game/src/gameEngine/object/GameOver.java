package gameEngine.object;

import collisions.Point;
import gameEngine.Transform;
import gameEngine.behaviour.LivesBehaviour;
import gameEngine.behaviour.ScoreBehaviour;
import gameEngine.shape.TextShape;

import java.awt.*;

/**
 * Responsive class to represent the GameOver GameObject.
 * <p>
 * This class is responsible for displaying game over a message when a player loses the game.
 * <p>
 * It extends the GameObject class and uses a TextShape to render the message.
 *
 * @author Rodrigo Linhas
 * @author Ricardo Rodrigues
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class GameOver extends GameObject {

    /**
     * Constructs a GameOver GameObject that displays a message when a player loses.
     * <p>
     * It uses a white TextShape with font size 44 to render the GAME OVER text.
     */
    public GameOver() {
        super("GameOver", Transform.simpleTransform(new Point(336, 372)), null, null,
                new TextShape("GAME OVER", Color.RED, 44)); //por enquanto deixar assim
        shape.gameObject(this);
    }
}
