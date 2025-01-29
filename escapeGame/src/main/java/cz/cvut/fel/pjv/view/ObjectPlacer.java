package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.HERB;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.ORE;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.WATER_ITEM;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.KEY;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.WALL;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.GHOST;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.WATER;

public class ObjectPlacer {

    private final GameBoard gameBoard;
    protected final Player player;
    private final PlayerController playerController;

    public ObjectPlacer(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.player = new Player(9, 0);
        this.playerController = new PlayerController(player, gameBoard);
    }

    public Player getPlayer() {
        return this.player;
    }


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

        gameBoard.placeObject(WALL, 8, 3); gameBoard.placeObject(GHOST, 8, 5); gameBoard.placeObject(GHOST, 8, 6);
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


        playerController.move(Direction.UP);
        playerController.move(Direction.RIGHT);
        playerController.move(Direction.RIGHT);
        playerController.move(Direction.UP);
        playerController.move(Direction.UP);
        playerController.move(Direction.LEFT);
        playerController.move(Direction.LEFT);
        playerController.move(Direction.UP);

        System.out.println("INVENTORY: ");
        System.out.println(player.getInventory());
        System.out.println("HEALTH: " + player.getHealth());
    }
}
