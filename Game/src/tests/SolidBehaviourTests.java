package tests;

import collisions.Point;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.behaviour.SolidBehaviour;
import gameEngine.object.GameObject;
import gameEngine.object.Solid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolidBehaviourTests {
    Solid solid = new Solid(new Point(1, 1), 2, 2);
    SolidBehaviour solidBehaviour = new SolidBehaviour(solid);

    @Test
    void testConstructor() {
        assertInstanceOf(SolidBehaviour.class, solidBehaviour);
        assertNotNull(solidBehaviour);
        assertEquals(solid, solidBehaviour.gameObject());
    }

    @Test
    void onUpdate() {
    }

    @Test
    void onCollision() {
    }

    @Test
    void onInit() {
    }

    @Test
    void onEnabled() {
    }

    @Test
    void onDisabled() {
    }

    @Test
    void onDestroy() {
    }
}