/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Handles the rendering of the player's health indicators.
 */
public class RenderHealthForPlayer {

    private final Player player; // The player object whose health is being rendered
    private final Image heartImage; // The image representing a heart
    private final int imageSize = 32; // The size of the heart image
    private final int padding = 10; // The padding between heart image

    /**
     * Constructs a RenderHealthForPlayer object with the specified player.
     * @param player The player whose health is being rendered.
     */
    public RenderHealthForPlayer(Player player) {
        this.player = player;
        this.heartImage = new Image("heart2.png"); // Load the heart image
    }

    /**
     * Renders the player's health indicators on the canvas using the provided GraphicsContext
     * @param graphicsContext The graphics context to render on.
     */
    public void render(GraphicsContext graphicsContext) {
        int playerHealth = player.getHealth();
        for (int i = 0; i < playerHealth; i++) {
            int x = i * (imageSize + padding);
            graphicsContext.drawImage(heartImage, x, 15, imageSize, imageSize);
        }
    }
}
