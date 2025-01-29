package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderHealthForPlayer {

    private final Player player;
    private final Image heartImage;
    private final int imageSize = 32;
    private final int padding = 10;

    public RenderHealthForPlayer(Player player) {
        this.player = player;
        this.heartImage = new Image("heart.png");
    }

    public void render(GraphicsContext graphicsContext) {
        int playerHealth = player.getHealth();
        for (int i = 0; i < playerHealth; i++) {
            int x = i * (imageSize + padding);
            graphicsContext.drawImage(heartImage, x, 15, imageSize, imageSize);
        }
    }
}
