package gameEngine.object;

/**
 * Represents different types of game objects that can be collected in the game.
 * <p>
 * These types include generic predefined items like points and various food ingredients.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public enum Type {
    //Generic types pre-define
    POINT("Point"),
    TOMATO("Tomato"),
    ONION("Onion"),
    CHEESE("Cheese"),
    PICKLE("Pickle");

    /**
     * The human-readable name of the type used for identification and display purposes.
     */
    final String name;

    /**
     * Constructs a new Type with the specified name.
     * @param name The human-readable name of the type (used for identification and display).
     */
    Type(String name) {
        this.name = name;
    }

}