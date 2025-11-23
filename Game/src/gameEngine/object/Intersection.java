package gameEngine.object;

import collisions.Circle;
import collisions.Point;
import gameEngine.Transform;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an intersection in the game where multiple paths converge.
 * <p>
 * This class extends GameObject and provides functionality to manage
 * directions and player interactions at the intersection.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class Intersection extends GameObject {
    private final List<Point> directions, normalized;
    private final Point returnDir;
    private @Nullable Point metPlayer;

    /**
     * Constructs an Intersection object with a base point and a list of directions.
     *
     * @param base       the base point of the intersection
     * @param directions a list of directions available at this intersection
     * @throws IllegalArgumentException if the directions list is empty
     */
    public Intersection(Point base, List<Point> directions) {
        super("Inter", Transform.simpleTransform(base), new Circle(base, 0.1), null, null);
        if (directions.isEmpty()) throw new IllegalArgumentException();
        this.directions = new ArrayList<>(directions);
        normalized = new ArrayList<>(directions);
        for (Point point : directions) {
            this.directions.add(point.scaleOrigin(0.5));
            //this.directions.add(point.scaleOrigin(0.8));
            //this.directions.add(point.scaleOrigin(1.2));
            this.directions.add(point.scaleOrigin(2));
        }
        returnDir = directions.getFirst();
        metPlayer = null;
    }

    public Intersection(Point base, List<Point> directions, Point returnDir) {
        super("Inter", Transform.simpleTransform(base), new Circle(base, 0.1), null, null);
        this.directions = new ArrayList<>(directions);
        normalized = new ArrayList<>(directions);
        for (Point point : directions) {
            this.directions.add(point.scaleOrigin(0.5));
            this.directions.add(point.scaleOrigin(0.8));
            this.directions.add(point.scaleOrigin(1.2));
            this.directions.add(point.scaleOrigin(2));
        }
        this.returnDir = returnDir;
        metPlayer = null;
    }


    /**
     * Returns the list of directions available at this intersection.
     *
     * @return a list of Point objects representing the directions
     */
    public List<Point> list() {
        return directions;
    }

    public List<Point> getNormalized() {
        return normalized;
    }

    /**
     * Returns a random direction from the available directions, excluding the specified direction.
     *
     * @param random the Random instance to use for selecting a direction
     * @param except the direction to exclude from selection
     * @return a random Point representing a direction
     */
    public Point randomDir(Random random, Point except) {
        List<Point> filter = normalized.stream()
                .filter(p -> !p.equals(except.scaleOrigin(-1)))
                .toList();
        return filter.get(random.nextInt(filter.size()));
    }

    /**
     * Returns the return direction of the intersection.
     *
     * @return a Point representing the return direction
     */
    public Point getReturnDir() {
        return returnDir;
    }

    /**
     * Sets the last direction the player came from.
     *
     * @param point the Point representing the last direction
     */
    public void setLastPlayerDir(Point point) {
        metPlayer = point;
    }

    /**
     * Returns the last direction the player came from.
     *
     * @return a Point representing the last direction, or null if not set
     */
    public @Nullable Point getLastPlayerDir() {
        return metPlayer;
    }
}
