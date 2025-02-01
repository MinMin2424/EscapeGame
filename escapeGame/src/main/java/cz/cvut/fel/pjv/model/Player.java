/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;

/**
 * Represents the player character in the game.
 */
public class Player {

    private final System.Logger LOGGER = System.getLogger(Player.class.getName());
    private int health; // Player's health points
    private final int MAX_HEALTH = 5; // Maximum health points
    private int playerX;
    private int playerY;
    private final Inventory inventory; // Player's inventory

    /**
     * Default constructor for the Player class.
     * Initializes the player's health, position, and inventory with default values.
     */
    public Player() {
        this.health = MAX_HEALTH; // Initial health points
        this.playerX = 0; // Default X-coordinate
        this.playerY = 0; // Default Y-coordinate
        this.inventory = new Inventory(); // Initialize the inventory
        loggerINFO("playerCreated");
    }


    /**
     * Constructor a new Player object with the specified coordinates.
     * Initializes the player's health, position, and inventory.
     * @param playerX The initial x-coordinate of the player.
     * @param playerY The initial y-coordinate of the player.
     */
    public Player(int playerX, int playerY) {
        this.health = MAX_HEALTH; // Initial health points
        this.playerX = playerX;
        this.playerY = playerY;
        this.inventory = new Inventory(); // Initialize the inventory
        loggerINFO("playerCreatedPosition");
    }

    /**
     * Retrieves the player's current health points.
     * @return The current health points of the player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the player's health points to the specified value.
     * @param health The new health points of the player.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Retrieves the player's current x-coordinate.
     * @return The current x-coordinate of the player.
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Sets the player's x-coordinate to the specified value.
     * @param playerX The new x-coordinate of the player.
     */
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    /**
     * Retrieves the player's current y-coordinate.
     * @return The current y-coordinate of the player.
     */
    public int getPlayerY() {
        return playerY;
    }

    /**
     * Sets the player's y-coordinate to the specified value.
     * @param playerY The new y-coordinate of the player.
     */
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    /**
     * Retrieves the player's inventory.
     * @return The inventory object of the player.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Increase the player's health points by 1 if a POTION is found in the inventory.
     */
    public boolean increaseHealth() {
        for (Item item: inventory.getItems()) {
            if (item.getName().equals(CraftingItems.POTION.name()) && getHealth() < MAX_HEALTH) {
                inventory.removeItem(item);
                setHealth(getHealth() + 1);
                loggerINFO("increaseHealth");
                return true;
            }
        }
        loggerINFO("increaseHealthFalse");
        return false;
    }

    /**
     * Decrease the player's health points by the specified amount.
     * @param damage The amount of damage to be dealt.
     */
    public void loseHealth(int damage) {
        health -= damage;
        loggerINFO("loseHealth");
    }

    /**
     * Decrease the player's health points by 1 when colliding with an obstacle.
     */
    public void collideWithObstacle() {
        loseHealth(1);
        loggerINFO("collideWithObstacle");
    }

    /**
     * Collects an item when colliding with it.
     * @param itemType The type of item collected.
     */
    public void collideWithItem(GameItems itemType) {
        collectItem(itemType.name());
        loggerINFO("collideWithItem");
    }


    /**
     * Adds a new item to the player's inventory.
     * @param item The name of the item to be added.
     */
    private void collectItem(String item) {
        inventory.addItem(new Item(item, 1));
        loggerINFO("collectedItem");
    }

    /**
     * Uses the specified item from the inventory.
     * @param itemName The name of the item to be used.
     */
    public void useItem(String itemName) {
        for (Item item: inventory.getItems()) {
            if (item.getName().equals(itemName)) {
                inventory.removeItem(item);
                loggerINFO("useItem");
                break;
            }
        }
    }

    /**
     * Crafts a new item and adds it to the inventory.
     * @param craftingItems The type of the item to be crafted.
     */
    public boolean craftItem(CraftingItems craftingItems) {
        Item newItem = ItemFactory.createItem(craftingItems, inventory);
        if (newItem != null) {
            inventory.addItem(newItem);
            System.out.println(inventory);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Logs informational messages related to player actions and events.
     * @param info A string representing the specific type of information to log.
     */
    private void loggerINFO(String info) {
        String message = "";
        switch (info) {
            case "playerCreated" -> message += "Player created.";
            case "playerCreatedPosition" -> message += "Player created at position (" + getPlayerX() + ", " + getPlayerY() + ").";
            case "increaseHealth" -> message += "Used POTION and gained 1 health point.";
            case "increaseHealthFalse" -> message += "No POTION available or health is already full.";
            case "loseHealth" -> message += "Lost 1 health points.";
            case "collideWithObstacle" -> message += "Collided with obstacle can lose health point.";
            case "collideWithItem" -> message += "Collided with item.";
            case "collectedItem" -> message += "Collected item.";
            case "useItem" -> message += "Used item.";
        }
        LOGGER.log(System.Logger.Level.INFO, message);
    }
}
