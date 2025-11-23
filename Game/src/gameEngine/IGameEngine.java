package gameEngine;

import gameEngine.gui.IGUI;
import gameEngine.object.IGameObject;

import java.util.List;

/**
 * The {@code game.IGameEngine} interface stores information about the gameEngine methods
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public interface IGameEngine {
    /**
     * Adiciona um GameObject à lista de ativos.
     * @param gameObject GameObject a ser habilitado.
     */
    void addEnabled(IGameObject gameObject);

    /**
     * Adiciona um GameObject à lista de inativos.
     * @param gameObject GameObject a ser desabilitado.
     */
    void addDisabled(IGameObject gameObject);

    /**
     * Habilita um GameObject desabilitado.
     * @param gameObject GameObject a ser reativado.
     */
    void enable(IGameObject gameObject);

    /**
     * Desabilita um GameObject ativo.
     * @param gameObject GameObject a ser desativado.
     */
    void disable(IGameObject gameObject);

    /**
     * Verifica se um GameObject está ativo.
     * @param gameObject GameObject a ser verificado.
     * @return true se o objeto está habilitado.
     */
    boolean isEnabled(IGameObject gameObject);

    /**
     * Verifica se um GameObject está inativo.
     * @param gameObject GameObject a ser verificado.
     * @return true se o objeto está desabilitado.
     */
    boolean isDisabled(IGameObject gameObject);

    /**
     * Retorna todos os GameObjects ativos.
     * @return Lista de objetos habilitados.
     */
    List<IGameObject> getEnabled();

    /**
     * Retorna todos os GameObjects inativos.
     * @return Lista de objetos desabilitados.
     */
    List<IGameObject> getDisabled();

    /**
     * Destroy IGameObject gameObject whether it is enabled or disabled
     * pre: gameObject != null
     * pos: gameObject.onDestroy()
     * @param gameObject
     */
    void destroy(IGameObject gameObject);

    /**
     * Destroy all IGameObjects
     * pos: calls onDesttoy for each IGameObject
     */
    void destroyAll();

    /**
     * Manipula a transform
     * Generates a new frame:
     * Get user input from Ul
     * update all the enabled GameObjects
     * check collisions and send info to GameObjects
     * update Ul
     * pos: Ul.input() &&
     calls Behaviour.onUpdate() for all enabled objects && Behaviour.checkCollisions() &&
     Ul.draw()
     */
    void run();

    /**
     * Check collisions for all the enabled objects
     * pos: calls Behaviour.onCollision(goI) for all enabled GameObjects passing in the list of all the objects that
     * collided with each IGameObject
     */
    void checkCollisions();

    IGUI gui();
}
