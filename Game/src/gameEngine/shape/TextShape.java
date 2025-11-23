package gameEngine.shape;

import java.awt.*;
import java.io.InputStream;

/**
 * This class represents a text shape that can be rendered in the game.
 * It extends the Shape class and allows for displaying text with a specific color and font size.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class TextShape extends Shape {
    private String text;
    private final Color color;
    private final int fontSize;
    private Font font;

    /**
     * Constructor that initializes the TextShape with the given text, color, and font size.
     *
     * @param initialText The initial text to be displayed
     * @param color       The color of the text
     * @param fontSize    The size of the font
     */
    public TextShape(String initialText, Color color, int fontSize) {
        super(null); // O GameObject será associado depois
        this.text = initialText;
        this.color = color;
        this.fontSize = fontSize;

        try (InputStream is = getClass().getResourceAsStream("/fonts/pacmanFont.TTF")) {
            if (is == null) {return;}
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            this.font = baseFont.deriveFont((float) this.fontSize);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao carregar a fonte: /fonts/pacmanFont.TTF", e);
        }
    }

    /**
     * This method returns the current text displayed by the TextShape.
     *
     * @param newText The new text to be displayed
     */
    public void setText(String newText) {
        this.text = newText;
    }

    /**
     * This method returns the current text displayed by the TextShape.
     *
     * @param g Graphics2D object used for rendering
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = -metrics.stringWidth(text) / 2; //centraliza o texto horizontalmente
        int y = metrics.getAscent() / 2; //ajusta verticalmente
        g.drawString(text, x, y);
    }
}