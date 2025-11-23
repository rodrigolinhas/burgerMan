package tests;

import collisions.Circle;
import collisions.Point;
import collisions.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class CircleTest {

    @Test
    void testInvalidCircle()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new Circle(new Point(1, 2), -2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Circle(new Point(1, 2), -200000);
        });
    }

    @Test
    void getRadius()
    {
        Assertions.assertEquals(10, new Circle(new Point(1, 2), 10).getRadius());
    }

    @Test
    void collides()
    {
        Circle c1 = new Circle(new Point(0, 0), 5);
        assertTrue(c1.isColliding(new Circle(new Point(4, 0), 3))); //Here, two circles intersect.
        assertTrue(c1.isColliding(new Circle(new Point(6, 0), 1))); //Here, two circles intersect too.
        assertFalse(c1.isColliding(new Circle(new Point(7, 0), 1))); //Here, this two circles not intersect.

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(-1, 0));
        points.add(new Point(5, 0));
        points.add(new Point(2, 4));
        Polygon p1 = new Polygon(points);
        assertTrue(c1.isColliding(p1)); //the circle and polygon must intersect.

        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(new Point(-3.00, -2));
        points1.add(new Point(3, -2.00));
        points1.add(new Point(3.00, 2.00));
        points1.add(new Point(-3.00, 2.00));
        Polygon p2 = new Polygon(points1);
        p2 = new Polygon(points1);
        assertTrue(c1.isColliding(p2)); //the circle and this polygon must intersect too.

        Circle c2 = new Circle(new Point(10.0, 10.0), 2.00);
        assertFalse(c2.isColliding(p1)); //here´s no intersection.
        assertFalse(c2.isColliding(p2)); //here´s no intersection too.
    }

    @Test
    void move()
    {
        Circle circle = new Circle(new Point(1, 2), 10);
        circle.move(new Point(20, 20));
        Assertions.assertEquals(new Point(21, 22), circle.centroid());
    }

    @Test
    void centroid()
    {
        Circle circle = new Circle(new Point(1, 2), 10);
        Assertions.assertEquals(new Point(1, 2), circle.centroid());
    }

    @Test
    void rotate()
    {
        Circle circle = new Circle(new Point(1, 2), 10);
        circle.rotate(Math.PI / 2);
        Assertions.assertEquals(new Point(1, 2), circle.centroid());
        Assertions.assertEquals(10, circle.getRadius());
    }

    @Test
    void scale()
    {
        Circle circle = new Circle(new Point(1, 2), 10);
        circle.scale(2);
        Assertions.assertEquals(new Point(1, 2), circle.centroid());
        Assertions.assertEquals(20, circle.getRadius());
    }

    @Test
    void scaleInvalid()
    {
        Circle circle = new Circle(new Point(1, 2), 10);
        assertThrows(IllegalArgumentException.class, () -> {
            circle.scale(-2);
        });
    }

    @Test
    void testToString()
    {
        Assertions.assertEquals("(1.00,2.00) 10.00", new Circle(new Point(1, 2), 10).toString());
    }
}