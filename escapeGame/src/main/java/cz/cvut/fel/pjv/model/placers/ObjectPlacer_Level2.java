/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.placers;


import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.*;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.*;

/**
 * This class handles the placement of objects for level 2 of the game.
 * It initializes the gae board, places the player, objects, items, and defines the starting positions.
 * Inherits from ObjectPlacerBase.
 */
public class ObjectPlacer_Level2 extends ObjectPlacerBase {

    /**
     * Constructs an ObjectPlacer_Level2 object with the specified game board.
     * @param gameBoard The game board where the objects and items will be placed.
     */
    public ObjectPlacer_Level2(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public int getLevel() {
        return 2;
    }

    /**
     * Retrieves the default position for the player in Level 2.
     * @return The default player object with the specified position.
     */
    @Override
    protected Player getDefaultPlayerPosition() {
        return new Player(9, 9);
    }

    /**
     * Starts the game for level 2 by placing objects, items, and the player on the game board.
     */
    @Override
    public void startGame() {

        gameBoard.placePlayer(player);

        gameBoard.placeObject(FIRE, 0, 0); gameBoard.placeObject(FIRE, 0, 1); gameBoard.placeObject(FIRE, 0, 2);
        gameBoard.placeObject(FIRE, 0, 4); gameBoard.placeObject(FIRE, 0, 5); gameBoard.placeObject(FIRE, 0, 6);

        gameBoard.placeObject(WALL, 1, 8);

        gameBoard.placeObject(FIRE, 2, 0); gameBoard.placeObject(WALL, 2, 2); gameBoard.placeObject(WALL, 2, 4);
        gameBoard.placeObject(WALL, 2, 5); gameBoard.placeObject(WALL, 2, 6); gameBoard.placeObject(WALL, 2, 7);
        gameBoard.placeObject(WALL, 2, 8);

        gameBoard.placeObject(FIRE, 3, 0); gameBoard.placeObject(WALL, 3, 2); gameBoard.placeObject(WALL, 3, 8);

        gameBoard.placeObject(FIRE, 4, 0); gameBoard.placeObject(WALL, 4, 1); gameBoard.placeObject(GHOST, 4, 2);
        gameBoard.placeObject(FIRE, 4, 6); gameBoard.placeObject(WALL, 4, 8);

        gameBoard.placeObject(FIRE, 5, 0); gameBoard.placeObject(FIRE, 5, 6); gameBoard.placeObject(WALL, 5, 9);
        gameBoard.placeObject(FIRE, 5, 1); gameBoard.placeObject(FIRE, 5, 3); gameBoard.placeObject(FIRE, 5, 4);
        gameBoard.placeObject(FIRE, 5, 5);

        gameBoard.placeObject(WALL, 6, 6); gameBoard.placeObject(WALL, 6, 7);

        gameBoard.placeObject(FIRE, 7, 0); gameBoard.placeObject(WALL, 7, 1); gameBoard.placeObject(WALL, 7, 2);
        gameBoard.placeObject(WALL, 7, 3); gameBoard.placeObject(WALL, 7, 4); gameBoard.placeObject(WALL, 7, 8);
        gameBoard.placeObject(WALL, 7, 9);

        gameBoard.placeObject(GHOST, 8, 5);

        gameBoard.placeObject(FIRE, 9, 1); gameBoard.placeObject(FIRE, 9, 2); gameBoard.placeObject(FIRE, 9, 3);
        gameBoard.placeObject(FIRE, 9, 4); gameBoard.placeObject(FIRE, 9, 5); gameBoard.placeObject(WALL, 9, 8);

        gameBoard.placeItem(WATER_ITEM, 1, 0); gameBoard.placeItem(ORE, 3, 1); gameBoard.placeItem(ORE, 3, 9);
        gameBoard.placeItem(HERB, 6, 0); gameBoard.placeItem(KEY, 9, 0); gameBoard.placeItem(WATER_ITEM, 9, 6);
        gameBoard.placeItem(ORE, 6, 9);

        gameBoard.placeNextLevel(GameNextLevel.NEXT_LEVEL, 4, 9);

    }
}
