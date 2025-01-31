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

import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.HERB;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.ORE;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.WATER_ITEM;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameItems.KEY;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.WALL;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.GHOST;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects.WATER;

/**
 * Handles the placement of objects and items on the game board at the start of the game.
 */
public class ObjectPlacer {

    private final GameBoard gameBoard; // The game board where objects and items are placed
    private Player player; // The player object
    protected final PlayerController playerController; // The controller for player movement
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructs an ObjectPlacer object with the specified game board.
     * Initializes the player object and player controller.
     * @param gameBoard The game board where the objects and items will be placed.
     */
    public ObjectPlacer(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        loadPlayerPositionFromFile();
        this.playerController = new PlayerController(player, gameBoard);
    }

    /**
     * Retrieves the player object.
     * @return The player object.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player object to the specified player.
     * @param player The player object to be set.
     */
    public void setPlayer (Player player) {
        this.player = player;
    }

    /**
     * Loads the player position from a JSON file.
     * If the file exists and contains player data, the player's position is loaded from the file.
     * If the file does not exist or there is an error reading it, default player position (9,0) is set.
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
                this.player = new Player(9, 0);
            }

        } catch (IOException e) {
            System.err.println("Cannot load game: " + e.getMessage());
            this.player = new Player(9, 0);
        }
    }

    /**
     * Starts the game by placing objects, obstacles and items on the game board.
     */
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

        System.out.println(" ");
        System.out.println("LEVEL 1: ");
        System.out.println("HEALTH: " + player.getHealth());
    }
}
