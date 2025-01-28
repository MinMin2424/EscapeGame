package cz.cvut.fel.pjv;

import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.HERB;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.ORE;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.WATER_ITEM;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.KEY;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.WALL;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.GHOST;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.FIRE;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.WATER;

public class GamePosition {

    public void startGame() {
        GameBoard gameBoard = new GameBoard();
        Player player = new Player(9, 0);
        PlayerController playerController = new PlayerController(gameBoard);

        gameBoard.placePlayer(player);


        gameBoard.placeObject(WALL, 1, 1); gameBoard.placeObject(WALL, 1, 2); gameBoard.placeObject(WALL, 1, 3);
        gameBoard.placeObject(WALL, 1, 4); gameBoard.placeObject(WALL, 1, 5); gameBoard.placeObject(WALL, 1, 8);
        gameBoard.placeObject(WATER, 1, 9);

        gameBoard.placeObject(WALL, 2, 1); gameBoard.placeObject(WALL, 2, 8);

        gameBoard.placeObject(WALL, 3, 1); gameBoard.placeObject(WATER, 3, 2); gameBoard.placeObject(WATER, 3, 3);
        gameBoard.placeObject(WALL, 3, 5); gameBoard.placeObject(WALL, 3, 6); gameBoard.placeObject(WALL, 3, 7);
        gameBoard.placeObject(WALL, 3, 8);

        gameBoard.placeObject(WALL, 4, 0); gameBoard.placeObject(GHOST, 4, 2); gameBoard.placeObject(GHOST, 4, 3);
        gameBoard.placeObject(GHOST, 4, 4); gameBoard.placeObject(GHOST, 4, 5); gameBoard.placeObject(GHOST, 4, 6);
        gameBoard.placeObject(GHOST, 4, 7);

//        gameBoard.placeObject(WALL, 5, 2);
        gameBoard.placeObject(WALL, 5, 3); gameBoard.placeObject(WALL, 5, 4);
        gameBoard.placeObject(WALL, 5, 6); gameBoard.placeObject(WALL, 5, 7); gameBoard.placeObject(WALL, 5, 8);

        gameBoard.placeObject(WALL, 6, 3); gameBoard.placeObject(WALL, 6, 4); gameBoard.placeObject(WALL, 6, 5);
        gameBoard.placeObject(WALL, 6, 6); gameBoard.placeObject(WALL, 6, 7); gameBoard.placeObject(WALL, 6, 8);

        gameBoard.placeObject(WALL, 7, 0); gameBoard.placeObject(WALL, 7, 1); gameBoard.placeObject(WALL, 7, 3);

        gameBoard.placeObject(WALL, 8, 3); gameBoard.placeObject(GHOST, 8, 5); gameBoard.placeObject(GHOST, 5, 6);
        gameBoard.placeObject(GHOST, 8, 7); gameBoard.placeObject(GHOST, 8, 8); gameBoard.placeObject(GHOST, 8, 9);

        gameBoard.placeObject(WALL, 9, 3); gameBoard.placeObject(WALL, 9, 4); gameBoard.placeObject(WALL, 9, 5);
        gameBoard.placeObject(WALL, 9, 6); gameBoard.placeObject(WALL, 9, 7); gameBoard.placeObject(WALL, 9, 8);

        gameBoard.placeItem(ORE, 8, 2);
        gameBoard.placeItem(WATER_ITEM, 7, 2);

        playerController.moveRight(player);
        playerController.moveRight(player);
        playerController.moveUp(player);
        playerController.moveUp(player);
        playerController.moveUp(player);
        playerController.moveUp(player);
        playerController.moveUp(player);
        playerController.moveLeft(player);
        playerController.moveDown(player);
        playerController.moveDown(player);

        gameBoard.drawBoard();

        System.out.println(player.getInventory());
        System.out.println(player.getHealth());
    }
}

