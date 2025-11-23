package gameEngine.object;

import collisions.Point;
import gameEngine.ICollider;
import gameEngine.shape.IShape;
import gameEngine.ITransform;
import gameEngine.behaviour.IBehaviour;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents GameObjects in 2D.
 * <p>
 * It implements the IGameObject and encapsulates the object's name,
 * its transform (layer,position, angle, scale) and its collider.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 27, 2025
 */
public class GameObject implements IGameObject
{
    private final String name;
    private final ITransform transform;
    private final @Nullable ICollider collider;
    protected final IBehaviour behaviour;
    protected IShape shape;

    /**
     * Constructor to class GameObject, create an instance of GameObject (see description of class).
     * @param name
     * @param transform
     * @param collider
     */
    public GameObject(String name, ITransform transform, @Nullable ICollider collider, @Nullable IBehaviour behaviour, IShape shape)
    {
        this.name = name;
        this.transform = transform;
        this.collider = collider;
        this.behaviour = behaviour;
        this.shape = shape;
        if (this.behaviour != null)
            this.behaviour.gameObject(this); //se nao for null associa este objeto ao behaviour
    }

    /**
     * Returns the name of the GameObject.
     * @return String with the name of the GameObject
     */
    @Override
    public String name() { return name; }

    /**
     * Response method to get the transform associates with this GameObject
     * The transform contains all the transformation info, including position, layer, rotation angle, and scale factor.
     * @return an instance of ITransform that represents this GameObject's transform.
     */
    @Override
    public ITransform transform() { return transform; }

    /**
     * Obtain the collider associate with this GameObject.
     * @return an instance of ICollider that represents this GameObject's collider.
     */
    @Override
    public @Nullable ICollider collider() { return collider; }

    /**
     * Obtain the behaviour associate with this GameObject.
     * @return an instance of IBehaviour that represents this GameObject's behaviour.
     */
    @Override
    public @Nullable IBehaviour behaviour() {
        return behaviour;
    }

    /**
     * Obtain the shape associate with this GameObject.
     * @return an instance of IShape that represents this GameObject's shape.
     */
    @Override
    public IShape shape() {
        return shape;
    }

    /**
     * Move the gameObject and the collider
     * @param point to move
     * @param layer to move
     */
    public void move(Point point, int layer) {
        transform.move(point, layer);
        if (collider != null)
            collider.move(point);
    }

    /**
     * Rotate the gameObject and the collider
     * @param dTheta to rotate
     */
    public void rotate(double dTheta) {
        transform.rotate(dTheta);
        if (collider != null)
            collider.rotate(dTheta);
    }

    /**
     * Scale the gameObject and the collider
     * @param dScale to scale
     */
    public void scale(double dScale) {
        transform.scale(dScale);
        if (collider != null)
            collider.scale(dScale);
    }

    /**
     * Move the gameObject to a specific point
     * @param point to calculate the difference between the actual position to this point
     * @param layer to move
     */
    public void moveTo(Point point, int layer) {
        Point delta = new Point(point.getX() - transform.position().getX(), point.getY() - transform.position().getY());
        System.out.println(delta);
        move(delta, layer);
    }

    /**
     * Give the GameObject info in a string
     * @return String with info of a GameObject
     */
    @Override
    public String toString() {
        return "<" + name + " | " + transform + " | " + collider + ">";
    }
}
