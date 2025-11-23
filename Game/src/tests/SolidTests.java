package tests;

import collisions.Point;
import gameEngine.object.Solid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolidTests {
    @Test
    void testConstructor() {
        Solid wall = new Solid(new Point(1, 1), 2, 2);
        assertEquals("<Solid | (1.00,1.00) 0 0.00 1.00 | (1.00,1.00) (3.00,1.00) (3.00,3.00) (1.00,3.00)>", wall.toString());
    }
}