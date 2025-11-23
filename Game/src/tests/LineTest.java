package tests;

import collisions.Circle;
import collisions.Line;
import collisions.Point;
import collisions.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    static Line line = new Line(new Point(0, 3), new Point(2, 2));

    @Test
    void testInvalidLine() {
        assertThrows(IllegalArgumentException.class, () -> new Line(new Point(4, 4), new Point(4, 4)));
    }

    @Test
    void testContains() {
        Assertions.assertTrue(line.contains(new Point(4, 1)));
        Assertions.assertFalse(line.contains(new Point(-2, 5)));
    }

    @Test
    void testPerpendicular() {
        Line other = new Line(new Point(1, 1), line);
        System.out.println(other.getA());
        System.out.println(other.getB());
        Assertions.assertTrue(other.contains(new Point(2, 3)));
        Line another = new Line(new Point(4, 1), line);
        Assertions.assertTrue(another.contains(new Point(5, 3)));
    }

    @Test
    void testClosest() {
        Point point = line.closest(new Point(2, 4.5));
        Assertions.assertEquals(point.getX(), 1);
        Assertions.assertEquals(point.getY(), 2.5);
    }

    @Test
    void testIntersections() {
        Assertions.assertTrue(line.intersects(new Circle(new Point(2, 0), 2)));
        Assertions.assertFalse(line.intersects(new Circle(new Point(4, 3), 1)));
        Assertions.assertTrue(line.intersects(new Polygon(new ArrayList<>(List.of(
                new Point(3, 1.5),
                new Point(3, 2.5),
                new Point(4, 1.5)
        )))));
        Assertions.assertTrue(line.intersects(new Polygon(new ArrayList<>(List.of(
                new Point(-2, 5),
                new Point(-1.5, 3),
                new Point(-1, 5)
        )))));
    }
}