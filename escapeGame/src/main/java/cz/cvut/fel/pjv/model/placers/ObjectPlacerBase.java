/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.placers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.gameData.GameData;

import java.io.File;
import java.io.IOException;

/**
 * Handles the placement of objects and items on the game board at the start of the game.
 * This is an abstract base class for specified object placers for different levels.
 *
 */
public abstract class ObjectPlacerBase {
    protected final GameBoard gameBoard;
    protected Player player;
    protected final PlayerController playerController;
    protected final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructs an ObjectPlacer object with the specified game board.
     * Initializes the player object and player controller.
     * @param gameBoard The game board where the objects and items will be placed.
     */
    public ObjectPlacerBase(GameBoard gameBoard) {
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
     * Sets the player object to the specified player.
     * @param player The player object to be set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Retrieves the game board.
     * @return The game board.
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Retrieves the player controller.
     * @return The player controller.
     */
    public PlayerController getPlayerController() {
        return playerController;
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
            if (gameData.player != null && (gameData.level == getLevel() || gameData.level == 2)) {
                this.player = gameData.player;
                int loadPlayerX = gameData.player.getPlayerX();
                int loadPlayerY = gameData.player.getPlayerY();
                player.setPlayerX(loadPlayerX);
                player.setPlayerY(loadPlayerY);
            } else {
                this.player = getDefaultPlayerPosition();
            }

        } catch (IOException e) {
            this.player = getDefaultPlayerPosition();
        }
    }

    /**
     * Retrieves the default player position.
     * Subclasses must implement this method to specify the default player position.
     * @return The default player object with the specified position.
     */
    protected abstract Player getDefaultPlayerPosition();

    /**
     * Starts the game by placing objects, obstacles, and items on the game board.
     * Subclasses must implement this method to handle the placement of objects and items specific to their levels.
     */
    public abstract void startGame();
    public abstract int getLevel();
}
