/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Handles the rendering of the background grid on the game board.
 */
public class RenderBackground {

    private final GameBoard gameBoard; // The game board to render the background on

    /**
     * Constructs a RenderBackground object with the specified game board.
     * @param gameBoard The game board to render the background grid on.
     */
    public RenderBackground(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Renders the background grid on the game board using the provided GraphicsContext.
     * @param graphicsContext The graphics context to render on.
     */
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GHOSTWHITE); // Set fill color to GHOSTWHITE
        graphicsContext.fillRect(0, 0, gameBoard.getSize(), gameBoard.getSize()); // Fill the background with GHOSTWHITE
        graphicsContext.setStroke(Color.DARKGRAY); // Set stroke color to DARKGRAY

        // Render vertical and horizontal grid lines
        for (int i = 0; i <= gameBoard.getSize(); i += gameBoard.getTileDim()) {
            graphicsContext.strokeLine(i, 0, i, gameBoard.getSize()); // Vertical lines
            graphicsContext.strokeLine(0, i, gameBoard.getSize(), i); // Horizontal lines
        }
    }
}
