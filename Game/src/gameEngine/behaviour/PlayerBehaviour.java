package gameEngine.behaviour;

import collisions.Point;
import gameEngine.Client;
import gameEngine.ICollider;
import gameEngine.object.*;
import gameEngine.shape.PlayerShape;
import gameEngine.sound.AudioPlayer;
import org.jetbrains.annotations.Nullable;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Responsive class to deal with Player behavior.
 * This class manages the player's movements, states (e.g., invincibility),
 * collision handling, and interactions with the game environment.
 * <p>
 * The class updates the player's state on every frame, handles user inputs, 
 * and processes collisions with other objects such as enemies, items, and obstacles.
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * 
 * @version  May 11, 2025
 */
public class PlayerBehaviour extends Behaviour {
    private double playerSpeed;
    private double playerSpeedTime;
    private boolean invincible;
    private double invincibilityTime;
    private int respawnBuffer;
    public Point speed;
    ScoreBehaviour score;
    LivesBehaviour lives;
    AudioPlayer audioPlayer;

    /**
     * Constructor that associates a GameObject with this behavior.
     * 
     * @param player The GameObject controlled by this behavior.
     */
    public PlayerBehaviour(Player player) {
        super(player);
        speed = new Point(0, 0);
        this.audioPlayer = new AudioPlayer();
        respawnBuffer = 0;
        playSound();
    }

    @Override
    public void gameObject(IGameObject gameObject) {
        super.gameObject(gameObject);
        score = (ScoreBehaviour) Client.ENGINE.getScoreObject().behaviour();
        lives = (LivesBehaviour) Client.ENGINE.getLivesObject().behaviour();
    }

    /**
     * Called on every frame to update the GameObject's state.
     * 
     * @param dT The time in seconds since the last frame (delta time).
     * @param ie User input events (e.g., keyboard).
     */
    @SuppressWarnings("DataFlowIssue")
    @Override
    public void onUpdate(double dT, @Nullable InputEvent ie) {
        if (this.invincible) {
            this.invincibilityTime--;
            if (this.invincibilityTime <= 0) {
                invincible = false;
            }
            //System.out.println(invincibilityTime);
        }
        Point nsp = speed.scaleOrigin(playerSpeed);
        if (ie instanceof KeyEvent k) switch (k.getKeyCode()) {
            case KeyEvent.VK_W -> nsp = new Point(0, -2);
            case KeyEvent.VK_S -> nsp = new Point(0, 2);
            case KeyEvent.VK_D -> nsp = new Point(2, 0);
            case KeyEvent.VK_A -> nsp = new Point(-2, 0);
        }

        Intersection col = Client.ENGINE.getInterAt(igameObject.collider().centroid());

        if ("Player".equals(this.igameObject.name())
                && (col != null || nsp.equals(speed.scaleOrigin(-1)))) {
            speed = nsp;
        }

        if ((speed.getX() + speed.getY()) != 0 && col != null && !col.getNormalized().contains(speed))
            speed = new Point(0, 0);

        PlayerShape shape = (PlayerShape) igameObject.shape();
        switch ((int) speed.getX()) {
            case -2:
                shape.setDirection("LEFT");
                break;
            case 0:
                if (speed.getY() < 0)
                    shape.setDirection("UP");
                else if (speed.getY() > 0)
                    shape.setDirection("DOWN");
                break;
            case 2:
                shape.setDirection("RIGHT");
                break;
        }

        ((GameObject) igameObject).move(speed, 0);
        if (playerSpeedTime > 0) {
            playerSpeedTime--;
            if (playerSpeedTime <= 0) {
                playerSpeed = 1; // Restaure para a velocidade padrão
            }
        }
    }

