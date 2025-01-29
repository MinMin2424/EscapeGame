package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderHealthForPlayer {

    private final ObjectPlacer objectPlacer;

    public RenderHealthForPlayer(ObjectPlacer objectPlacer) {
        this.objectPlacer = objectPlacer;
    }

    public void render(GraphicsContext graphicsContext) {
        Image image = new Image("heart.png");
        int playerHealth = objectPlacer.player.getHealth();
        int imageSize = 32;
        int padding = 10;

        for (int i = 0; i < playerHealth; i++) {
            int x = i * (imageSize + padding);
            graphicsContext.drawImage(image, x, 15, imageSize, imageSize);
        }
    }
}
