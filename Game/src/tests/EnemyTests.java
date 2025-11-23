package tests;

import collisions.Point;
import gameEngine.behaviour.Behaviour;
import gameEngine.behaviour.EnemyBehavior;
import gameEngine.object.Enemy;
import gameEngine.object.EnemyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTests {

    @Test
    void testConstructor() {
        Enemy enemy = new Enemy(new Point(1, 1), EnemyType.GRAY_TRASH);
        assertEquals("<Enemy | (1.00,1.00) 0 0.00 1.00 | (0.00,0.00) (0.00,2.00) (2.00,2.00) (2.00,0.00)>", enemy.toString());
    }
}