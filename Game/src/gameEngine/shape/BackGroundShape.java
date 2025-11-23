package gameEngine.shape;

import gameEngine.object.BackGround;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
/**
 * This class represents the background shape of the game.
 * It extends the Shape class and renders the background image.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class BackGroundShape extends Shape {
    private final Image background;

    /**
     * Constructor that initializes the background shape with the given BackGround object.
     * @param background
     */
    public BackGroundShape(BackGround background) {
        super(background);
        this.background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/map.png"))).getImage();
    }

    /**
     * This method renders the background image on the given Graphics2D object.
     * It centers the background image on the screen.
     *
     * @param g The Graphics2D object used for rendering.
     */
    @Override
    public void render(Graphics2D g) {
        // Renderiza o fundo centralizado
        int x = -background.getWidth(null) / 2;
        int y = -background.getHeight(null) / 2;

        //int x = (getWidth() - BACKGROUND.getWidth(null)) / 2;
        //int y = (getHeight() - BACKGROUND.getHeight(null)) / 2;
        g.drawImage(background, x, y, null);
    }
}