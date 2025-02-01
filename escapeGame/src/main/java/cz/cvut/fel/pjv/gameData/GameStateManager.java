/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.gameData;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.placers.ObjectPlacerBase;

import java.io.File;
import java.io.IOException;

/**
 * Manages the saving and loading of game states.
 * This class provides methods to save the current game state to a file and load a saved game state from a file.
 * It uses the Jackson library for JSON serialization and deserialization.
 */
public class GameStateManager {

    private static final System.Logger LOGGER = System.getLogger(GameStateManager.class.getName());

    /**
     * Saves the current game state to a file.
     * @param fileName The name of the file to save the game state to.
     * @param gameBoard The game board containing the current state of the game.
     * @param objectPlacer The object placer responsible for placing objects on the game board.
     */
    public static void saveGameState(String fileName, GameBoard gameBoard, ObjectPlacerBase objectPlacer, int level) {

        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = new GameData();
        gameData.gameBoard = gameBoard.getBoard();
        gameData.player = objectPlacer.getPlayer();
        gameData.level = level;

        try {
            objectMapper.writeValue( new File(fileName), gameData);
            LOGGER.log(System.Logger.Level.INFO, "Status: Save game successful.");
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Failed to save game: " + e.getMessage());
        }

    }

    /**
     * Loads a game state from a file.
     * @param fileName The name of the file to load the game state from.
     * @param gameBoard The game board where the loaded state will be applied.
     */
    public static void loadGameState(String fileName, GameBoard gameBoard) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GameData gameData = objectMapper.readValue(new File(fileName), GameData.class);
            gameBoard.setBoard(gameData.gameBoard);
            LOGGER.log(System.Logger.Level.INFO, "Status: Load game successful.");
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Failed to load game: " + e.getMessage());
        }
    }

    /**
     * Retrieves the saved level from the specified file.
     * This method reads the saved game data from the provided file using Jackson ObjectMapper.
     * If the file is successfully read and parsed, it returns the saved level.
     * If an IOException occurs during file reading or parsing, it logs an error message and returns 0.
     *
     * @param fileName The name of the file containing the saved game data.
     * @return The saved level retrieved from the file, or 0 if an error occurs.
     */
    public static int getSavedLevelFromFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GameData gameData = objectMapper.readValue(new File(fileName), GameData.class);
            return gameData.level;
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Failed to get saved level from file: " + e.getMessage());
            return 0;
        }
    }

}
