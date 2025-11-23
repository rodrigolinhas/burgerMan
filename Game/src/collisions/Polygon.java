package collisions;

import gameEngine.ICollider;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a polygon which contains 3 or more points
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 27, 2025
 */

public class Polygon extends Colisor {

    private ArrayList<Point> points;
    private ArrayList<LineSegment> segments;
    private Point centroid;

    /**
     * Constructs a polygon from a list of points
     * @param points the list of vertices of the polygon
     * @throws IllegalArgumentException if the provided point amount is less than 3,
     * or if three consecutive points are collinear
     */
    public Polygon(ArrayList<Point> points)
    {
        int size = points.size();
        if (size < 3) throw new IllegalArgumentException();
        this.points = points;
        this.segments = new ArrayList<>();

        // Criar segmentos conectando os pontos
        for (int i = 0; i < points.size(); i++) {
            segments.add(new LineSegment(points.get(i), points.get((i + 1) % points.size())));
        }
        if (size == 3)
        {
            if (new Line(points.getFirst(), points.get(1)).contains(points.getLast())) throw new IllegalArgumentException();
        }
        else
        {
            for (int i = 0; i < points.size(); i++)
            {
                if (new Line(points.get(i), points.get((i + 1) % size)).contains(points.get((i + 2) % size))) throw new IllegalArgumentException();
            }
        }

        centroid = centroidCalculate();
    }

    /**
     * This method calculates de centroid of a polygon
     * @return collisions.Point that is the centroid
     */
    private Point centroidCalculate()
    {
        double area = 0, cx = 0, cy = 0;
        int n = points.size();
        for (int i = 0; i < n; i++)
        {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n);
            double cross = p1.getX() * p2.getY() - p2.getX() * p1.getY();
            area += cross;
            cx += (p1.getX() + p2.getX()) * cross;
            cy += (p1.getY() + p2.getY()) * cross;
        }

        area /= 2;
        cx /= (6 * area);
        cy /= (6 * area);
        return new Point(cx, cy);
    }

    /**
     * Getter points
     * @return points
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Getter segments
     * @return segments
     *
     */
    public ArrayList<LineSegment> getSegments() {
        return segments;
    }

    /**
     * Method to check contains a certain point
     * @param point
     * @return true if contains
     *         false otherwise
     */
    @Override
    public boolean contains(Point point) {
        Line line;
        for (LineSegment seg : segments) {
            if (seg.contains(point)) return true;
            line = new Line(point, seg);
            boolean b = false;
            for (LineSegment other : segments) if (line.intersects(other)) {
                b = true;
                break;
            }
            if (!b) return false;
        }
        return true;
    }

    /**
     * Simple method to check if collide with another collider
     * @param other ICollider
     * @return boolean depending on the situation
     *         true if it happens
     *         false otherwise
     */
    @Override
    public boolean isColliding(ICollider other) {
        return other != null && other.isColliding(this);
    }

    /**
     * Checks if this polygon collides with a circle
     *
     * @param circle the circle to check collision with
     * @return true if the polygon collides with the circle, false otherwise
     */
    @Override
    public boolean isColliding(Circle circle) {
        return circle.isColliding(this);
    }

    /**
     * Checks if this polygon collides with another polygon by checking if any points
     * are contained within the other polygon or if any line segments intersect
     *
     * @param polygon the other polygon to check collision with
     * @return true if the polygons collide, false otherwise
     */
    @Override
    public boolean isColliding(Polygon polygon) {
        for (Point p : this.points) {
            if (polygon.contains(p)) return true;
        }
        for (LineSegment s : this.segments) {
            for (LineSegment ss : polygon.segments) {
                if (s.intersects(ss)) return true;
            }
        }
        return false;
    }

    /**
     * Method to move a certain point
     * @param point
     */
    @Override
    public void move(Point point)
    {
        for (Point p : points) p.move(point);
        centroid.move(point);
    }

    /**
     * Method to calculate the centroid
     * @return centroid point
     */
    @Override
    public Point centroid() {
        return centroid;
    }

    /**
     * Method to rotate a certain angle
     * @param angle
     */
    @Override
    public void rotate(double angle) {
        Point centroid = centroid();
        for (Point p : points) {
            p.rotate(angle, centroid);
        }
    }

    /**
     * Method to scale the polygon
     * @param scale
     */
    @Override
    public void scale(double scale) {
        if (scale <= 0) throw new IllegalArgumentException();
        double cx = centroid.getX();
        double cy = centroid.getY();
        for (Point p : points) {
            double dx = (p.getX() - cx) * (scale - 1);
            double dy = (p.getY() - cy) * (scale - 1);
            p.move(new Point(dx, dy));
        }
    }

    /**
     * Method to scale the polygon
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) {
            sb.append(p.toString()).append(" ");
        }
        return sb.toString().trim();
    }

    public static Polygon simpleSquare(Point center, int rad) {
        return new Polygon(new ArrayList<>(List.of(
                new Point(center.getX() - rad, center.getY() - rad),
                new Point(center.getX() - rad, center.getY() + rad),
                new Point(center.getX() + rad, center.getY() + rad),
                new Point(center.getX() + rad, center.getY() - rad)
        )));
    }

    public static Polygon simpleRectangle(Point topLeft, double horizontal, double vertical) {
        return new Polygon(new ArrayList<>(List.of(
                new Point(topLeft.getX(), topLeft.getY()),
                new Point(topLeft.getX() + horizontal, topLeft.getY()),
                new Point(topLeft.getX() + horizontal, topLeft.getY() + vertical),
                new Point(topLeft.getX(), topLeft.getY() + vertical)
        )));
    }
}