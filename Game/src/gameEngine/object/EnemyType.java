package gameEngine.object;

/**
 * Enum representing different types of enemies in the game.
 * <p>
 * Each type has a human-readable name for identification and display purposes.
 *
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 24, 2025
 */
public enum EnemyType {
    GREEN_TRASH("Green Trash"),
    GRAY_TRASH("Gray Trash");

    final String name;

    /**
     * Constructs an EnemyType with a specified name.
     *
     * @param name The human-readable name of the enemy type, used for identification and display purposes.
     */
    EnemyType(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the enemy type.
     *
     * @return The human-readable name of the enemy type.
     */
    public String getName() {
        return name;
    }
}