package tests;

import collisions.Point;
import gameEngine.behaviour.Behaviour;
import gameEngine.behaviour.CollectibleBehaviour;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.object.Collectible;
import gameEngine.object.Player;
import gameEngine.object.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectibleTests {
    @Test
    void testConstructor() {
        Collectible collectible = new Collectible(Type.TOMATO, new Point(1, 1));
        assertEquals("<Tomato | (1.00,1.00) 0 0.00 1.00 | (1.00,1.00) 0.50>", collectible.toString());
    }
}