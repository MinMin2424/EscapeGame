package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.direction.Direction;
import cz.cvut.fel.pjv.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.gameObjects_Items.GameObjects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.HERB;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.ORE;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.WATER_ITEM;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.KEY;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.WALL;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.GHOST;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.FIRE;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.WATER;

public class GamePosition {

    GameBoard gameBoard = new GameBoard();
    Player player = new Player(9, 0);
    PlayerController playerController = new PlayerController(player, gameBoard);

    public void startGame() {

        gameBoard.placePlayer(player);

        gameBoard.placeObject(WALL, 1, 1); gameBoard.placeObject(WALL, 1, 2); gameBoard.placeObject(WALL, 1, 3);
        gameBoard.placeObject(WALL, 1, 4); gameBoard.placeObject(WALL, 1, 5); gameBoard.placeObject(WATER, 1, 8);
        gameBoard.placeObject(WATER, 1, 9);

        gameBoard.placeObject(WALL, 2, 1); gameBoard.placeObject(WALL, 2, 8);

        gameBoard.placeObject(WALL, 3, 1); gameBoard.placeObject(WATER, 3, 2); gameBoard.placeObject(WATER, 3, 3);
        gameBoard.placeObject(WALL, 3, 5); gameBoard.placeObject(WALL, 3, 6); gameBoard.placeObject(WALL, 3, 7);
        gameBoard.placeObject(WALL, 3, 8);

        gameBoard.placeObject(WALL, 4, 0); gameBoard.placeObject(GHOST, 4, 2); gameBoard.placeObject(GHOST, 4, 3);
        gameBoard.placeObject(GHOST, 4, 4); gameBoard.placeObject(GHOST, 4, 5); gameBoard.placeObject(GHOST, 4, 6);
        gameBoard.placeObject(GHOST, 4, 7);

        gameBoard.placeObject(WALL, 5, 2); gameBoard.placeObject(WALL, 5, 3); gameBoard.placeObject(WALL, 5, 4);
        gameBoard.placeObject(WALL, 5, 6); gameBoard.placeObject(WALL, 5, 7); gameBoard.placeObject(WALL, 5, 8);

        gameBoard.placeObject(WALL, 6, 3); gameBoard.placeObject(WALL, 6, 4); gameBoard.placeObject(WALL, 6, 5);
        gameBoard.placeObject(WALL, 6, 6); gameBoard.placeObject(WALL, 6, 7); gameBoard.placeObject(WALL, 6, 8);

        gameBoard.placeObject(WALL, 7, 0); gameBoard.placeObject(WALL, 7, 1); gameBoard.placeObject(WALL, 7, 3);

        gameBoard.placeObject(WALL, 8, 3); gameBoard.placeObject(GHOST, 8, 5); gameBoard.placeObject(GHOST, 5, 6);
        gameBoard.placeObject(GHOST, 8, 7); gameBoard.placeObject(GHOST, 8, 8); gameBoard.placeObject(GHOST, 8, 9);

        gameBoard.placeObject(WALL, 9, 3); gameBoard.placeObject(WALL, 9, 4); gameBoard.placeObject(WALL, 9, 5);
        gameBoard.placeObject(WALL, 9, 6); gameBoard.placeObject(WALL, 9, 7); gameBoard.placeObject(WALL, 9, 8);

        gameBoard.placeItem(ORE, 8, 0); gameBoard.placeItem(ORE, 2, 0); gameBoard.placeItem(ORE, 0, 9); gameBoard.placeItem(ORE, 5, 5);
        gameBoard.placeItem(WATER_ITEM, 5, 0); gameBoard.placeItem(WATER_ITEM, 6, 0);
        gameBoard.placeItem(HERB, 2, 2);
        gameBoard.placeItem(KEY, 2, 9);

        gameBoard.placeNextLevel(GameNextLevel.NEXT_LEVEL, 9, 9);

        System.out.println(" ");
        System.out.println("LEVEL 1: ");
        System.out.println("HEALTH: " + player.getHealth());
        System.out.println("INVENTORY: ");
        System.out.println(player.getInventory());

        playerController.move(Direction.RIGHT);
        playerController.move(Direction.RIGHT);
        playerController.move(Direction.UP);
        playerController.move(Direction.UP);
        playerController.move(Direction.UP);
        playerController.move(Direction.LEFT);
        playerController.move(Direction.UP);
        playerController.move(Direction.UP);
        playerController.move(Direction.RIGHT);

        System.out.println("HEALTH: " + player.getHealth());

        gameBoard.drawBoard();

    }

    public void renderObjects(GraphicsContext graphicsContext, int tileDim) {

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
                        if (gameItems != null) {
                            image = new Image(gameItems.getImageName());

                        } else if (objectCode == 1) {
                            image = new Image("character.png");
                        }
                    }

                    if (image != null) {
                        graphicsContext.drawImage(image, j * tileDim, i * tileDim, tileDim, tileDim);
                    }
                }
            }
        }
    }

}

