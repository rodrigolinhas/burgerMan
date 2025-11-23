package gameEngine.gui;

import gameEngine.Client;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.object.IGameObject;
import gameEngine.shape.IShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents the GUI of the game Burger Man
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 17, 2025
 */
public class GUI extends JFrame implements IGUI {
    public final CopyOnWriteArrayList<InputEvent> queue;
    private final GamePanel gamePanel;

    /**
     * Constructor of the GUI class.
     * Initializes the input event queue using CopyOnWriteArrayList
     * to ensure thread safety in concurrent environments.
     */
    public GUI(List<IGameObject> gameObjects) {
        super("Burger-Man");
        queue = new CopyOnWriteArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(672, 744);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        gamePanel = new GamePanel(gameObjects);
        setContentPane(gamePanel);
        // setBackground(Color.BLACK);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/burgerUp.png"))).getImage());
        setVisible(true);

        addKeyListener();
    }

    /**
     * Refreshes the game panel by repainting it.
     * <p>
     * This method can be called to update the display of the game objects.
     */
    public void refresh() {
        gamePanel.repaint();
    }

    /**
     * Displays the game objects in the GUI.
     * <p>
     * This method is called to render the game objects on the panel.
     */
    @Override
    public @Nullable InputEvent dequeue() {
        try {
            InputEvent ie = queue.removeFirst();
            // System.out.println(ie);
            return ie;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Adds a key listener to the GUI to handle key events.
     */
    private void addKeyListener() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println(e);
                // System.out.println(((PlayerBehaviour)
                // Client.ENGINE.getPlayerObject().behaviour()).speed);
                queue.addLast(e);
                // repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
