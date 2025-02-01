/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.gameData;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.view.placers.ObjectPlacerBase;

import java.io.File;
import java.io.IOException;

/**
 * Manages the saving and loading of game states.
 * This class provides methods to save the current game state to a file and load a saved game state from a file.
 * It uses the Jackson library for JSON serialization and deserialization.
 */
public class GameStateManager {

    /**
     * Saves the current game state to a file.
     * @param fileName The name of the file to save the game state to.
     * @param gameBoard The game board containing the current state of the game.
     * @param objectPlacer The object placer responsible for placing objects on the game board.
     */
    public static void saveGameState(String fileName, GameBoard gameBoard, ObjectPlacerBase objectPlacer) {

        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = new GameData();
        gameData.gameBoard = gameBoard.getBoard();
        gameData.player = objectPlacer.getPlayer();

        try {
            objectMapper.writeValue( new File(fileName), gameData);
            System.out.println("Status: Save game successful.");
        } catch (IOException e) {
            System.err.println("Cannot save game: " + e.getMessage());
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
            System.out.println("Status: Load game successful.");
        } catch (IOException e) {
            System.err.println("Cannot load game: " + e.getMessage());
        }
    }

}
