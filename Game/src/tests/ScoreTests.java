package tests;

import gameEngine.object.Score;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTests {

    @Test
    void testConstructor() {
        Score score = new Score();
        assertEquals("<Score | (0.00,1.00) 0 0.00 1.00 | null>", score.toString());
    }
}