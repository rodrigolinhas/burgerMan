package gameEngine.gui;

import gameEngine.object.IGameObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 The {@code gameEngine.gui.IGUI} interface defines the contract for graphical user interfaces
 * within the game engine
 * @author Ricardo Rodrigues
 * @author Rodrigo Linhas
 * @author Tiago Tome
 * @version May 27, 2025
 */
public interface IGUI {
    @Nullable InputEvent dequeue();

    /**
     * Displays the given list of game objects on the GUI.
     * @param list the list of IGameObject instances to be displayed; must not be null
     */
    //void display(@NotNull List<IGameObject> list, Graphics graphics);
}
