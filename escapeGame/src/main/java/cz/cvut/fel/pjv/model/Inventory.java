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

    private static final System.Logger LOGGER = System.getLogger(Inventory.class.getName());
    private final List<Item> items; // List of items in the inventory

    /**
     * Constructors a new Inventory object with an empty list of items.
     */
    public Inventory() {
        this.items = new ArrayList<>();
        loggerINFO("inventory");
    }

    /**
     * Adds an item to the inventory.
     * If the item already exists in the inventory, its quantity is updated.
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        if (item == null) {
            loggerERROR("add");
            return;
        }

        boolean found = false;
        for (Item i: items) {
            if (i.getName().equals(item.getName())) {
                i.setQuantity(i.getQuantity() + item.getQuantity());
                found = true;
                loggerINFO("addItem");
                break;
            }
        }
        if (!found) {
            items.add(item); // Add the item to the inventory.
            loggerINFO("addNewItem");
        }
    }

    /**
     * Removes an item from the inventory.
     * If the quantity of the item is greater than 1, it decrements the quantity.
     * Otherwise, it removes the item.
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        if (item == null) {
            loggerERROR("remove");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if (currentItem.getName().equals(item.getName())) {
                if (currentItem.getQuantity() > 1) {
                    currentItem.setQuantity(currentItem.getQuantity() - 1);
                } else {
                    items.remove(i);
                }
                loggerINFO("remove");
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
        if (item == null) {
            loggerERROR("nullItem");
            return null;
        }
        String itemName = item.getName();
        for (GameItems gameItems: GameItems.values()) {
            if (gameItems.name().equals(itemName)) return gameItems.getImageName();
        }
        for (CraftingItems craftingItem : CraftingItems.values()) {
            if (craftingItem.name().equals(itemName)) return craftingItem.getImageName();
        }
        loggerINFO("getNameNull");
        return null;
    }

    /**
     * Logs informational messages related to player actions and events.
     * @param info A string representing the specific type of information to log.
     */
    private void loggerINFO(String info) {
        String message = "";
        switch (info) {
            case "inventory" -> message += "Inventory created.";
            case "addItem" -> message += "Item added to inventory.";
            case "addNewItem" -> message += "New item added to inventory.";
            case "remove" -> message += "Item removed from inventory.";
            case "getNameNull" -> message += "Image name not found.";
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
            case "add" -> message += "Attempted to add null item to inventory.";
            case "remove" -> message += "Attempted to remove null item from inventory.";
            case "nullItem" -> message += "Attempted to get image name for null item.";
        }
        LOGGER.log(System.Logger.Level.ERROR, message);
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
