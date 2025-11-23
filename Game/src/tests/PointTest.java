package tests;

import collisions.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Math.*;

class PointTest {

    @Test
    void getXTest() {
        Point point = new Point(-1.50, 0.00);
        Assertions.assertEquals(-1.50, point.getX());
    }

    @Test
    void getYTest() {
        Point point = new Point(0.00, 0.00);
        Assertions.assertEquals(0.00, point.getY());
    }

    @Test
    void moveTest() {
        Point point = new Point(0, 0);
        Point point2 = new Point(1, 0);
        point.move(point2);
        Assertions.assertEquals(1, point.getX());
        Assertions.assertEquals(0, point.getY());
    }

    @Test
    void distTest() {
        Point point = new Point(0, 0);
        Point point2 = new Point(-2, -2);
        Assertions.assertEquals(2 * sqrt(2), point.dist(point2));
    }

    @Test
    void testToString() {
        Point point = new Point(0, 0);
        Assertions.assertEquals("(0.00,0.00)", point.toString());
    }

    @Test
    void testEqualsTest() {
        Point point = new Point(0, 0);
        Point point2 = new Point(0, 0);
        Assertions.assertTrue(point.equals(point2));
    }

    @Test
    void testInvalidEquals() {
        Point point = new Point(0, 0);
        Point point2 = new Point(3, 0);
        Assertions.assertFalse(point.equals(point2));
    }
}