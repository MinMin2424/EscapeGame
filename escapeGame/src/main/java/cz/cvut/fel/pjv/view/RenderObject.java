package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderObject {
    private final GameBoard gameBoard;

    public RenderObject(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void renderObject(GraphicsContext graphicsContext, int tileDim) {
        int[][] board = gameBoard.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] != 0) {

                    int objectCode = board[i][j];
                    Image image = null;

                    GameObjects gameObjects = GameObjects.getByCode(objectCode);
                    if (gameObjects != null) {
                        image = new Image(gameObjects.getImageName());

                    } else {

                        GameItems gameItems = GameItems.getByCode(objectCode);
                        GameNextLevel gameNextLevel = GameNextLevel.NEXT_LEVEL;
                        if (gameItems != null) {
                            image = new Image(gameItems.getImageName());

                        } else if (objectCode == 1) {
                            image = new Image("character.png");

                        } else {
                            image = new Image(gameNextLevel.getImageName());

                        }
                    }
                    graphicsContext.drawImage(image, j * tileDim, i * tileDim, tileDim, tileDim);
                }
            }
        }
    }
}
