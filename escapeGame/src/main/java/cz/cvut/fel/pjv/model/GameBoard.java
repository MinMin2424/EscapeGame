package cz.cvut.fel.pjv.model;


import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;

public class GameBoard {
    private final int[][] board; // Herní pole
    private final int NUMBER_OF_SQUARES = 10;
    private final int TILE_DIM = 64; // in pixels

    public GameBoard() {
        this.board = new int[NUMBER_OF_SQUARES][NUMBER_OF_SQUARES]; // Inicializace herního pole
        initializeBoard();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getSize() {
        return NUMBER_OF_SQUARES * TILE_DIM;
    }

    public int getTileDim() {
        return TILE_DIM;
    }

    // Metoda pro inicializaci herního pole
    private void initializeBoard() {
        // Naplnění herního pole
        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARES; j++) {
                board[i][j] = 0; // Nastavení výchozí hodnoty pole
            }
        }
    }

    // Metoda pro umístění překážky na herní pole
    public void placeObject(GameObjects gameObjects, int x, int y) {
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameObjects.getCode();
        }
    }

    // Metoda pro umístění postavu na herní pole
    public void placePlayer(Player player) {
        int x = player.getPlayerX();
        int y = player.getPlayerY();
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = 1;
        }
    }

    // Metoda pro umístění surovin na herní pole
    public void placeItem(GameItems gameItems, int x, int y) {
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameItems.getCode();
        }
    }

    // Metoda pro umístění pole do dalšího levelu
    public void placeNextLevel(GameNextLevel gameNextLevel, int x, int y) {
        if (x >= 0 && x < NUMBER_OF_SQUARES && y >= 0 && y < NUMBER_OF_SQUARES) {
            board[x][y] = gameNextLevel.getCode();
        }
    }

    // Metoda pro vykreslení herního pole
    public void drawBoard() {
        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            for (int j = 0; j < NUMBER_OF_SQUARES; j++) {
                if (board[i][j] == 1) {
                    System.out.print("P  ");
                } else {
                    if (board[i][j] == GameObjects.WALL.getCode()) {
                        System.out.print("X  "); // Zeď
                    } else if (board[i][j] == GameObjects.GHOST.getCode()) {
                        System.out.print("G  "); // Duch
                    } else if (board[i][j] == GameObjects.FIRE.getCode()) {
                        System.out.print("F  "); // Oheň
                    } else if (board[i][j] == GameObjects.WATER.getCode()) {
                        System.out.print("W  "); // Voda
                    } else if (board[i][j] == GameItems.HERB.getCode()) {
                        System.out.print("H  "); // Bylinka
                    } else if (board[i][j] == GameItems.ORE.getCode()) {
                        System.out.print("O  "); // Kovová ruda
                    } else if (board[i][j] == GameItems.WATER_ITEM.getCode()) {
                        System.out.print("WI "); // Voda - surovina
                    } else if (board[i][j] == GameItems.KEY.getCode()) {
                        System.out.print("KE "); // Klíč
                    } else if (board[i][j] == GameNextLevel.NEXT_LEVEL.getCode()) {
                        System.out.println("NL "); // Next Level
                    } else {
                        System.out.print("-  "); // Prázdné pole
                    }
                }
            }
            System.out.println(); // Nový řádek po každém řádku herního pole
        }
    }
}
