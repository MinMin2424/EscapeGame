/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.gameData;

import cz.cvut.fel.pjv.model.Player;

/**
 * Represents the data structure used for saving and loading game data.
 */
public class GameData {
    public int[][] gameBoard; // Represents the game board
    public Player player; // Represents the player object containing player data
    public int level; // Represents the level

    // GHOST POSITION
    public int GHOST1_currentX, GHOST1_currentY, GHOST1_startY, GHOST1_finalY;
    public int GHOST2_currentX, GHOST2_currentY, GHOST2_startY, GHOST2_finalY;

}
