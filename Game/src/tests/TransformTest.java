package tests;

import collisions.Point;
import gameEngine.Transform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransformTest {
    Transform transform = new Transform(new Point(1, 1), 1, 180, 1);

    @Test
    void move()
    {
        transform.move(new Point(10.0, 10.0), 2);
        Assertions.assertEquals(new Point(11, 11), transform.position());
        Assertions.assertEquals(3, transform.layer());
    }

    @Test
    void rotate()
    {
        transform.rotate(190);
        Assertions.assertEquals(10, transform.angle());
    }

    @Test
    void scale()
    {
        transform.scale(3.5);
        Assertions.assertEquals(4.5, transform.scale());
    }

    @Test
    void position()
    {
        Assertions.assertEquals(new Point(1, 1), transform.position());
    }

    @Test
    void layer()
    {
        Assertions.assertEquals(1, transform.layer());
    }

    @Test
    void angle()
    {
        Assertions.assertEquals(180, transform.angle());
    }

    @Test
    void testScale()
    {
        Assertions.assertEquals(1, transform.scale());
    }

    @Test
    void testToString()
    {
        Assertions.assertEquals("(1.00,1.00) 1 180.00 1.00", transform.toString());
    }
}