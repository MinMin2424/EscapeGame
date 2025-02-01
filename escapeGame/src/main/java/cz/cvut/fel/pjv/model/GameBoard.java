/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;

/**
 * Represents the game board for a simple 2D game.
 */
public class GameBoard {

    private static final System.Logger LOGGER = System.getLogger(GameBoard.class.getName());
    private int[][] board; // Game board
    private final int NUMBER_OF_SQUARES = 10; // Number of squares in each dimension
    private final int TILE_DIM = 64; // Tile dimension in pixels

    /**
     * Constructor a new GameBoard object.
     * Initializes the game board and populates it with a default values.
     */
    public GameBoard() {
        this.board = new int[NUMBER_OF_SQUARES][NUMBER_OF_SQUARES]; // Initialize the game board
        initializeBoard();
        LOGGER.log(System.Logger.Level.INFO, "Game board is created");
    }

    /**
     * Retrieves the game board.
     * @return The 2D array representing the game board.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Sets the game board to the specified array.
     * @param board Thr array representing the game board to be set.
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * Gets the size of the game board.
     * @return The size of the game board in pixels.
     */
    public int getSize() {
        return NUMBER_OF_SQUARES * TILE_DIM;
    }

    /**
     * Gets the dimension of a single tile on the game board.
     * @return The dimension of a single tile in pixels.
     */
    public int getTileDim() {
        return TILE_DIM;
    }

    /**
     * Initialize the game board with default values.
     * Each cell of the board is set to 0.
     */
    private void initializeBoard() {
        // Naplnění herního pole
        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARES; j++) {
                board[i][j] = 0; // Nastavení výchozí hodnoty pole
            }
        }
    }

    /**
     * Place a game object on the game board at the specified coordinates.
     * @param gameObjects the Game object to be placed
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void placeObject(GameObjects gameObjects, int x, int y) {
        if (gameObjects == null) {
            loggerERROR("nullObject");
            throw new IllegalArgumentException("GameObjects cannot be null.");
        }
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameObjects.getCode();
        } else {
            loggerERROR("objectOutOfBounds");
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
    }

    /**
     * Places a player character on the game board at the specified coordinates.
     * @param player The player character to be placed.
     */
    public void placePlayer(Player player) {
        int x = player.getPlayerX();
        int y = player.getPlayerY();
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = 1;
        } else {
            loggerERROR("playerOutOfBounds");
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
    }

    /**
     * Places a game item on the game board at the specified coordinates.
     * @param gameItems The game item to be places.
     * @param x The x-coordinates.
     * @param y The y-coordinates.
     */
    public void placeItem(GameItems gameItems, int x, int y) {
        if (gameItems == null) {
            loggerERROR("nullItem");
            throw new IllegalArgumentException("GameObjects cannot be null.");
        }
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameItems.getCode();
        } else {
            loggerERROR("itemOutForBounds");
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
    }

    /**
     * Places a next level indicator on the game board at the specified coordinates.
     * @param gameNextLevel The next level indicator.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void placeNextLevel(GameNextLevel gameNextLevel, int x, int y) {
        if (gameNextLevel == null) {
            loggerERROR("nullNextLevel");
            throw new IllegalArgumentException("GameObjects cannot be null.");
        }
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameNextLevel.getCode();
        } else {
            loggerERROR("nextLevelOutOfBounds");
            throw new IllegalArgumentException("Coordinates out of bounds.");
        }
    }

    /**
     * Logs error messages related to unexpected or erroneous situations.
     * @param error A string representing the specific type of error to log.
     */
    private void loggerERROR(String error) {
        String message = "";
        switch (error) {
            case "nullObject" -> message += "Attempted to place a null object on the game board.";
            case "objectOutOfBounds" -> message += "Attempted to place object out of bounds.";
            case "playerOutOfBounds" -> message += "Attempted to place player out of bounds.";
            case "nullItem" -> message += "Attempted to place a null item on the game board.";
            case "itemOutForBounds" -> message += "Attempted to place item out of bounds.";
            case "nullNextLevel" -> message += "Attempted to place a null next level on the game board.";
            case "nextLevelOutOfBounds" -> message += "Attempted to place next level out of bounds.";
        }
        LOGGER.log(System.Logger.Level.ERROR, message);
    }
}
