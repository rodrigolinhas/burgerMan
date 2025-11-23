package tests;

import collisions.Point;
import gameEngine.Client;
import gameEngine.GameEngine;
import gameEngine.ICollider;
import gameEngine.Transform;
import gameEngine.behaviour.EnemyBehavior;
import gameEngine.object.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTests {
    GameEngine engine = new GameEngine();
    Player player = new Player(new Point(1, 1));
    Random random = new Random();

    @Test
    void add() {
        engine.add(player);
        engine.add(new Enemy(new Point(5, 5),EnemyType.GREEN_TRASH));
        engine.add(new Collectible(Type.POINT, new Point(1, 1)));
        assertEquals(5, engine.getLoadedObjects().size());
    }

    @Test
    void destroy() {
        Collectible collectible;
        engine.add(collectible = new Collectible(Type.POINT, new Point(0, 0)));
        assertFalse(engine.getLoadedObjects().isEmpty());
        engine.destroy(collectible);
        System.out.println(engine.getLoadedObjects());
        assertEquals(2, engine.getLoadedObjects().size());
    }

    @Test
    void getLoadedObjects() {
        engine.add(player);
        for (int i = 0; i < 4; i++)
            engine.add(new Enemy(new Point(random.nextDouble(), random.nextDouble()), EnemyType.GRAY_TRASH));
        ArrayList<GameObject> list = new ArrayList<>();
        for (GameObject g : engine.getLoadedObjects())
            if (g instanceof Enemy)
                list.add(g);
        for (GameObject g : list)
            engine.destroy(g);
        assertEquals(3, engine.getLoadedObjects().size());
    }

    @Test
    void addEnabled() {
        IGameObject obj = new Player(new Point(1,1));
        engine.addEnabled(obj);
        assertTrue(engine.getEnabled().contains(obj));
        assertEquals(3, engine.getEnabled().size());

    }

    @Test
    void addDisabled() {
        IGameObject obj = new GameObject("Obj", Transform.simpleTransform(new Point(0,0)), null, null, null);
        engine.addDisabled(obj);
        assertTrue(engine.getDisabled().contains(obj));
        assertEquals(1, engine.getDisabled().size());

    }

    @Test
    void enable() {
        IGameObject obj = new GameObject("Obj", Transform.simpleTransform(new Point(0,0)), null, null, null);
        engine.addDisabled(obj);
        engine.enable(obj);
        assertTrue(engine.isEnabled(obj));

    }

    @Test
    void disable() {
        IGameObject obj = new Player(new Point(1,1));
        engine.addEnabled(obj);
        engine.disable(obj);
        assertTrue(engine.isDisabled(obj));

    }

    @Test
    void isEnabled() {
        IGameObject obj = new Player(new Point(1,1));
        engine.addEnabled(obj);
        assertTrue(engine.isEnabled(obj));
        assertFalse(engine.isDisabled(obj));

    }

    @Test
    void isDisabled() {
        IGameObject obj = new Player(new Point(1,1));
        engine.addDisabled(obj);
        assertTrue(engine.isDisabled(obj));
        assertFalse(engine.isEnabled(obj));

    }

    @Test
    void getEnabled() {
        IGameObject obj = new Player(new Point(1,1));
        IGameObject obj2 = new Player(new Point(1,1));
        engine.addEnabled(obj);
        engine.addEnabled(obj2);
        List<IGameObject> enabled = engine.getEnabled();
        assertEquals(4, enabled.size());
        assertTrue(enabled.contains(obj));
        assertTrue(enabled.contains(obj2));

    }

    @Test
    void getDisabled() {
        IGameObject obj = new GameObject("Obj", Transform.simpleTransform(new Point(0,0)), null, null, null);
        IGameObject obj2 = new GameObject("Obj2", Transform.simpleTransform(new Point(1,1)), null, null, null);

        engine.addDisabled(obj);
        engine.addDisabled(obj2);

        List<IGameObject> disabled = engine.getDisabled();
        assertEquals(2, disabled.size());
        assertTrue(disabled.contains(obj));
        assertTrue(disabled.contains(obj2));
    }

    @Test
    void testDestroy() {
        engine.add(player);
        assertNotEquals(2, engine.getLoadedObjects().size());
        engine.destroy(player);
        assertEquals(2, engine.getLoadedObjects().size());
    }

    @Test
    void destroyAll() {
        for (int i = 0; i < 100; i++)
            engine.add(player);
        assertFalse(engine.getLoadedObjects().isEmpty());
        engine.destroyAll();
        assertTrue(engine.getLoadedObjects().isEmpty());
    }

    @Test
    void run() {
        Enemy e1 = new Enemy(new Point(1, 1), EnemyType.GREEN_TRASH);
        EnemyBehavior eb = new EnemyBehavior(e1);
        eb.gameObject(e1);
        Client.ENGINE.add(new Solid(new Point(0, 0), 0.5, 2));
        Client.ENGINE.add(new Solid(new Point(0, 0), 100, 0.5));
        Client.ENGINE.add(new Solid(new Point(0, 1.5), 100, 0.5));
        Client.ENGINE.add(e1);
        eb.onInit();
        Client.ENGINE.run();
        //noinspection DataFlowIssue
        assertTrue(e1.collider().centroid().getX() >= 12);
    }

    @Test
    void checkCollisions() {
        IGameObject obj = new Player(new Point(1,1));
        IGameObject obj2 = new Player(new Point(1,1));
        engine.addEnabled(obj);
        engine.addEnabled(obj2);
        engine.checkCollisions();
        //MUITO ABSTRATO MAS A IDEIA E ALGO DESTE GENERO
    }

    @Test
    void randomObject() {
        engine.add(player);
        List<IGameObject> list = List.of(player, engine.getScoreObject(), engine.getLivesObject());
        assertTrue(list.contains(engine.randomObject(_ -> true)));
    }
}