/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.PlayerController;

public class ObjectPlacer_Level2 {

    public void startGame() {
        GameBoard gameBoard = new GameBoard();
        Player player = new Player(9, 0);
        PlayerController playerController = new PlayerController(player, gameBoard);

        gameBoard.placePlayer(player);

        System.out.println(" ");
        System.out.println("LEVEL 2: ");
        gameBoard.drawBoard();
    }
}
