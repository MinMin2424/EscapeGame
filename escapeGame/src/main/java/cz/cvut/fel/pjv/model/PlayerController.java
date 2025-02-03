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

    private final System.Logger LOGGER = System.getLogger(PlayerController.class.getName());
    private final Player player; // The controlled player
    private final GameBoard gameBoard; // The game board
    public boolean transition;
    public boolean isSaved, removeFire, isVictory;

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
     * Moves the player in the specified direction.
     * @param direction The direction in which the player should move.
     */
    public void move(Direction direction) {

        // Current position of the player
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        // Check if the player has enough health for the next move
        if (checkPlayerHealth()) return;

        if (isVictory) return;

        // New position of the player
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case UP -> newX--;
            case DOWN -> newX++;
            case LEFT -> newY--;
            case RIGHT -> newY++;
            default -> {
                loggerERROR("unknownDirection");
                return;
            }
        }

        // Check if the new position is outside the game board
        if (newX < 0 || newX >= gameBoard.getBoard().length || newY < 0 || newY >= gameBoard.getBoard()[0].length) {
            loggerERROR("outOfBoard");
            return;
        }

        int objectCode = gameBoard.getBoard()[newX][newY];

        if (objectCode == GameObjects.WALL.getCode()) {
            loggerINFO("collideWithWall");
            return;
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
            collideWithObjects(object, newX, newY);

        } else {
            GameItems item = GameItems.getByCode(objectCode);
            collideWithItems(item, newX, newY);
            movePlayer(currentX, currentY, newX, newY);
        }
    }

    /**
     * Checks if the player has enough health for the next move.
     * @return True if the player has zero or lower health points, otherwise false.
     */
    public boolean checkPlayerHealth() {
        if (player.getHealth() <= 0) {
            loggerINFO("checkHealth");
            return true;
        }
        return false;
    }

    /**
     * Checks if the player can proceed to the next level.
     * @return True if the player has the key in the inventory, otherwise false.
     */
    protected boolean handleNextLevel() {
        if (hasItem(GameItems.KEY.name())) {
           loggerINFO("nextLevelTrue");
            return true;
        }
        loggerINFO("nextLevelFalse");
        return false;
    }

    /**
     * Checks if the specified item is in the player's inventory
     * @param itemName The name of the item to check.
     * @return True if the item is in the inventory, otherwise false.
     */
    protected boolean hasItem(String itemName) {
        Inventory inventory = player.getInventory();
        for (Item item : inventory.getItems()) {
            if (item.getName().equals(itemName)) return true;
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
    protected void movePlayer(int currentX, int currentY, int newX, int newY) {
        gameBoard.getBoard()[currentX][currentY] = 0; // Remove the player from the current position
        player.setPlayerX(newX); // Set the new X-coordinate of the player
        player.setPlayerY(newY); // Set the new Y-coordinate of the player
        gameBoard.placePlayer(player); // Place the player at the new position
    }

    /**
     * Handles collision between the player and objects on the game board.
     * @param object The game object the player collided with.
     * @param x The x-coordinate of the collision.
     * @param y The y-coordinate of the collision.
     */
    protected void collideWithObjects(GameObjects object, int x, int y) {
        if (object == GameObjects.FIRE && hasItem(GameItems.WATER_ITEM.name())) {
            player.useItem(GameItems.WATER_ITEM.name());
            setRemoveFire(true);
            loggerINFO("collideWithFire");
            gameBoard.getBoard()[x][y] = 0; // Remove the fire

        } else if (object == GameObjects.GHOST && hasItem(CraftingItems.SWORD.name())) {
            player.useItem(CraftingItems.SWORD.name());
            setSaved(true);
            loggerINFO("collideWithGhost");
            gameBoard.getBoard()[x][y] = 0;

        } else {
            player.collideWithObstacle();
        }
    }

    /**
     * Handles collision between the player and items on the game board.
     * @param item The game item the player collided with.
     * @param x The x-coordinate of the collision.
     * @param y The y-coordinate of the collision.
     */
    private void collideWithItems(GameItems item, int x, int y) {
        if (item != null) {
            player.collideWithItem(item);
            gameBoard.getBoard()[x][y] = 0; // Remove the item
        }
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
     * Checks if the player removes fire by using water item.
     * @return true if the player removes fire, false otherwise.
     */
    public boolean isRemoveFire() {
        return removeFire;
    }

    /**
     * Sets the flag indicating whether the player removes fire by using water item.
     * @param removeFire true if the player removes fire, false otherwise.
     */
    public void setRemoveFire(boolean removeFire) {
        this.removeFire = removeFire;
    }

    /**
     * Checks if the game has been won.
     * @return true if the game has been won, false otherwise.
     */
    public boolean isVictory() {
        return isVictory;
    }

    /**
     * Sets the status of the game victory.
     * @param victory true if the game has been won, false otherwise.
     */
    public void setVictory(boolean victory) {
        isVictory = victory;
    }

    /**
     * Logs informational messages related to player actions and events.
     * @param info A string representing the specific type of information to log.
     */
    private void loggerINFO(String info) {
        String message = "";
        switch (info) {
            case "collideWithWall" -> message += "Player tried to move into a wall.";
            case "nextLevelTrue" -> message += "Player has the key in the inventory. Proceeding to the next level...";
            case "nextLevelFalse" -> message += "Player does not have the key in the inventory. Cannot proceed to the next level.";
            case "checkHealth" -> message += "Player does not have enough health points. Game over.";
            case "collideWithFire" -> message += "Player used WATER_ITEM to save themselves.";
            case "collideWithGhost" -> message += "Player used SWORD to save themselves.";
        }
        LOGGER.log(System.Logger.Level.INFO, message);
    }

    /**
     * Logs error messages related to unexpected or erroneous situations.
     * @param error A string representing the specific type of error to log.
     */
    private void loggerERROR(String error) {
        String message = "";
        switch (error) {
            case "unknownDirection" -> message += "Unknown movement direction.";
            case "outOfBoard" -> message += "Player tried to move outside the game board.";
        }
        LOGGER.log(System.Logger.Level.ERROR, message);
    }
}
