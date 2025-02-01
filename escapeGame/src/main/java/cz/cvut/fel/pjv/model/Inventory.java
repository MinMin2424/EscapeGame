/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the inventory of the player, storing various items.
 */
public class Inventory {

    private final List<Item> items; // List of items in the inventory

    /**
     * Constructors a new Inventory object with an empty list of items.
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     * If the item already exists in the inventory, its quantity is updated.
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        if (item == null) {
            return;
        }
        boolean found = false;
        for (Item i: items) {
            if (i.getName().equals(item.getName())) {
                i.setQuantity(i.getQuantity() + item.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            items.add(item); // Add the item to the inventory.
        }
    }

    /**
     * Removes an item from the inventory.
     * If the quantity of the item is greater than 1, it decrements the quantity.
     * Otherwise, it removes the item.
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if (currentItem.getName().equals(item.getName())) {
                if (currentItem.getQuantity() > 1) {
                    currentItem.setQuantity(currentItem.getQuantity() - 1);
                } else {
                    items.remove(i);
                }
                break;
            }
        }
    }

    /**
     * Retrieves the list of items in the inventory.
     * @return The list of items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Retrieves the image name of an item based on its code.
     * @param item The item whose image name is to be retrieved.
     * @return The image name of the item.
     */
    public String getImageName(Item item) {
        String itemName = item.getName();
        for (GameItems gameItems: GameItems.values()) {
            if (gameItems.name().equals(itemName)) {
                return gameItems.getImageName();
            }
        }
        for (CraftingItems craftingItem : CraftingItems.values()) {
            if (craftingItem.name().equals(itemName)) {
                return craftingItem.getImageName();
            }
        }
        return null;
    }

    /**
     * Converts the inventory to a string representation.
     * @return The string representation of the inventory.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item: items) {
            sb.append(item.getName()).append(": ").append(item.getQuantity()).append("\n");
        }
        return sb.toString();
    }


}
