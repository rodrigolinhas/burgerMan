package gameEngine.object;

import collisions.Point;
import gameEngine.Transform;
import gameEngine.behaviour.LivesBehaviour;
import gameEngine.shape.TextShape;

import java.awt.*;

/**
 * Responsive class to represent the Lives GameObject.
 * <p>
 * This class is responsible for displaying the number of lives remaining in the game.
 * <p>
 * It extends the GameObject class and uses a TextShape to render the lives count.
 *
 * @author Rodrigo Linhas
 * @author Ricardo Rodrigues
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class Lives extends GameObject {

    /**
     * Constructs a Lives object with a default position and initial lives count.
     * The position is set to (550, 65), and the initial lives count is set to 3.
     */
    public Lives() {
        super("Lives", Transform.simpleTransform(new Point(550, 65)), null, new LivesBehaviour(null),
                new TextShape("Lives: 3", Color.WHITE, 24)); //por enquanto deixar assim
        behaviour.gameObject(this);
        shape.gameObject(this);
        //behaviour.onInit();
    }
}
