package gameEngine;

import collisions.Colisor;
import collisions.Point;
import gameEngine.behaviour.IBehaviour;
import gameEngine.behaviour.PlayerBehaviour;
import gameEngine.behaviour.SolidBehaviour;
import gameEngine.gui.GUI;
import gameEngine.gui.IGUI;
import gameEngine.object.*;
import gameEngine.sound.AudioPlayer;
import org.jetbrains.annotations.Nullable;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class represents various gameObjects in an arrayList
 * 
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version March 27, 2025
 */
public class GameEngine implements IGameEngine {
    private final ArrayList<GameObject> loadedObjects;
    private final ArrayList<IGameObject> enableObjects;
    private final ArrayList<IGameObject> disableObjects;
    private static final int FRAME_RATE = 30;
    private static final long FRAME_TIME = 1000 / FRAME_RATE;
    public KeyEvent event = null; // retirar depois, só para testes
    private GUI gui;
    private boolean isPaused = false;

    /**
     * Construtor of GameEngine
     */
    public GameEngine() {
        this.loadedObjects = new ArrayList<>();
        this.enableObjects = new ArrayList<>();
        this.disableObjects = new ArrayList<>();
    }

    /**
     * Sets the GUI for the game engine.
     * 
     * @param gui the GUI to be set
     */
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    /**
     * Method do add gameObjects to arrayList
     * 
     * @param gameObject
     */
    public void add(GameObject gameObject) {
        loadedObjects.add(gameObject);
        enableObjects.add(gameObject);

        // inicializa o comportamento e o estado "enabled"
        IBehaviour b = gameObject.behaviour();
        if (b != null) {
            b.onEnabled();
        }
    }

    /**
     * Method do remove gameObjects from arrayList
     * 
     * @param gameObject
     */
    public void destroy(GameObject gameObject) {
        if (gameObject == null)
            return;
        loadedObjects.remove(gameObject);
        (isEnabled(gameObject) ? enableObjects : disableObjects).remove(gameObject);
    }

    /**
     * Response method to give the ArrayList of gameObjects.
     * 
     * @return the List of gameObjets in engine
     */
    public ArrayList<GameObject> getLoadedObjects() {
        return loadedObjects;
    }

    /**
     * Adiciona um GameObject à lista de objetos habilitados (ativos no jogo).
     * 
     * @param gameObject GameObject a ser ativado.
     * @pre gameObject != null
     * @post GameObject é atualizado e verifica colisões.
     */
    @Override
    public void addEnabled(IGameObject gameObject) {
        if (gameObject != null) {
            enableObjects.add(gameObject);
            this.checkCollisions();
        }
    }

    /**
     * Adiciona um GameObject à lista de objetos desabilitados (inativos).
     * 
     * @param gameObject GameObject a ser desativado.
     * @pre gameObject != null
     * @post GameObject não é mais atualizado.
     */
    @Override
    public void addDisabled(IGameObject gameObject) {
        if (gameObject != null) {
            disableObjects.add(gameObject);
        }
    }

    /**
     * Habilita um GameObject previamente desabilitado.
     * 
     * @param gameObject GameObject a ser reativado.
     * @pre gameObject está em disabledObjects
     * @post GameObject é movido para enabledObjects
     */
    @Override
    public void enable(IGameObject gameObject) {
        if (gameObject != null && this.isDisabled(gameObject)) {
            disableObjects.remove(gameObject);
            enableObjects.add(gameObject);
        }
    }

    /**
     * Desabilita um GameObject ativo.
     * 
     * @param gameObject GameObject a ser desativado.
     * @pre gameObject está em enabledObjects
     * @post GameObject é movido para disabledObjects
     */
    @Override
    public void disable(IGameObject gameObject) {
        if (gameObject != null && this.isEnabled(gameObject)) {
            enableObjects.remove(gameObject);
            disableObjects.add(gameObject);
        }
    }

    /**
     * Verifica se um GameObject está ativo.
     * 
     * @param gameObject GameObject a ser verificado.
     * @return true se o objeto está em enabledObjects.
     */
    @Override
    public boolean isEnabled(IGameObject gameObject) {
        return enableObjects.contains(gameObject);
    }

    /**
     * Verifica se um GameObject está inativo.
     * 
     * @param gameObject GameObject a ser verificado.
     * @return true se o objeto está em disabledObjects.
     */
    @Override
    public boolean isDisabled(IGameObject gameObject) {
        return disableObjects.contains(gameObject);
    }

    /**
     * Retorna todos os GameObjects ativos.
     * 
     * @return Lista de objetos em enabledObjects.
     */
    @Override
    public List<IGameObject> getEnabled() {
        return enableObjects;
    }

    /**
     * Retorna todos os GameObjects inativos.
     * 
     * @return Lista de objetos em disabledObjects.
     */
    @Override
    public List<IGameObject> getDisabled() {
        return disableObjects;
    }

    /**
     * Destroy IGameObject gameObject whether it is enabled or disabled
     * 
     * @pre: gameObject != null
     * @pos: gameObject.onDestroy()
     *
     * @param gameObject
     */
    @Override
    public void destroy(IGameObject gameObject) {
        if (gameObject != null) {
            GameObject go = (GameObject) gameObject;
            loadedObjects.remove(go);
            (isEnabled(go) ? enableObjects : disableObjects).remove(go);
        }
    }

