package tests;

import collisions.Point;
import gameEngine.Client;
import gameEngine.behaviour.EnemyBehavior;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.object.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemyBehaviorTests {
    Enemy e1 = new Enemy(new Point(1, 1),EnemyType.GRAY_TRASH);
    EnemyBehavior eb = new EnemyBehavior(e1);

    @Test
    void testConstructor() {
        assertInstanceOf(EnemyBehavior.class, eb);
        assertNotNull(eb);
        assertEquals(e1, eb.gameObject());
        assertEquals(EnemyType.GRAY_TRASH, e1.getEnemytype());
    }

    @Test
    void onUpdate() {
        eb.gameObject(e1);
        Client.ENGINE.add(new Solid(new Point(0, 0), 0.5, 2));
        eb.onUpdate(1.0, null);
        //noinspection DataFlowIssue
        assertTrue(e1.collider().centroid().getX() >= 1);
    }

    // COLLISION
    @Test
    void onCollision() {
        Player player = new Player(new Point(10, 10));
        Client.ENGINE.add(player);
        eb.onCollision(List.of(new Collectible(Type.PICKLE, new Point(1, 1))));
        assertEquals(((PlayerBehaviour) player.behaviour()).getPlayerSpeed(), 0.8);
    }

    @Test
    void onInit() {
        eb.gameObject(e1);
        Solid solid = new Solid(new Point(0, 0), 0.5, 2);
        Client.ENGINE.add(solid);
        eb.onInit();
        //noinspection DataFlowIssue
        assertTrue(e1.collider().centroid().getX() >= 1);
    }

    @Test
    void onEnabled() {
        Client.ENGINE.add((GameObject) eb.gameObject());
        assertFalse(Client.ENGINE.isDisabled(eb.gameObject()));
        assertTrue(Client.ENGINE.isEnabled(eb.gameObject()));
        eb.onDisabled();
        assertTrue(Client.ENGINE.isDisabled(eb.gameObject()));
        assertFalse(Client.ENGINE.isEnabled(eb.gameObject()));
        eb.onEnabled();
        assertFalse(Client.ENGINE.isDisabled(eb.gameObject()));
        assertTrue(Client.ENGINE.isEnabled(eb.gameObject()));
    }

    @Test
    void onDisabled() {
        Client.ENGINE.add((GameObject) eb.gameObject());
        assertFalse(Client.ENGINE.isDisabled(eb.gameObject()));
        eb.onDisabled();
        assertTrue(Client.ENGINE.isDisabled(eb.gameObject()));
    }

    @Test
    void onDestroy() {
        Client.ENGINE.add((GameObject) eb.gameObject());
        assertFalse(Client.ENGINE.isDestroyed(eb.gameObject()));
        eb.onDestroy();
        assertTrue(Client.ENGINE.isDestroyed(eb.gameObject()));
    }
}