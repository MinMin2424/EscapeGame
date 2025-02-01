/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;

/**
 * Controls the player's movement and interactions in the game.
 */
public class PlayerController {

    private final Player player; // The controlled player
    private final GameBoard gameBoard; // The game board
    public boolean transition;
    public boolean isSaved;

    /**
     * Constructors a new PlayerController with a specified player and game board.
     * @param player The player object to control.
     * @param gameBoard The game board object.
     */
    public PlayerController(Player player, GameBoard gameBoard) {
        this.player = player;
        this.gameBoard = gameBoard;
    }

    /**
     * Indicates whether the game is currently in transition to another level.
     * @return True if the game is in transition to another level, otherwise false.
     */
    public boolean isTransition() {
        return transition;
    }

    /**
     * Sets the transition state of the game.
     * @param transition transition true to indicate that the game is transitioning to another level, false otherwise.
     */
    public void setTransition(boolean transition) {
        this.transition = transition;
    }

    /**
     * Indicates whether the player's progress in the game is currently saved.
     * @return True if the player's progress is saved, otherwise false.
     */
    public boolean isSaved() {
        return isSaved;
    }

    /**
     * Sets the saved state of the player's progress in the game.
     * @param saved true to indicate that the player's progress is saved, false otherwise.
     */
    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    /**
     * Moves the player in the specified direction.
     * @param direction The direction in which the player should move.
     */
    public void move(Direction direction) {

        // Current position of the player
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        // Check if the player has enough health for the next move
        if (checkPlayerHealth()) {
            return;
        }

        // New position of the player
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case UP:
                newX--;
                break;
            case DOWN:
                newX++;
                break;
            case LEFT:
                newY--;
                break;
            case RIGHT:
                newY++;
                break;
            default:
                System.out.println("Neznámý směr pohybu.");
                return;
        }

        // Check if the new position is outside the game board
        if (newX < 0 || newX >= gameBoard.getBoard().length || newY < 0 || newY >= gameBoard.getBoard()[0].length) {
            return;
        }

        int objectCode = gameBoard.getBoard()[newX][newY];

        if (objectCode == GameObjects.WALL.getCode()) {
            return; // Player cannot pass through a wall
        }

        if (objectCode == GameNextLevel.NEXT_LEVEL.getCode()) {
            if (!handleNextLevel()) {
                return;
            }

            setTransition(true);
            return;
        }

        GameObjects object = GameObjects.getByCode(objectCode);
        if (object != null && object.isDamage()) {

            if (object == GameObjects.FIRE && hasItem(GameItems.WATER_ITEM.name())) {
                player.useItem(GameItems.WATER_ITEM.name());
                System.out.println("Použil jsi WATER_ITEM k zhasnutí ohně.");
                gameBoard.getBoard()[newX][newY] = 0; // Remove the fire

            } else if (object == GameObjects.GHOST && hasItem(CraftingItems.SWORD.name())) {
                player.useItem(CraftingItems.SWORD.name());
                setSaved(true);
                System.out.println("Použil jsi SWORD k boji s duchem.");
                gameBoard.getBoard()[newX][newY] = 0;

            } else {
                player.collideWithObstacle(object);
            }

        } else {
            GameItems item = GameItems.getByCode(objectCode);
            if (item != null) {
                player.collideWithItem(item);
                gameBoard.getBoard()[newX][newY] = 0; // Remove the item
            }

            // Move the player to the new position
            movePlayer(currentX, currentY, newX, newY);
        }
    }

    /**
     * Checks if the player has enough health for the next move.
     * @return True if the player has zero or lower health points, otherwise false.
     */
    private boolean checkPlayerHealth() {
        if (player.getHealth() <= 0) {
            System.out.println("Hráč nemá dostatek životů. Konec hry");
            return true;
        }
        return false;
    }

    /**
     * Checks if the player can proceed to the next level.
     * @return True if the player has the key in the inventory, otherwise false.
     */
    private boolean handleNextLevel() {
        if (hasItem(GameItems.KEY.name())) {
            System.out.println("Hráč má klíč v inventáři. Přechod na další level ...");
            return true;
        }
        // Player does not have the key in the inventory
        System.out.println("Hráč nemá klíč v inventáři. Nelze přejít na další level.");
        return false;
    }

    /**
     * Checks if the specified item is in the player's inventory
     * @param itemName The name of the item to check.
     * @return True if the item is in the inventory, otherwise false.
     */
    private boolean hasItem(String itemName) {
        Inventory inventory = player.getInventory();
        for (Item item : inventory.getItems()) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the player to the new position on the game board.
     * @param currentX The current x-coordinate of the player.
     * @param currentY The current y-coordinate of the player.
     * @param newX The new x-coordinate of the player.
     * @param newY The new y-coordinate of the player
     */
    private void movePlayer(int currentX, int currentY, int newX, int newY) {
        gameBoard.getBoard()[currentX][currentY] = 0; // Remove the player from the current position
        player.setPlayerX(newX); // Set the new X-coordinate of the player
        player.setPlayerY(newY); // Set the new Y-coordinate of the player
        gameBoard.placePlayer(player); // Place the player at the new position
    }
}
