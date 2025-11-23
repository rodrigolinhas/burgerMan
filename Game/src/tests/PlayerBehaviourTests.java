package tests;

import collisions.Point;
import gameEngine.Client;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.behaviour.ScoreBehaviour;
import gameEngine.object.*;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import java.awt.Component;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBehaviourTests {
    Player p1 = new Player(new Point(1, 1));
    PlayerBehaviour pb = new PlayerBehaviour(p1);

    @Test
    void testConstructor() {
        assertInstanceOf(PlayerBehaviour.class, pb);
        assertNotNull(pb);
        assertEquals(p1, pb.gameObject());
    }

    @Test
    void onUpdate() {
        //teste para ver se o tempo está a passar bem e desconta bem o tempo de invencibilidade
        pb.onInit();
        pb.setInvincible(10);
        pb.onUpdate(1.0, null);
        double remainingTime = pb.getInvincibilityTime();
        assertEquals(remainingTime, pb.getInvincibilityTime(), 1E-9);
        assertTrue(pb.isInvincible());

        pb.onUpdate(9.0, null);
        remainingTime = pb.getInvincibilityTime();
        assertEquals(remainingTime, pb.getInvincibilityTime(), 1E-9);
        assertFalse(pb.isInvincible());

        //teste para ver os movimentos quando estiverem implemenatdos
        pb.onInit();
        pb.gameObject().transform().move(new Point(1, 1), 0);
        KeyEvent keyA = new KeyEvent(new Component(){}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        pb.onUpdate(1.0, keyA);
        assertEquals(1, pb.gameObject().transform().position().getX());
        assertEquals(2, pb.gameObject().transform().position().getY());

        pb.gameObject().transform().move(new Point(1, 1), 0);
        KeyEvent keyD = new KeyEvent(new Component(){}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        pb.onUpdate(1.0, keyD);
        assertEquals(3, pb.gameObject().transform().position().getX());
        assertEquals(3, pb.gameObject().transform().position().getY());

        pb.gameObject().transform().move(new Point(1, 1), 0);
        KeyEvent keyS = new KeyEvent(new Component(){}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        pb.onUpdate(1.0, keyS);
        assertEquals(4, pb.gameObject().transform().position().getX());
        assertEquals(5, pb.gameObject().transform().position().getY());

        pb.gameObject().transform().move(new Point(1, 1), 0);
        KeyEvent keyW = new KeyEvent(new Component(){}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        pb.onUpdate(1.0, keyW);
        assertEquals(5, pb.gameObject().transform().position().getX());
        assertEquals(5, pb.gameObject().transform().position().getY());
    }

    @Test
    void onCollision() {
        pb.gameObject(p1);
        GameObject point = new Collectible(Type.POINT, new Point(2,2));
        assertDoesNotThrow(() -> pb.onCollision(java.util.List.of(point)));
        ScoreBehaviour score = (ScoreBehaviour) Client.ENGINE.getScoreObject().behaviour();
        assertEquals(10, score.getScore());
        assertDoesNotThrow(() -> pb.onCollision(java.util.List.of(point)));
        assertEquals(20, score.getScore());

        GameObject Tomato = new Collectible(Type.TOMATO, new Point(3,3));
        assertDoesNotThrow(() -> pb.onCollision(java.util.List.of(Tomato)));
        assertTrue(pb.isInvincible());
        assertEquals(60, pb.getInvincibilityTime());
        pb.onInit();

        GameObject Onion = new Collectible(Type.ONION, new Point(4,4)); //rever este teste
        GameObject Onion2 = new Collectible(Type.ONION, new Point(4.5,4.5));
        assertDoesNotThrow(() -> pb.onCollision(java.util.List.of(Onion)));
        assertTrue(Client.ENGINE.isDestroyed(Onion2));


        GameObject Pickle = new Collectible(Type.PICKLE, new Point(5,5));
        assertDoesNotThrow(() -> pb.onCollision(java.util.List.of(Pickle)));
        assertTrue(Client.ENGINE.isDestroyed(Pickle));

        GameObject Enemy = new Enemy(new Point(6, 6), EnemyType.GREEN_TRASH);
        assertDoesNotThrow(() -> pb.onCollision(java.util.List.of(Enemy)));
        if (pb.isInvincible()) {
            assertTrue(Client.ENGINE.isDestroyed(Enemy));
            assertFalse(Client.ENGINE.isDestroyed(p1));
        } else {
            assertTrue(Client.ENGINE.isDestroyed(p1));
            assertFalse(Client.ENGINE.isDisabled(Enemy));
        }
    }

    @Test
    void onInit() {
        pb.setInvincible(60);
        pb.onInit();
        assertFalse(pb.isInvincible());
        assertEquals(0.0, pb.getInvincibilityTime());
    }

    @Test
    void onEnabled() {
        pb.setInvincible(60);
        pb.onEnabled();
        assertFalse(pb.isInvincible());
        assertEquals(0.0, pb.getInvincibilityTime());
    }

    @Test
    void onDisabled() {
        Client.ENGINE.add((GameObject) pb.gameObject());
        assertFalse(Client.ENGINE.isDisabled(pb.gameObject()));
        pb.onDisabled();
        assertTrue(Client.ENGINE.isDisabled(pb.gameObject()));
    }

    @Test
    void onDestroy() {
        Client.ENGINE.add((GameObject) pb.gameObject());
        assertFalse(Client.ENGINE.isDestroyed(pb.gameObject()));
        pb.onDestroy();
        assertTrue(Client.ENGINE.isDestroyed(pb.gameObject()));
    }
}