    /**
     * Destroy all IGameObjects
     * 
     * @pos: calls onDesttoy for each IGameObject
     */
    @Override
    public void destroyAll() {
        loadedObjects.clear();
    }

    /**
     * Manipula a transform
     * Generates a new frame:
     * Get user input from UI
     * update all the enabled GameObjects
     * check collisions and send info to GameObjects
     * update UI
     * 
     * @pos: UI.input() &&
     *       calls Behaviour.onUpdate() for all enabled objects &&
     *       Behaviour.checkCollisions() &&
     *       UI.draw()
     */
    @Override
    public void run() {
        while (true) {
            if (!isPaused) {
                long startTime = System.currentTimeMillis();
                IBehaviour be;
                InputEvent ie = gui.dequeue();
                event = ie == null ? null : (KeyEvent) ie;
                ArrayList<IGameObject> list = new ArrayList<>(enableObjects);
                for (IGameObject go : list) {
                    if ((be = go.behaviour()) != null)
                        be.onUpdate(startTime, event);
                }
                this.checkCollisions();
                gui.refresh();
                // gui.display(enableObjects, gui.getGraphics());
                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = FRAME_TIME - elapsedTime;

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Player p;
                if ((p = getPlayerObject()) != null)
                    ((PlayerBehaviour) Objects.requireNonNull(p.behaviour())).decrementRespawnBuffer();
                // System.out.println(loadedObjects.stream().filter(go -> go instanceof
                // Collectible).toList());
            }
        }
    }

    /**
     * Pauses the game by clearing all enabled objects and setting the paused state.
     * 
     * @pos: calls destroyAll() && isPaused = true
     */
    public void pauseGame() {
        destroyAll();
        isPaused = true;
    }

    /**
     * Check collisions for all the enabled objects
     * 
     * @pos: calls Behaviour.onCollision(goI) for all enabled GameObjects passing in
     *       the list of all the objects that
     *       collided with each IGameObject
     */
    @Override
    public void checkCollisions() {
        IBehaviour be;
        List<IGameObject> copy = new ArrayList<>(enableObjects);
        for (IGameObject iGameObject : copy) {
            if ((be = iGameObject.behaviour()) != null)
                be.onCollision(copy);
        }
    }

    /**
     * Retorna o objeto GUI associado ao GameEngine.
     * 
     * @return o objeto GUI.
     */
    @Override
    public IGUI gui() {
        return gui;
    }

    /**
     * Retorna um GameObject aleatório que satisfaz um predicado.
     * 
     * @param predicate Condição para filtrar objetos.
     * @return GameObject aleatório ou null se nenhum for encontrado.
     */
    public @Nullable GameObject randomObject(Predicate<GameObject> predicate) {
        List<GameObject> list = loadedObjects.stream().filter(predicate).toList();
        if (list.isEmpty())
            return null;
        return list.get(Client.RANDOM.nextInt(list.size()));
    }

    /**
     * Verifica se um GameObject foi destruído.
     * 
     * @param iGameObject Objeto a ser verificado.
     * @return true se o objeto foi destruído (implementação temporária).
     */
    public boolean isDestroyed(IGameObject iGameObject) {
        for (IGameObject iGo : loadedObjects) {
            if (iGo.equals(iGameObject)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se há colisões com objetos do tipo {@link Solid} numa
     * coordenada específica
     * 
     * @param point a coordenada
     * @return {@code true} se e só se há colisão com paredes
     */
    public boolean checkSolidCollisionAt(Point point) {
        return checkSolidCollisionAt(point, false);
    }

    @SuppressWarnings("DataFlowIssue")
    public boolean checkSolidCollisionAt(Point point, boolean enemyWall) {
        for (GameObject o : loadedObjects)
            if (o instanceof Solid s
                    && ((Colisor) s.collider()).contains(point)
                    && (!enemyWall || ((SolidBehaviour) s.behaviour()).enemyWall()))
                return true;
        return false;
    }

    public Intersection getInterAt(Point point) {
        for (GameObject o : loadedObjects)
            if (o instanceof Intersection i
                    && ((Colisor) Objects.requireNonNull(i.collider())).contains(point)) {
                return i;
            }
        return null;
    }

    /**
     * Encontra o primeiro {@link GameObject} do tipo {@link Score}.
     * 
     * @return o primeiro {@link GameObject} do tipo {@link Score}.
     */
    public Score getScoreObject() {
        return (Score) loadedObjects.stream()
                .filter(obj -> obj.name().equals("Score"))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Encontra o primeiro {@link GameObject} do tipo {@link Lives}.
     * 
     * @return o primeiro {@link GameObject} do tipo {@link Lives}.
     */
    public Lives getLivesObject() {
        return (Lives) loadedObjects.stream()
                .filter(obj -> obj.name().equals("Lives"))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Encontra o primeiro {@link GameObject} do tipo {@link Player}.
     * 
     * @return o primeiro {@link GameObject} do tipo {@link Player}.
     */
    public @Nullable Player getPlayerObject() {
        try {
            return (Player) loadedObjects.stream()
                    .filter(obj -> obj.name().equals("Player"))
                    .findFirst()
                    .orElseThrow();
        } catch (Exception ignored) {
            return null;
        }
    }
}