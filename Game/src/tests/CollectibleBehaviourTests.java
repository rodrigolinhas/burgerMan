package tests;

import collisions.Point;
import gameEngine.Client;
import gameEngine.GameEngine;
import gameEngine.behaviour.CollectibleBehaviour;
import gameEngine.object.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectibleBehaviourTests {
    public static final GameEngine ENGINE = new GameEngine();
    Collectible c1 = new Collectible(Type.TOMATO, new Point(1, 1));
    CollectibleBehaviour cb1 = new CollectibleBehaviour(c1);

    Collectible c2 = new Collectible(Type.PICKLE, new Point(1, 1));
    CollectibleBehaviour cb2 = new CollectibleBehaviour(c2);



    @Test
    void testConstructor() {
        assertInstanceOf(CollectibleBehaviour.class, cb1);
        assertNotNull(cb1);
        assertEquals(c1, cb1.gameObject());


        assertInstanceOf(CollectibleBehaviour.class, cb2);
        assertNotNull(cb2);
        assertEquals(c2, cb2.gameObject());
    }

    @Test
    void onUpdate() {
        cb1.onUpdate(0.1,null);
        assertEquals(Type.TOMATO, c1.getType());
        cb2.onUpdate(0.1,null);
        assertEquals(Type.PICKLE, c2.getType());
    }

    @Test
    void onCollision() {
        List<IGameObject> gameObjects = new ArrayList<>(List.of());
        Player player = new Player(new Point(1,1));
        Enemy enemy = new Enemy(new Point(2,2),EnemyType.GREEN_TRASH);
        gameObjects.add(player);
        gameObjects.add(enemy);
        cb1.onCollision(gameObjects);
        assertTrue(cb1.isCollected());

        //agr pickle e enemy
        cb2.onCollision(gameObjects);
        assertTrue(cb2.isCollected());
    }

    @Test
    void onInit() {
        cb1.onInit();
        //assertFalse(cb1.isInitialized());
        assertFalse(cb1.isCollected());
        assertSame(Type.TOMATO, c1.getType());


        cb2.onInit();
        //assertFalse(cb2.isInitialized());
        assertFalse(cb2.isCollected());
        assertSame(Type.PICKLE, c2.getType());
    }

    @Test
    void onEnabled() {
    }

    @Test
    void onDisabled() {
    }

    //review this test
    @Test
    void onDestroy() {
        Client.ENGINE.add(c1);
        System.out.println(Client.ENGINE.getLoadedObjects());
        cb1.onDestroy();
        assertFalse(cb1.isCollected());
        System.out.println(Client.ENGINE.getLoadedObjects());
        System.out.println(Client.ENGINE.getEnabled());
        System.out.println(Client.ENGINE.getDisabled());
        assertEquals(2, Client.ENGINE.getLoadedObjects().size());
        assertFalse(Client.ENGINE.getLoadedObjects().isEmpty()); //nao esta vazia pq tem o score e o lives
    }
}