package collisions;

/**
 * Implementation of a line segment.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 28, 2025
 */
public class LineSegment extends Line {
    /**
     * Constructs a line segment from two points.
     * @param a the first point;
     * @param b the second point.
     * @throws IllegalArgumentException if the 2 points are equal.
     */
    public LineSegment(Point a, Point b) {
        super(a, b);
    }

    /**
     * Returns the length of the segment.
     * @return the length of the segment.
     */
    public double length() {
        return a.dist(b);
    }

    /**
     * {@inheritDoc}
     * @param point the provided point;
     * @param interior if true, the ratio is clamped within 0 and 1.
     * @return {@inheritDoc}
     */
    @Override
    public double closestRatio(Point point, boolean interior) {
        double k = super.closestRatio(point, interior);
        if (!interior) return k;
        if (k > 1) return 1;
        if (k < 0) return 0;
        return k;
    }

    @Override
    public boolean contains(Point point) {
        return super.contains(point)
                && a.getX() <= point.getX() && point.getX() <= b.getX()
                && a.getY() <= point.getY() && point.getY() <= b.getY();
    }

    /**
     * {@inheritDoc}
     * @param segment the segment to check intersection for.
     * @return {@inheritDoc}
     */
    @Override
    public boolean intersects(LineSegment segment) {
        if (!super.intersects(segment)) return false;
        double dx1 = b.getX() - a.getX(), dy1 = b.getY() - a.getY();
        double dx2 = segment.b.getX() - segment.a.getX(), dy2 = segment.b.getY() - segment.a.getY();
        double dx3 = segment.a.getX() - a.getX(), dy3 = segment.a.getY() - a.getY();

        if (dx1 * dy2 == dy1 * dx2) return false;
        double k2 = (dy1 * dx3 - dx1 * dy3) / (dx1 * dy2 - dy1 * dx2);

        if (k2 < 0 || k2 > 1) return false;
        double k1 = dx1 == 0 ? (dy3 + k2 * dy2) / dy1 : (dx3 + k2 * dx2) / dx1;

        return 0 <= k1 && k1 <= 1;
    }
}
