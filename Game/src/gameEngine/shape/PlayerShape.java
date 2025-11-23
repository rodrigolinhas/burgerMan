package gameEngine.shape;

import gameEngine.object.Player;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents the shape of a player object in the game.
 * It extends the Shape class and renders the player image based on its direction.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class PlayerShape extends Shape {
    private final Map<String, BufferedImage> images = new HashMap<>();
    private String currentDirection = "UP";

    /**
     * Constructor that initializes the PlayerShape with a specific Player object.
     *
     * @param player The Player object associated with this shape
     */
    public PlayerShape(Player player) {
        super(player);
        try {
            images.put("UP", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/burgerUp.png"))));
            images.put("DOWN", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/burgerDown.png"))));
            images.put("LEFT", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/burgerLeft.png"))));
            images.put("RIGHT", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/burgerRight.png"))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load player images", e);
        }
    }

    /**
     * This method returns the current direction of the player.
     *
     * @param direction The direction to set for the player (e.g., "UP", "DOWN", "LEFT", "RIGHT")
     */
    public void setDirection(String direction) {
        if (images.containsKey(direction))
            currentDirection = direction;
    }

    /**
     * This method returns the current direction of the player.
     *
     * @param g Graphics2D object used for rendering
     */
    @Override
    public void render(Graphics2D g) {
        BufferedImage img = images.get(currentDirection);
        if (img != null) {
            g.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);
        }
    }
}