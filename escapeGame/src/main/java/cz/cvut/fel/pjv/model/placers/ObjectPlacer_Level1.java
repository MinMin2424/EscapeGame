/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.placers;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.HERB;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.ORE;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.WATER_ITEM;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.KEY;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.WALL;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.GHOST;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.WATER;

/**
 * Handles the placement of objects and items on the game board at the start of the game.
 * Inherits from ObjectPlacerBase.
 */
public class ObjectPlacer_Level1 extends ObjectPlacerBase {

    /**
     * Constructs an ObjectPlacer_Level1 object with the specified game board.
     * @param gameBoard The game board where the objects and items will be placed.
     */
    public ObjectPlacer_Level1(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public int getLevel() {
        return 1;
    }

    /**
     * Retrieves the default position for the player in Level 1.
     * @return The default player object with the specified position.
     */
    @Override
    protected Player getDefaultPlayerPosition() {
        return new Player(9, 0);
    }

    /**
     * Starts the game by placing objects, obstacles and items on the game board.
     */
    @Override
    public void startGame() {

        gameBoard.placePlayer(player);

        gameBoard.placeObject(WALL, 1, 1); gameBoard.placeObject(WALL, 1, 2); gameBoard.placeObject(WALL, 1, 3);
        gameBoard.placeObject(WALL, 1, 4); gameBoard.placeObject(WALL, 1, 5); gameBoard.placeObject(WATER, 1, 8);
        gameBoard.placeObject(WATER, 1, 9);

        gameBoard.placeObject(WALL, 2, 1); gameBoard.placeObject(WALL, 2, 8);

        gameBoard.placeObject(WALL, 3, 1); gameBoard.placeObject(WATER, 3, 2); gameBoard.placeObject(WATER, 3, 3);
        gameBoard.placeObject(WALL, 3, 5); gameBoard.placeObject(WALL, 3, 6); gameBoard.placeObject(WALL, 3, 7);
        gameBoard.placeObject(WALL, 3, 8);

        gameBoard.placeObject(WALL, 4, 0); gameBoard.placeObject(GHOST, 4, 2);

        gameBoard.placeObject(WALL, 5, 2); gameBoard.placeObject(WALL, 5, 3); gameBoard.placeObject(WALL, 5, 4);
        gameBoard.placeObject(WALL, 5, 6); gameBoard.placeObject(WALL, 5, 7); gameBoard.placeObject(WALL, 5, 8);

        gameBoard.placeObject(WALL, 6, 3); gameBoard.placeObject(WALL, 6, 4); gameBoard.placeObject(WALL, 6, 5);
        gameBoard.placeObject(WALL, 6, 6); gameBoard.placeObject(WALL, 6, 7); gameBoard.placeObject(WALL, 6, 8);

        gameBoard.placeObject(WALL, 7, 0); gameBoard.placeObject(WALL, 7, 1); gameBoard.placeObject(WALL, 7, 3);

        gameBoard.placeObject(WALL, 8, 3); gameBoard.placeObject(GHOST, 8, 5);

        gameBoard.placeObject(WALL, 9, 3); gameBoard.placeObject(WALL, 9, 4); gameBoard.placeObject(WALL, 9, 5);
        gameBoard.placeObject(WALL, 9, 6); gameBoard.placeObject(WALL, 9, 7); gameBoard.placeObject(WALL, 9, 8);

        gameBoard.placeItem(ORE, 8, 0); gameBoard.placeItem(ORE, 2, 0); gameBoard.placeItem(ORE, 0, 9); gameBoard.placeItem(ORE, 5, 5);
        gameBoard.placeItem(WATER_ITEM, 5, 0); gameBoard.placeItem(WATER_ITEM, 6, 0);
        gameBoard.placeItem(HERB, 2, 2);
        gameBoard.placeItem(KEY, 2, 9);

        gameBoard.placeNextLevel(GameNextLevel.NEXT_LEVEL, 9, 9);
    }
}
