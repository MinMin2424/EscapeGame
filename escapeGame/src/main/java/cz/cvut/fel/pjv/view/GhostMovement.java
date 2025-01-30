package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

import java.util.Timer;
import java.util.TimerTask;

public class GhostMovement {

    private final GameBoard gameBoard;
    private final int currentX;
    private int currentY;
    private final int finalY;
    private int direction;
    private GraphicsContext graphicsContext;
    private RenderBackground renderBackground;
    private ObjectRender objectRender;

    public GhostMovement(GameBoard gameBoard, int currentX, int currentY, int finalY, GraphicsContext graphicsContext, RenderBackground renderBackground, ObjectRender objectRender) {
        this.gameBoard = gameBoard;
        this.currentX = currentX;
        this.currentY = currentY;
        this.finalY = finalY;
        this.direction = -1;
        this.graphicsContext = graphicsContext;
        this.renderBackground = renderBackground;
        this.objectRender = objectRender;
    }

    public void startMovement() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveGhost();
                renderGame();
            }
        }, 1000, 1000);
    }

    private void moveGhost() {
        gameBoard.getBoard()[currentX][currentY] = 0;

        if (currentY == finalY || currentY == 2 || currentY == 5) {
            direction *= -1;
        }

        currentY += direction;
        gameBoard.getBoard()[currentX][currentY] = GameObjects.GHOST.getCode();
    }

    private void renderGame() {
        Platform.runLater(() -> {
            renderBackground.render(graphicsContext);
            objectRender.renderObject(graphicsContext, gameBoard.getTileDim());
        });
    }
}
