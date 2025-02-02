/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.gameData;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition;
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
     *
     * @param fileName     The name of the file to save the game state to.
     * @param gameBoard    The game board containing the current state of the game.
     * @param objectPlacer The object placer responsible for placing objects on the game board.
     * @param level        The level of the game.
     * @param ghost1       The position of the first ghost.
     * @param ghost2       The position of the second ghost.
     */
    public static void saveGameState(String fileName, GameBoard gameBoard, ObjectPlacerBase objectPlacer, int level, GhostPosition ghost1, GhostPosition ghost2) {

        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = new GameData();
        gameData.gameBoard = gameBoard.getBoard();
        gameData.player = objectPlacer.getPlayer();
        gameData.level = level;
        saveGhostPosition(gameData, ghost1, ghost2);

        try {
            objectMapper.writeValue(new File(fileName), gameData);
            LOGGER.log(System.Logger.Level.INFO, "Status: Save game successful.");
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Failed to save game: " + e.getMessage());
        }

    }

    /**
     * Saves the position of ghosts to the GameData object.
     *
     * @param gameData The GameData object to store ghost positions.
     * @param ghost1   The position of the first ghost.
     * @param ghost2   The position of the second ghost.
     */
    private static void saveGhostPosition(GameData gameData, GhostPosition ghost1, GhostPosition ghost2) {
        gameData.GHOST1_currentX = ghost1.getPositionX();
        gameData.GHOST1_currentY = ghost1.getPositionY();
        gameData.GHOST1_startY = ghost1.getStartPositionY();
        gameData.GHOST1_finalY = ghost1.getFinalPositionY();

        gameData.GHOST2_currentX = ghost2.getPositionX();
        gameData.GHOST2_currentY = ghost2.getPositionY();
        gameData.GHOST2_startY = ghost2.getStartPositionY();
        gameData.GHOST2_finalY = ghost2.getFinalPositionY();
    }

    /**
     * Loads a game state from a file.
     *
     * @param fileName  The name of the file to load the game state from.
     * @param gameBoard The game board where the loaded state will be applied.
     */
    public static void loadGameState(String fileName, GameBoard gameBoard) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GameData gameData = objectMapper.readValue(new File(fileName), GameData.class);
            int playerX = gameData.player.getPlayerX();
            int playerY = gameData.player.getPlayerY();
            int GHOST1_posX = gameData.GHOST1_currentX;
            int GHOST1_posY = gameData.GHOST1_currentY;
            int GHOST2_posX = gameData.GHOST2_currentX;
            int GHOST2_posY = gameData.GHOST2_currentY;
            gameBoard.setBoard(modifyGameBoard(gameData.gameBoard, playerX, playerY, GHOST1_posX, GHOST1_posY, GHOST2_posX, GHOST2_posY));
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

    /**
     * Retrieves the position of a ghost from the specified file.
     *
     * @param fileName The name of the file containing the saved game data.
     * @param ghost    The ghost number.
     * @param position The position parameter to retrieve (currentX, currentY, startY, finalY).
     * @return The position value.
     */
    private static int getGhostPositionFromFile(String fileName, int ghost, String position) {
        ObjectMapper objectMapper = new ObjectMapper();
        int savedPosition = 0;
        try {
            GameData gameData = objectMapper.readValue(new File(fileName), GameData.class);
            switch (ghost) {
                case 1 -> {
                    savedPosition = switch (position) {
                        case "currentX" -> gameData.GHOST1_currentX;
                        case "currentY" -> gameData.GHOST1_currentY;
                        case "startY" -> gameData.GHOST1_startY;
                        case "finalY" -> gameData.GHOST1_finalY;
                        default -> savedPosition;
                    };
                }
                case 2 -> {
                    savedPosition = switch (position) {
                        case "currentX" -> gameData.GHOST2_currentX;
                        case "currentY" -> gameData.GHOST2_currentY;
                        case "startY" -> gameData.GHOST2_startY;
                        case "finalY" -> gameData.GHOST2_finalY;
                        default -> savedPosition;
                    };
                }
            }
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Failed to get saved level from file: " + e.getMessage());
        }
        return savedPosition;
    }

    /**
     * Retrieves the position of a ghost from the specified file.
     *
     * @param fileName      The name of the file containing the saved game data.
     * @param ghost         The ghost number.
     * @param ghostPosition The ghost position object to update.
     * @return The updated ghost position object.
     */
    public static GhostPosition getGhost(String fileName, int ghost, GhostPosition ghostPosition) {
        int currentX = getGhostPositionFromFile(fileName, ghost, "currentX");
        int currentY = getGhostPositionFromFile(fileName, ghost, "currentY");
        int startY = getGhostPositionFromFile(fileName, ghost, "startY");
        int finalY = getGhostPositionFromFile(fileName, ghost, "finalY");
        ghostPosition.setPositionX(currentX);
        ghostPosition.setPositionY(currentY);
        ghostPosition.setStartPositionY(startY);
        ghostPosition.setFinalPositionY(finalY);
        return ghostPosition;
    }

    /**
     * Modifies the game board by updating player and ghost positions and converting code 1 to code 0.
     *
     * @param gameBoard  The original game board.
     * @param positionX  The X coordinate of the player.
     * @param positionY  The Y coordinate of the player.
     * @param GHOST1_posX The X coordinate of the first ghost.
     * @param GHOST1_posY The Y coordinate of the first ghost.
     * @param GHOST2_posX The X coordinate of the second ghost.
     * @param GHOST2_posY The Y coordinate of the second ghost.
     * @return The modified game board.
     */
    protected static int[][] modifyGameBoard(int[][] gameBoard, int positionX, int positionY, int GHOST1_posX, int GHOST1_posY, int GHOST2_posX, int GHOST2_posY) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == 1 || gameBoard[i][j] == GameObjects.GHOST.getCode()) {
                    gameBoard[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (i == positionX && j == positionY) {
                    gameBoard[i][j] = 1;
                } else if (i == GHOST1_posX && j == GHOST1_posY || i == GHOST2_posX && j == GHOST2_posY) {
                    gameBoard[i][j] = GameObjects.GHOST.getCode();
                }
            }
        }
        return gameBoard;
    }

}
