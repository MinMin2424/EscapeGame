package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerControllerTest {

    private Player player;
    private GameBoard gameBoard;
    private PlayerController playerController;

    /**
     * Initializes the test environment before each test case.
     */
    @BeforeEach
    void initData() {
        player = new Player();
        gameBoard = new GameBoard();
        playerController = new PlayerController(player, gameBoard);
    }

    /**
     * Tests the movement of the player in a valid direction.
     */
    @Test
    void testMove_ValidDirection() {
        player.setPlayerX(1);
        player.setPlayerY(1);
        playerController.move(Direction.RIGHT);
        Assertions.assertEquals(1, player.getPlayerX());
        Assertions.assertEquals(2, player.getPlayerY());
    }

    /**
     * Tests the movement of the player outside the game board boundaries.
     */
    @Test
    void testMove_OutsideBoard() {
        player.setPlayerX(0);
        player.setPlayerY(0);
        playerController.move(Direction.UP);
        Assertions.assertEquals(0, player.getPlayerX());
        Assertions.assertEquals(0, player.getPlayerY());
    }

    /**
     * Tests the collision with a wall while moving.
     */
    @Test
    void testMove_WallCollision() {
        player.setPlayerX(2);
        player.setPlayerY(2);
        gameBoard.getBoard()[2][3] = GameObjects.WALL.getCode();

        playerController.move(Direction.RIGHT);
        Assertions.assertEquals(2, player.getPlayerX());
        Assertions.assertEquals(2, player.getPlayerY());
    }

    /**
     * Tests the collision with an item while moving.
     */
    @Test
    void testMove_ItemCollision() {
        player.setPlayerX(2);
        player.setPlayerY(2);
        gameBoard.getBoard()[2][3] = GameItems.HERB.getCode();

        playerController.move(Direction.RIGHT);
        Assertions.assertEquals(2, player.getPlayerX());
        Assertions.assertEquals(3, player.getPlayerY());
    }

    /**
     * Tests the health check of the player when health is low.
     */
    @Test
    void testCheckPlayerHealth_LowHealth() {
        player.setHealth(0);
        Assertions.assertTrue(playerController.checkPlayerHealth());
    }

    /**
     * Tests the health check of the player when health is high.
     */
    @Test
    void testCheckPlayerHealth_HighHealth() {
        player.setHealth(20);
        Assertions.assertFalse(playerController.checkPlayerHealth());
    }

    /**
     * Tests the handling of proceeding to the next level with the key.
     */
    @Test
    void testHandleNextLevel_WithKey() {
        player.getInventory().addItem(new Item("KEY", 1));
        Assertions.assertTrue(playerController.handleNextLevel());
    }

    /**
     * Tests the handling of proceeding to the next level without the key.
     */
    @Test
    void testHandleNextLevel_NoKey() {
        Assertions.assertFalse(playerController.handleNextLevel());
    }

    /**
     * Tests the presence of an item in the player's inventory when the item exists.
     */
    @Test
    void testHasItem_ItemExists() {
        player.getInventory().addItem(new Item("WATER_ITEM", 1));
        Assertions.assertTrue(playerController.hasItem(GameItems.WATER_ITEM.name()));
    }

    /**
     * Tests the presence of an item in the player's inventory when the item does not exist.
     */
    @Test
    void testHasItem_ItemDoesNotExist() {
        Assertions.assertFalse(playerController.hasItem(GameItems.WATER_ITEM.name()));
    }

    /**
     * Tests the movement of the player to a new position on the game board.
     */
    @Test
    void testMovePlayer_ValidMove() {
        player.setPlayerX(1);
        player.setPlayerY(1);
        playerController.movePlayer(1, 1, 1, 2);
        Assertions.assertEquals(1, player.getPlayerX());
        Assertions.assertEquals(2, player.getPlayerY());
    }

    /**
     * Tests the collision of the player with fire while using the water item.
     */
    @Test
    void testCollideWithObjects_FireWithWaterItem() {
        player.getInventory().addItem(new Item(GameItems.WATER_ITEM.name(), 1));
        gameBoard.getBoard()[1][2] = GameObjects.FIRE.getCode();
        playerController.collideWithObjects(GameObjects.FIRE, 1, 2);
        Assertions.assertTrue(playerController.isRemoveFire());
    }

    /**
     * Tests the collision of the player with a ghost while using the sword item.
     */
    @Test
    void testCollideWithObjects_GhostWithSword() {
        player.getInventory().addItem(new Item(CraftingItems.SWORD.name(), 1));
        gameBoard.getBoard()[1][2] = GameObjects.GHOST.getCode();
        playerController.collideWithObjects(GameObjects.GHOST, 1, 2);
        Assertions.assertTrue(playerController.isSaved());
    }

}
