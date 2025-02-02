package cz.cvut.fel.pjv.model.processTesting;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Item;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerControllerProcessTest {

    // 3. process test
    // Initialize positions of player and item on game board.
    // Test if player is moved to the position of item; and item is removed from board and added to inventory.
    @Test
    void testMovePlayer_and_CollectItem() {

        // Initialize position
        Player player = new Player(2, 2);
        GameBoard gameBoard = new GameBoard();
        gameBoard.placePlayer(player);
        gameBoard.placeItem(GameItems.HERB, 2, 3);

        // Move player
        PlayerController playerController = new PlayerController(player, gameBoard);
        playerController.move(Direction.RIGHT);

        // Testing
        // if player is moved to the position of item and item is removed
        Assertions.assertEquals(1, gameBoard.getBoard()[2][3]);
        Assertions.assertNotEquals(GameItems.HERB.getCode(), gameBoard.getBoard()[2][3]);

        // if item is added to inventory
        List<Item> list = player.getInventory().getItems();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(GameItems.HERB.name(), list.getFirst().getName());
    }

    // 4. process test
    // When player has no health to play game, then player cannot move anywhere
    @Test
    void testMovePlayer_IfPlayerHasNoHealth() {

        // Initialize positions
        Player player = new Player(2, 2);
        GameBoard gameBoard = new GameBoard();
        gameBoard.placePlayer(player);
        PlayerController playerController = new PlayerController(player, gameBoard);

        // Set health to 0
        player.setHealth(0);

        // Move player
        playerController.move(Direction.UP);

        // Testing
        // If player health is 0
        Assertions.assertEquals(0, player.getHealth());
        Assertions.assertTrue(playerController.checkPlayerHealth());

        // If player is not moved anywhere
        Assertions.assertEquals(1, gameBoard.getBoard()[2][2]);
        Assertions.assertNotEquals(1, gameBoard.getBoard()[1][2]);
    }
}
