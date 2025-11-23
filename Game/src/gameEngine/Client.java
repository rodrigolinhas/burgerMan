package gameEngine;

import gameEngine.gui.GUI;
import gameEngine.gui.Initializer;

import java.util.Random;

public class Client {
    public static final GameEngine ENGINE = new GameEngine();
    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Initializer.initializeGameObjects(ENGINE);
        Initializer.initializeIntersections(ENGINE);
        ENGINE.setGUI(new GUI(ENGINE.getEnabled()));
        ENGINE.run();
    }
}