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
    private final int[][] board; // Game board
    private final int NUMBER_OF_SQUARES = 10; // Number of squares in each dimension
    private final int TILE_DIM = 64; // Tile dimension in pixels

    /**
     * Constructor a new GameBoard object.
     * Initializes the game board and populates it with a default values.
     */
    public GameBoard() {
        this.board = new int[NUMBER_OF_SQUARES][NUMBER_OF_SQUARES]; // Initialize the game board
        initializeBoard();
    }

    /**
     * Retrieves the game board.
     * @return The 2D array representing the game board.
     */
    public int[][] getBoard() {
        return board;
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
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameObjects.getCode();
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
        }
    }

    /**
     * Places a game item on the game board at the specified coordinates.
     * @param gameItems The game item to be places.
     * @param x The x-coordinates.
     * @param y The y-coordinates.
     */
    public void placeItem(GameItems gameItems, int x, int y) {
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameItems.getCode();
        }
    }

    /**
     * Places a next level indicator on the game board at the specified coordinates.
     * @param gameNextLevel The next level indicator.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void placeNextLevel(GameNextLevel gameNextLevel, int x, int y) {
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameNextLevel.getCode();
        }
    }

    /**
     * Draws the game board, displaying the current state of the game.
     * Walls, characters, items, and other elements are represented bz specific symbols.
     */
    public void drawBoard() {
        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARES; j++) {
                if (board[i][j] == 1) {
                    System.out.print("P  ");
                } else {
                    if (board[i][j] == GameObjects.WALL.getCode()) {
                        System.out.print("X  "); // WALL
                    } else if (board[i][j] == GameObjects.GHOST.getCode()) {
                        System.out.print("G  "); // GHOST
                    } else if (board[i][j] == GameObjects.FIRE.getCode()) {
                        System.out.print("F  "); // FIRE
                    } else if (board[i][j] == GameObjects.WATER.getCode()) {
                        System.out.print("W  "); // WATER
                    } else if (board[i][j] == GameItems.HERB.getCode()) {
                        System.out.print("H  "); // HERB
                    } else if (board[i][j] == GameItems.ORE.getCode()) {
                        System.out.print("O  "); // ORE
                    } else if (board[i][j] == GameItems.WATER_ITEM.getCode()) {
                        System.out.print("WI "); // WATER_ITEM
                    } else if (board[i][j] == GameItems.KEY.getCode()) {
                        System.out.print("KE "); // KEY
                    } else if (board[i][j] == GameNextLevel.NEXT_LEVEL.getCode()) {
                        System.out.println("NL "); // NEXT_LEVEL
                    } else {
                        System.out.print("-  "); // EMPTY SPACE
                    }
                }
            }
            System.out.println(); // New line after each row.
        }
    }
}
