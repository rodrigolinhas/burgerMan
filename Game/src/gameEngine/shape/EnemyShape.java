package gameEngine.shape;

import gameEngine.object.Enemy;
import gameEngine.object.EnemyType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents the shape of an enemy object in the game.
 * It extends the Shape class and renders the enemy image based on its type and direction.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class EnemyShape extends Shape {
    private final Map<String, BufferedImage> images = new HashMap<>();
    private final EnemyType enemyType;
    private String currentDirection = "DOWN"; // Direção padrão

    /**
     * Constructor that initializes the EnemyShape with a specific type and associated Enemy object.
     *
     * @param type   The type of enemy (e.g., GREEN_TRASH, GRAY_TRASH)
     * @param object The Enemy object associated with this shape
     */
    public EnemyShape(EnemyType type, Enemy object) {
        super(null); // O GameObject será associado depois
        this.enemyType = type;
        gameObject(object);

        try {
            if (type == EnemyType.GREEN_TRASH) {
                images.put("UP", loadImage("/images/greenTrashUp.png"));
                images.put("DOWN", loadImage("/images/greenTrashDown.png"));
                images.put("LEFT", loadImage("/images/greenTrashLeft.png"));
                images.put("RIGHT", loadImage("/images/greenTrashRight.png"));
            } else if (type == EnemyType.GRAY_TRASH) {
                images.put("UP", loadImage("/images/greyTrashUp.png"));
                images.put("DOWN", loadImage("/images/greyTrashDown.png"));
                images.put("LEFT", loadImage("/images/greyTrashLeft.png"));
                images.put("RIGHT", loadImage("/images/greyTrashRight.png"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load enemy images", e);
        }
    }

    /**
     * This method loads an image from the specified path.
     *
     * @param path The path to the image resource
     * @return The loaded BufferedImage
     */
    private BufferedImage loadImage(String path) {
        try {
            return javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    /**
     * This method returns the type of enemy associated with this shape.
     *
     * @param direction The direction in which the enemy is facing (e.g., "UP", "DOWN", "LEFT", "RIGHT")
     */
    public void setDirection(String direction) {
        if (images.containsKey(direction)) {
            currentDirection = direction;
        }
    }

    /**
     * This method returns the type of enemy associated with this shape.
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