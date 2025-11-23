package gameEngine.gui;

import gameEngine.object.IGameObject;
import gameEngine.shape.IShape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * GamePanel is a JPanel that renders game objects based on their layers.
 * It sorts the game objects by their layer and draws them accordingly.
 * Each game object must implement IGameObject and provide a shape to be drawn.
 * The panel has a black background to represent layer -2.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @verison May 24, 2025
 */
public class GamePanel extends JPanel {
    private final List<IGameObject> gameObjects;

    /**
     * Constructs a GamePanel with the specified list of game objects.
     *
     * @param gameObjects List of game objects to be rendered in the panel.
     */
    public GamePanel(List<IGameObject> gameObjects) {
        this.gameObjects = gameObjects;
        setBackground(Color.BLACK); // Fundo preto para layer -2
    }

    /**
     * Updates the game objects in the panel.
     * This method can be called to refresh the list of game objects.
     *
     * @param g Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenhar os objetos por layer
        gameObjects.stream()
                .sorted((o1, o2) -> Integer.compare(o1.transform().layer(), o2.transform().layer()))
                .forEach(go -> {
                    IShape shape = go.shape();
                    if (shape != null) {
                        shape.draw(g2d);
                    }
                });
    }
}