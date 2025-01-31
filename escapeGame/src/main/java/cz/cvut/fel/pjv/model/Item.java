/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

/**
 * Represents an item in the game.
 */
public class Item {
    private final String name; // Name of the item
    private int quantity; // Quantity of the item

    /**
     * Default constructor for the Item class.
     * Initializes the item name to an empty string and quantity to 0.
     */
    public Item() {
        this.name = "";
        this.quantity = 0;
    }

    /**
     * Constructors a new Item object with a specified name and quantity.
     * @param name The name of the item.
     * @param quantity The quantity of the item.
     */
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Retrieves the name of the item.
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the quantity of the item.
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity The new quantity of the item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
