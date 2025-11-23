package gameEngine.object;

import gameEngine.Transform;
import gameEngine.shape.BackGroundShape;
/** This class represents the background of the game.
 * It extends the GameObject class and initializes the background shape.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class BackGround extends GameObject {

    /** Constructor that initializes the background object with its name, transform,
     * collider, and shape.
     */
    public BackGround() {
        super("BackGround", Transform.backgroundTransform(), null, null, new BackGroundShape(null));
        shape.gameObject(this);
    }
}