    /**
     * Called when a collision occurs with other GameObjects.
     * 
     * @param gameObjects A list of collided GameObjects.
     */
    @Override
    public void onCollision(List<IGameObject> gameObjects) {
        ICollider c1 = gameObject().collider(), c2;
        if (c1 == null) return;
        for (IGameObject gameObject : gameObjects) {
            if ((c2 = gameObject.collider()) != null && c1.isColliding(c2)) {
                //System.out.println("Colisao com: " + gameObject.name());
                switch (gameObject.name()) {
                    case "Point", "Cheese", "Onion", "Tomato", "Pickle" -> {
                        this.score.incrementScore(10);
                        Collectible point = (Collectible) gameObject;
                        //System.out.println(point.getType());
                        switch (point.getType()) {
                            case TOMATO:
                                //System.out.println("Colidiu com Tomato. Invincibilidade ativada!");
                                playSE(2);
                                invincible = true;
                                invincibilityTime = 200;
                                Client.ENGINE.destroy(gameObject);
                                break;
                            case ONION:
                                //System.out.println("Colidiu com Onion. Remover inimigo!");
                                playSE(2);
                                Client.ENGINE.destroy(Client.ENGINE.randomObject(o -> o instanceof Enemy));
                                Client.ENGINE.destroy(gameObject);
                                break;
                            case CHEESE:
                                //System.out.println("Colidiu com Cheese. Velocidade aleatoria!");
                                playSE(2);
                                playerSpeed = Client.RANDOM.nextBoolean() ? 0.5 : 2;
                                playerSpeedTime = 5;
                                Client.ENGINE.destroy(gameObject);
                                break;
                            case PICKLE:
                                //System.out.println("Colidiu com Pickle. Removido!");
                                playSE(2);
                                Client.ENGINE.destroy(gameObject);
                                break;
                            default:
                                Client.ENGINE.destroy(gameObject);
                                break;
                        }
                    }
                    case "Enemy" -> {
                        if (invincible) gameObject.behaviour().onDisabled();
                        else onDisabled();
                    }
                    case "Inter" -> {
                        Intersection i = (Intersection) gameObject;
                        if (!i.getNormalized().contains(speed)) speed = new Point(0, 0);
                        else i.setLastPlayerDir(speed);
                    }
                    default -> {}
               }
            }
        }
    }

    /**
     * Initializes the PlayerBehaviour with default values.
     * This method is called once when the GameObject is created.
     */
    @Override
    public void onInit() {
        // Initializes default properties
        invincible = false;
        invincibilityTime = 0;
        playerSpeed = 1;
        playerSpeedTime = 0;
    }

    /**
     * Called when the GameObject is enabled.
     * This method is invoked by the game engine to prepare the GameObject for interaction.
     */
    @Override
    public void onEnabled() {
        onInit();
        Client.ENGINE.enable(gameObject());
    }

    /**
     * Called when the GameObject is disabled.
     * This method is invoked by the game engine to stop interactions with the GameObject.
     */
    @Override
    public void onDisabled() {
        if (lives != null) {
            playDead();
            this.lives.decreaseLives();
        }
        respawnBuffer = 100;
        Client.ENGINE.disable(gameObject());
    }

    /**
     * Destroys the PlayerBehaviour and cleans up resources.
     * This method is called when the GameObject is no longer needed.
     */
    @Override
    public void onDestroy() {
        playSound();
        Client.ENGINE.destroy(igameObject);
    }

    /**
     * Checks if the player is currently invincible.
     *
     * @return true if the player is invincible, false otherwise.
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * Gets the remaining invincibility time for the player.
     *
     * @return The time in seconds that the player remains invincible.
     */
    public double getInvincibilityTime() {
        return invincibilityTime;
    }

    /**
     * Gets the current speed of the player.
     *
     * @return The player's speed as a double value.
     */
    public double getPlayerSpeed() {
        return playerSpeed;
    }

    /**
     * Gets the remaining time for the player's speed effect.
     *
     * @return The time in seconds that the player's speed effect lasts.
     */
    public double getPlayerSpeedTime() {
        return playerSpeedTime;
    }

    /**
     * Slows down the player for a limited time.
     * This method sets the player's speed to 0.8 and the duration to 60 seconds.
     */
    public void slowDown() {
        playerSpeed = 0.5;
        playerSpeedTime = 400;
    }

    /**
     * Increments the respawn buffer, preventing the player from being enabled immediately.
     * This method is called when the player is disabled, allowing a delay before respawning.
     */
    public void decrementRespawnBuffer() {
        if (respawnBuffer <= 0) return;
        if (--respawnBuffer <= 0)
            Client.ENGINE.enable(gameObject());
    }

    /**
     * Plays the background sound for the player.
     * This method sets the audio file and starts playing it in a loop.
     */
    private void playSound() {
        audioPlayer.setFile(1);
        audioPlayer.play();
        audioPlayer.loop();
    }

    /**
     * Plays the death sound for the player.
     * This method stops any currently playing audio, resets the audio file, and plays the death sound.
     */
    private void playDead() {
        audioPlayer.stop();
        audioPlayer.setFile(0);
        audioPlayer.play();
    }

    /**
     * Plays a sound effect based on the provided index.
     * This method sets the audio file based on the index and plays it.
     *
     * @param i The index of the sound effect to play.
     */
    private void playSE(int i) {
        audioPlayer.setFile(i);
        audioPlayer.play();
    }

    /**
     * For testing purposes.
     * <p>
     * Sets the player to be invincible for a specified duration.
     * @param time The duration in seconds for which the player will be invincible.
     */
    public void setInvincible(double time) {
        invincible = true;
        invincibilityTime = time;
    }
}