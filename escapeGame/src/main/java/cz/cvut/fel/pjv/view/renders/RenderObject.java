/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.renders;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Handles the rendering of objects and items on the game board.
 */
public class RenderObject {
    private final GameBoard gameBoard; // The game board containing object positions

    /**
     * Constructs an ObjectRender object with the specified game board.
     * @param gameBoard The game board to render objects and items on.
     */
    public RenderObject(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Renders objects and items on the game board using the provided GraphicsContext and tile dimension.
     * @param graphicsContext The graphics context to render on.
     * @param tileDim The dimension of each tile on the game board.
     */
    public void renderObject(GraphicsContext graphicsContext, int tileDim) {
        int[][] board = gameBoard.getBoard(); // Get the array representing the game board

        // Iterate through the game board and render objects and items
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] != 0) { // Checks if the tile is not empty

                    int objectCode = board[i][j]; // Get the code representing the object or item
                    Image image; // Image to render

                    // Determine the type of object or item and load the corresponding image
                    GameObjects gameObjects = GameObjects.getByCode(objectCode);
                    if (gameObjects != null) {
                        image = new Image(gameObjects.getImageName());

                    } else {

                        GameItems gameItems = GameItems.getByCode(objectCode);
                        GameNextLevel gameNextLevel = GameNextLevel.NEXT_LEVEL;
                        if (gameItems != null) {
                            image = new Image(gameItems.getImageName());

                        } else if (objectCode == 1) { // Player character
                            image = new Image("character.png");

                        } else {
                            image = new Image(gameNextLevel.getImageName());

                        }
                    }
                    // Render the image on the graphics context at the appropriate position
                    graphicsContext.drawImage(image, j * tileDim, i * tileDim, tileDim, tileDim);
                }
            }
        }
    }
}
