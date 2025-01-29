package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderBackground {

    private final GameBoard gameBoard;

    public RenderBackground(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GAINSBORO);
        graphicsContext.fillRect(0, 0, gameBoard.getSize(), gameBoard.getSize());
        graphicsContext.setStroke(Color.DARKGRAY);

        for (int i = 0; i <= gameBoard.getSize(); i += gameBoard.getTileDim()) {
            graphicsContext.strokeLine(i, 0, i, gameBoard.getSize());
            graphicsContext.strokeLine(0, i, gameBoard.getSize(), i);
        }
    }
}
