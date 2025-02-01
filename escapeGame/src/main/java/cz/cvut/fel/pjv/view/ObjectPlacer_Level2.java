/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.save.GameData;

import java.io.File;
import java.io.IOException;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.*;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.*;

/**
 * This class handles the placement of objects for level 2 of the game.
 * It initializes the gae board, places the player, objects, items, and defines the starting positions.
 */
public class ObjectPlacer_Level2 {

    private final GameBoard gameBoard; // The game board where objects and items are placed
    private Player player; // The player object
    protected final PlayerController playerController; // The controller for player movement
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructs an ObjectPlacer_Level2 with the given game board.
     * @param gameBoard The gameBoard for level 2.
     */
    public ObjectPlacer_Level2(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        loadPlayerPositionFromFile();
        this.playerController = new PlayerController(player, gameBoard);
    }

    /**
     * Retrieves the player object.
     * @return The player object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player object.
     * @param player The player object to set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Loads the player position from a JSON file.
     * If the file exists and contains player data, the player's position is loaded from the file.
     * If the file does not exist or there is an error reading it, default player position (9,9) is set.
     */
    private void loadPlayerPositionFromFile() {

        try {
            String LOAD_GAME_FILE = "saveGame.json";
            GameData gameData = objectMapper.readValue(new File(LOAD_GAME_FILE), GameData.class);
            if (gameData.player != null) {
                this.player = gameData.player;
                int loadPlayerX = gameData.player.getPlayerX();
                int loadPlayerY = gameData.player.getPlayerY();
                player.setPlayerX(loadPlayerX);
                player.setPlayerY(loadPlayerY);
            } else {
                this.player = new Player(9, 9);
            }

        } catch (IOException e) {
            this.player = new Player(9, 9);
        }
    }

    /**
     * Starts the game for level 2 by placing objects, items, and the player on the game board.
     */
    public void startGame() {

        gameBoard.placePlayer(player);

        gameBoard.placeObject(FIRE, 0, 0); gameBoard.placeObject(FIRE, 0, 1); gameBoard.placeObject(FIRE, 0, 2);
        gameBoard.placeObject(GHOST, 0, 3);
//        gameBoard.placeObject(GHOST, 0, 4); gameBoard.placeObject(GHOST, 0, 5);
//        gameBoard.placeObject(GHOST, 0, 6); gameBoard.placeObject(GHOST, 0, 7);

        gameBoard.placeObject(WALL, 1, 8);

        gameBoard.placeObject(FIRE, 2, 0); gameBoard.placeObject(WALL, 2, 2); gameBoard.placeObject(WALL, 2, 4);
        gameBoard.placeObject(WALL, 2, 5); gameBoard.placeObject(WALL, 2, 6); gameBoard.placeObject(WALL, 2, 7);
        gameBoard.placeObject(WALL, 2, 8);

        gameBoard.placeObject(FIRE, 3, 0); gameBoard.placeObject(WALL, 3, 2); gameBoard.placeObject(WALL, 3, 8);

        gameBoard.placeObject(FIRE, 4, 0); gameBoard.placeObject(WALL, 4, 2); gameBoard.placeObject(WALL, 4, 3);
        gameBoard.placeObject(FIRE, 4, 4); gameBoard.placeObject(FIRE, 4, 5); gameBoard.placeObject(FIRE, 4, 6);
        gameBoard.placeObject(WALL, 4, 8);

        gameBoard.placeObject(FIRE, 5, 0); gameBoard.placeObject(FIRE, 5, 6); gameBoard.placeObject(WALL, 5, 9);

        gameBoard.placeObject(GHOST, 6, 1);
//        gameBoard.placeObject(GHOST, 6, 2); gameBoard.placeObject(GHOST, 6, 3);
//        gameBoard.placeObject(GHOST, 6, 4); gameBoard.placeObject(GHOST, 6, 5);
        gameBoard.placeObject(WALL, 6, 6); gameBoard.placeObject(WALL, 6, 7);

        gameBoard.placeObject(FIRE, 7, 0); gameBoard.placeObject(WALL, 7, 1); gameBoard.placeObject(WALL, 7, 2);
        gameBoard.placeObject(WALL, 7, 3); gameBoard.placeObject(WALL, 7, 4); gameBoard.placeObject(WALL, 7, 8);

        gameBoard.placeObject(FIRE, 8, 6); gameBoard.placeObject(WALL, 8, 8);

        gameBoard.placeObject(FIRE, 9, 1); gameBoard.placeObject(FIRE, 9, 2); gameBoard.placeObject(FIRE, 9, 3);
        gameBoard.placeObject(FIRE, 9, 4); gameBoard.placeObject(FIRE, 9, 5); gameBoard.placeObject(WALL, 9, 8);

        gameBoard.placeItem(WATER_ITEM, 1, 0); gameBoard.placeItem(ORE, 3, 9); gameBoard.placeItem(ORE, 5, 5);
        gameBoard.placeItem(HERB, 6, 0); gameBoard.placeItem(KEY, 9, 0); gameBoard.placeItem(WATER_ITEM, 9, 6);

        gameBoard.placeNextLevel(GameNextLevel.NEXT_LEVEL, 4, 9);

        System.out.println(" ");
        System.out.println("LEVEL 2: ");
        System.out.println("HEALTH: " + player.getHealth());

    }
}
