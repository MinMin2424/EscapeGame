/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;

/**
 * Represents the player character in the game.
 */
public class Player {
    private int health; // Player's health points
    private final int MAX_HEALTH = 5; // Maximum health points
    private int playerX;
    private int playerY;
    private final Inventory inventory; // Player's inventory

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
    public void increaseHealth() {
        for (Item item: inventory.getItems()) {
            if (item.getName().equals(CraftingItems.POTION.name()) && getHealth() < MAX_HEALTH) {
                inventory.removeItem(item);
                setHealth(getHealth() + 1);
                System.out.println("Použil jsi POTION a získal jsi 1 život!");
                return;
            }
        }
        System.out.println("Nemáš žádný POTION nebo máš plné životy!");
    }

    /**
     * Decrease the player's health points by the specified amount.
     * @param damage The amount of damage to be dealt.
     */
    public void loseHealth(int damage) {
        health -= damage;
    }

    /**
     * Decrease the player's health points by 1 when colliding with an obstacle.
     * @param obstacleType The type of obstacle encountered.
     */
    public void collideWithObstacle(GameObjects obstacleType) {
        loseHealth(1);
        System.out.println("Dotek s " + obstacleType + " způsobil ztrátu životů.");
    }

    /**
     * Collects an item when colliding with it.
     * @param itemType The type of item collected.
     */
    public void collideWithItem(GameItems itemType) {
        collectItem(itemType.name());
    }


    /**
     * Adds a new item to the player's inventory.
     * @param item The name of the item to be added.
     */
    private void collectItem(String item) {
        inventory.addItem(new Item(item, 1));
        System.out.println("Předmět " + item + " byl přidán do inventáře.");
    }

    /**
     * Uses the specified item from the inventory.
     * @param itemName The name of the item to be used.
     */
    public void useItem(String itemName) {
        for (Item item: inventory.getItems()) {
            if (item.getName().equals(itemName)) {
                inventory.removeItem(item);
                System.out.println("Předmět " + item.getName() + " byl použit.");
                break;
            }
        }
    }

    /**
     * Crafts a new item and adds it to the inventory.
     * @param craftingItems The type of the item to be crafted.
     */
    public void craftItem(CraftingItems craftingItems) {
        Item newItem = ItemFactory.createItem(craftingItems, inventory);
        if (newItem != null) {
            inventory.addItem(newItem);
            System.out.println("Vytvořen nový předmět: " + newItem.getName());
        } else {
            System.out.println("Neznámý typ předmětu nebo není dostatek surovin k tvorbě předmětu.");
        }
    }

}
