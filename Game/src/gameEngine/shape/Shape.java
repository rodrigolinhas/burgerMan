package gameEngine.shape;

import gameEngine.ITransform;
import gameEngine.object.IGameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Abstract class that defines the behavior of a shape in the game.
 * A shape is a graphical representation of a GameObject.
 *
 * This class provides the layout and mechanisms for transforming and rendering
 * objects in a 2D space.
 * 
 * Classes extending Shape must implement the render method.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
abstract class Shape implements IShape {
    IGameObject igameObject;

    /**
     * Constructor that associates a GameObject with this shape.
     *
     * @param igameObject GameObject that owns this shape.
     */
    public Shape(IGameObject igameObject) {
        this.igameObject = igameObject;
    }

    /**
     * Returns the GameObject associated with this shape.
     *
     * @return The linked GameObject.
     */
    @Override
    public IGameObject gameObject() {
        return igameObject;
    }

    /**
     * Sets or replaces the GameObject associated with this shape.
     *
     * @param gameObject New GameObject to be linked with the shape.
     */
    @Override
    public void gameObject(IGameObject gameObject) {
        this.igameObject = gameObject;
    }

    /**
     * Renders the shape of the object on the screen.
     * 
     * This method automatically applies transformations such as position,
     * rotation, and scaling prior to invoking the render method for drawing.
     * 
     * @param g Graphic device for rendering.
     */
    @Override
    public final void draw(Graphics2D g) {
        AffineTransform originalTx = g.getTransform();
        RenderingHints originalHints = g.getRenderingHints();

        ITransform transform = igameObject.transform();
        g.translate(transform.position().getX(), transform.position().getY());
        g.rotate(transform.angle());
        g.scale(transform.scale(), transform.scale());

        this.render(g);

        g.setTransform(originalTx);
        g.setRenderingHints(originalHints);
    }

    /**
     * Abstract method that defines how each shape should be rendered.
     * 
     * Classes that extend Shape must implement this method to customize
     * the rendering process (e.g., drawing or loading sprites).
     *
     * @param g Graphics2D object responsible for rendering.
     */
    public abstract void render(Graphics2D g);
}