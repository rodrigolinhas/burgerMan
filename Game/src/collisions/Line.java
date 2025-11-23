package collisions;

/**
 * Implementation of an infinite line.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 28, 2025
 */

public class Line {
    protected Point a, b;

    /**
     * Constructs a line passing through 2 given points.
     * @param a the first point;
     * @param b the second point.
     * @throws IllegalArgumentException if the 2 points are equal.
     */
    public Line(Point a, Point b)
    {
        if (a.equals(b)) throw new IllegalArgumentException();
        this.a = a;
        this.b = b;
    }

    /**
     * Constructs a line passing through a point perpendicular to another line.
     * @param a the point;
     * @param l the line the new line should be perpendicular to.
     * @throws IllegalArgumentException if the 2 points are equal.
     */
    public Line(Point a, Line l)
    {
        this.a = a;
        Point c = l.closest(a, false);
        b = c != a ? c : new Point(a.getX() + l.b.getY() - l.a.getY(), a.getY() + l.a.getX() - l.b.getX());
    }

    /**
     * Getter from the first point.
     * @return first point defining the line.
     */
    public Point getA()
    {
        return a;
    }

    /**
     * Getter from the second point.
     * @return second point defining the line.
     */
    public Point getB()
    {
        return b;
    }

    /**
     * Method to check if the point is in the given line.
     * @param point the point to check;
     * @return true if in fact is the given line;
     *         false otherwise.
     */
    public boolean contains(Point point) {
        return (point.getX() - a.getX()) * (b.getY() - a.getY()) == (point.getY() - a.getY()) * (b.getX() - a.getX());
    }

    /**
     * Method to find the ratio between the points
     * of this line such that the point on that ratio is the
     * closest to the provided point.
     * @param point the provided point;
     * @param interior if true, the ratio is clamped within 0 and 1. (Only useful for {@link LineSegment}).
     * @return the ratio.
     */
    protected double closestRatio(Point point, boolean interior)
    {
        double dx = b.getX() - a.getX(), dy = b.getY() - a.getY();
        return (dx * (point.getX() - a.getX()) + dy * (point.getY() - a.getY())) / (dx * dx + dy * dy);
    }

    /**
     * Finds the closest point to the provided point on the line.
     * @param point the provided point.
     * @return the closest point on the line.
     */
    public Point closest(Point point)
    {
        return closest(point, true);
    }

    /**
     * Finds the closest point to the provided point on the line.
     * @param point the provided point.
     * @param interior if true, the point will actually lie on the line;
     *                 <p>if false, the point can also lie on an imaginary
     *                 infinite line containing this line. (Only useful for {@link LineSegment}).
     * @return
     */
    public Point closest(Point point, boolean interior)
    {
        double dx = b.getX() - a.getX(), dy = b.getY() - a.getY(), k = closestRatio(point, interior);
        return new Point(a.getX() + k * dx, a.getY() + k * dy);
    }

    /**
     * Checks whether this line intersects a circle.
     * @param circle the circle to check intersection for.
     * @return true - if the objects intersect.
     */
    public boolean intersects(Circle circle)
    {
        return closest(circle.centroid()).dist(circle.centroid()) <= circle.getRadius();
    }

    /**
     * Checks whether this line intersects a polygon.
     * @param polygon the polygon to check intersection for.
     * @return true - if the objects intersect.
     */
    public boolean intersects(Polygon polygon)
    {
        for (LineSegment segment : polygon.getSegments()) if (intersects(segment)) return true;
        return false;
    }

    /**
     * Checks whether this line intersects a line segment.
     * @param segment the segment to check intersection for.
     * @return true - if the objects intersect.
     */
    public boolean intersects(LineSegment segment)
    {
        double dx = b.getX() - a.getX(), dy = b.getY() - a.getY();
        double dz = b.getX() * a.getY() - b.getY() * a.getX();
        Point a = segment.getA(), b = segment.getB();
        return (dx * a.getY() - dy * a.getX() - dz) * (dx * b.getY() - dy * b.getX() - dz) <= 0;
    }
}
