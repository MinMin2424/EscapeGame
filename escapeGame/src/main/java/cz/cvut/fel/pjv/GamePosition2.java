package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.direction.Direction;
import cz.cvut.fel.pjv.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameNextLevel;

import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.HERB;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.ORE;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.WATER_ITEM;
import static cz.cvut.fel.pjv.gameObjects_Items.GameItems.KEY;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.WALL;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.GHOST;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.FIRE;
import static cz.cvut.fel.pjv.gameObjects_Items.GameObjects.WATER;

public class GamePosition2 {

    public void startGame() {
        GameBoard gameBoard = new GameBoard();
        Player player = new Player(9, 0);
        PlayerController playerController = new PlayerController(player, gameBoard);

        gameBoard.placePlayer(player);

        System.out.println(" ");
        System.out.println("LEVEL 2: ");
        gameBoard.drawBoard();
    }
}

