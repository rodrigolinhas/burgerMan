package tests;

import collisions.Circle;
import collisions.Point;
import collisions.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class ColisorTest {

    private Polygon createSamplePolygon() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(4, 0));
        points.add(new Point(2, 3));
        return new Polygon(points);
    }

    private Polygon createSamplePolygon2() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(2, 2));
        points.add(new Point(2, 6));
        points.add(new Point(4, 6));
        points.add(new Point(4, 2));
        return new Polygon(points);
    }

    private Circle createSampleCircle() {
        Point centroid = new Point(0, 0);
        double radius = 5;
        return new Circle(centroid, radius);
    }

    @Test
    void collides_polygon_polygon() {
        Polygon p1 = createSamplePolygon();
        Polygon p2 = createSamplePolygon();
        Assertions.assertTrue(p1.isColliding(p2));
    }

    @Test
    void collides_polygon_circle() {
        Polygon p1 = createSamplePolygon();
        Circle c1 = createSampleCircle();
        Assertions.assertTrue(p1.isColliding(c1));
    }

    @Test
    void collides_circle_circle() {
        Circle c1 = createSampleCircle();
        Circle c2 = createSampleCircle();
        Assertions.assertTrue(c1.isColliding(c2));
    }


    @Test
    void moveTest()
    {
        Polygon p = createSamplePolygon2();
        Point point = new Point(1, 1);
        p.move(point);
        Assertions.assertEquals(3, p.getPoints().get(0).getX());
        Assertions.assertEquals(3, p.getPoints().get(0).getY());
        Assertions.assertEquals(3, p.getPoints().get(1).getX());
        Assertions.assertEquals(7, p.getPoints().get(1).getY());
        Assertions.assertEquals(5, p.getPoints().get(2).getX());
        Assertions.assertEquals(7, p.getPoints().get(2).getY());
        Assertions.assertEquals(5, p.getPoints().get(3).getX());
        Assertions.assertEquals(3, p.getPoints().get(3).getY());
    }

    @Test
    void testToString() {
        Polygon p1 = createSamplePolygon();
        Assertions.assertEquals("(0.00,0.00) (4.00,0.00) (2.00,3.00)", p1.toString());
    }
}