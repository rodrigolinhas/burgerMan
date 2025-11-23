package tests;

import collisions.Point;
import gameEngine.behaviour.IBehaviour;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.object.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BehaviourTests {

    @Test
    void gameObject() {
        Player player = new Player(new Point(1, 1)); //cria um objeto do tipo ‘Player’ e não associa a nenhum comportamento (null), sendo primeiro criado o comportamento e depois é que se associa
        IBehaviour behaviour = new PlayerBehaviour(player); //aqui já associa ao comportamento
        assertEquals(player, behaviour.gameObject());

        Player player2 = new Player(new Point(1, 2));
        IBehaviour behaviour2 = new PlayerBehaviour(player2);
        assertEquals(player2, behaviour2.gameObject());
    }

    @Test
    void testGameObject() {
        Player player = new Player(new Point(1, 1));
        IBehaviour behaviour = new PlayerBehaviour(player);
        assertEquals(player, behaviour.gameObject());
        Player player2 = new Player(new Point(1, 2));
        behaviour.gameObject(player2);
        assertEquals(player2, behaviour.gameObject());
        Player player3 = new Player(new Point(1, 3));
        behaviour.gameObject(player3);
        assertEquals(player3, behaviour.gameObject());
    }
}