package tests;

import collisions.Point;
import gameEngine.object.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PlayerTests {

    @Test
    void testConstructor() {
        Player player = new Player(new Point(1, 1));
        assertEquals("<Player | (1.00,1.00) 0 0.00 1.00 | (0.00,0.00) (0.00,2.00) (2.00,2.00) (2.00,0.00)>", player.toString());
    }
}