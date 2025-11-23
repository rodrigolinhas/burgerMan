package gameEngine.object;

import collisions.Point;
import collisions.Polygon;
import gameEngine.Transform;
import gameEngine.behaviour.EnemyBehavior;
import gameEngine.shape.EnemyShape;
import org.jetbrains.annotations.NotNull;

/**
 * Responsive class to represent an Enemy GameObject.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version April 29, 2025
 */
public class Enemy extends GameObject {
    private final EnemyType enemyType;

    /**
     * Creates a new Enemy object with the specified position and type.
     * @param position Initial position of the enemy in the environment.
     */
    public Enemy(@NotNull Point position, @NotNull EnemyType type) {
        super("Enemy", Transform.simpleTransform(position), Polygon.simpleSquare(position, 1), new EnemyBehavior(null), null);
        this.enemyType = type;
        behaviour.gameObject(this);
        shape = new EnemyShape(type, this);
    }

    /**
     * Returns the type of the enemy.
     * @return the type of the enemy
     */
    public EnemyType getEnemytype() {
        return enemyType;
    }
}
