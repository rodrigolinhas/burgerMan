package gameEngine.behaviour;

import gameEngine.object.IGameObject;

/**
 * Abstract class responsive do deal with the behaviour of inheritance classes.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 11, 2025
 */
public abstract class Behaviour implements IBehaviour {
    IGameObject igameObject;

    /**
     * Construtor que associa um GameObject a este Behaviour.
     * @param igameObject GameObject que será controlado por este comportamento.
     */
    public Behaviour(IGameObject igameObject) {
        this.igameObject = igameObject;
    }

    /**
     * Retorna o GameObject associado a este Behaviour.
     * @return GameObject vinculado.
     */
    @Override
    public IGameObject gameObject() {
        return this.igameObject;
    }

    /**
     * Define um novo GameObject para este comportamento.
     * @param gameObject Novo GameObject a ser controlado.
     */
    @Override
    public void gameObject(IGameObject gameObject) {
        this.igameObject = gameObject;
    }
}
