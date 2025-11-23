package gameEngine.shape;

import gameEngine.object.Collectible;
import gameEngine.object.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * This class represents the shape of a collectible object in the game.
 * It extends the Shape class and renders the collectible image based on its type.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class CollectibleShape extends Shape {
    private final Map<Type, BufferedImage> images = new HashMap<>();
    private final Type collectibleType;

    /**
     * Constructor that initializes the CollectibleShape with a specific type and associated Collectible object.
     *
     * @param type The type of collectible (e.g., TOMATO, CHEESE, etc.)
     * @param object The Collectible object associated with this shape
     */
    public CollectibleShape(Type type, Collectible object) {
        super(null); // O GameObject será associado depois, assim posso criar um colletible e no shape passo-lhe o type
        this.collectibleType = type;
        gameObject(object);

        try {
            images.put(Type.TOMATO, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tomato.png"))));
            images.put(Type.CHEESE, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/cheese.png"))));
            images.put(Type.ONION, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/onion.png"))));
            images.put(Type.PICKLE, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pickle.png"))));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load collectible images", e);
        }
    }

    /**
     * This method returns the type of collectible associated with this shape.
     *
     * @param  g Graphics2D object used for rendering
     */
    @Override
    public void render(Graphics2D g) {
        BufferedImage img = images.get(collectibleType);
        if (img != null) {
            g.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);
        } else if (collectibleType == Type.POINT) {
            g.setColor(Color.YELLOW);
            g.fillOval(-3, -3, 5, 5);
        }
    }
}