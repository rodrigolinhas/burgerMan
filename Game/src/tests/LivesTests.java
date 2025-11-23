package tests;

import gameEngine.object.Lives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivesTests {

    @Test
    void testConstructor() {
        Lives lives = new Lives();
        assertEquals("<Lives | (0.00,0.00) 0 0.00 1.00 | null>", lives.toString());
    }
}