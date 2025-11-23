package gameEngine.sound;

import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * This class represents an audio player for the game.
 * It initializes the Clip and URL objects to null.
 *
 * @author Rodrigo Linhas
 * @author Ricardo Rodrigues
 * @author Tiago Tome
 * @version May 24, 2025
 */
public class AudioPlayer {

    private Clip clip;
    private final URL[] soundurl = new URL[10];

    /**
     * Constructor of the AudioPlayer class.
     * Initializes the Clip and URL objects to null.
     */
    public AudioPlayer() {
        soundurl[0] = getClass().getResource("/sounds/pacmanDeath.wav");
        soundurl[1] = getClass().getResource("/sounds/pacmanEat.wav");
        soundurl[2] = getClass().getResource("/sounds/pacmanEatFruit.wav");
        soundurl[3] = getClass().getResource("/sounds/pacmanEatGhost.wav");
    }

    /**
     * This method sets the sound file to be played.
     * It takes an integer as a parameter to select the sound file from the array.
     *
     * @param i The index of the sound file in the array.
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundurl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception ignored) {
        }
    }

    /**
     * This method plays the sound file.
     * It sets the file to be played and starts the clip.
     */
    public void play() {
        clip.start();
    }

    /**
     * This method plays the sound file in an infinite loop.
     * It sets the file to be played and starts the clip in a loop.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * This method stops the sound file from playing.
     * It stops the clip.
     */
    public void stop() {
        clip.stop();
    }
}