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

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the rendering of objects and items on the game board.
 */
public class RenderObject {
    private final GameBoard gameBoard; // The game board containing object positions
    private final Map<Integer, Image> imageMap;

    /**
     * Constructs an ObjectRender object with the specified game board.
     * @param gameBoard The game board to render objects and items on.
     */
    public RenderObject(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.imageMap = new HashMap<>();
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
                renderTile(graphicsContext, board[i][j], i, j, tileDim);
            }
        }
    }

    /**
     * Renders an image for the specified object code at the given row and column on the graphics context.
     * @param graphicsContext The graphics context to render the image on.
     * @param objectCode The code representing the object or item to render.
     * @param row The row position on the game board.
     * @param col The column position on the game board.
     * @param tileDim The dimension of a tile.
     */
    private void renderTile(GraphicsContext graphicsContext, int objectCode, int row, int col, int tileDim) {
        if (objectCode != 0) { // Checks if the tile is not empty
            Image image = getImage(objectCode); // Get the image for the object code
            graphicsContext.drawImage(image, col * tileDim, row * tileDim, tileDim, tileDim); // Render the image
        }
    }

    /**
     * Retrieved the image corresponding to the given object code.
     * @param objectCode The code representing the object or item.
     * @return The image corresponding to the object code.
     */
    private Image getImage(int objectCode) {
        if (imageMap.containsKey(objectCode)) {
            return imageMap.get(objectCode);
        } else {
            Image image = loadNewImage(objectCode);
            imageMap.put(objectCode, image);
            return image;
        }
    }


    /**
     * Loads a new image based on the object code.
     *
     * @param objectCode The code representing the object or item.
     * @return The loaded image corresponding to the object code.
     */
    private Image loadNewImage(int objectCode) {
        // Determine the type of object or item and load the corresponding image
        GameObjects gameObjects = GameObjects.getByCode(objectCode);
        if (gameObjects != null) {
            return new Image(gameObjects.getImageName());
        } else {
            GameItems gameItems = GameItems.getByCode(objectCode);
            if (gameItems != null) {
                return new Image(gameItems.getImageName());
            } else if (objectCode == 1) {
                return new Image("character.png");
            } else {
                return new Image(GameNextLevel.NEXT_LEVEL.getImageName());
            }
        }
    }
}
