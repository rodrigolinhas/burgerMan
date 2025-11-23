package gameEngine.shape;

import gameEngine.object.IGameObject;
import java.awt.*;

/**
 * Interface that defines the behavior of a shape in the game.
 * A shape is a graphical representation of a GameObject.
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public interface IShape {
    /**
     * Returns the GameObject associated with this shape.
     * 
     * @return The linked GameObject.
     */
    IGameObject gameObject();

    /**
     * Sets a GameObject for a shape.
     * 
     * @param gameObject The new GameObject for the shape in question.
     */
    void gameObject(IGameObject gameObject);

    /**
     * Draws the shape of the object on the screen.
     *
     * @param g Graphic device for rendering.
     */
    void draw(Graphics2D g);
}