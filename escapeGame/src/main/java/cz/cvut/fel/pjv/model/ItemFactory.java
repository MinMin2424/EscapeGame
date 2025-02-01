/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;

import java.util.*;

/**
 * Represents a factory for creating items in the game.
 */
public class ItemFactory {

    private static final System.Logger LOGGER = System.getLogger(ItemFactory.class.getName());

    /**
     * Creates an item based on the specified crafting item type and inventory.
     * First retrieve the list of required resources for crafting the item.
     * Then check if the inventory has sufficient resources.
     * If all required resources are present in the inventory, create the item and remove the required resources from the inventory.
     * @param craftingItems The type of item to be crafted.
     * @param inventory The inventory containing required resources.
     * @return The created item, or null if creation failed.
     */
    public static Item createItem(CraftingItems craftingItems, Inventory inventory) {
        if (inventory == null || craftingItems == null) {
            loggerERROR();
            return null;
        }

        int[] requiredItems = craftingItems.getRequiredItems();
        String[] items_inventory = getItemName(inventory);

        List<String> itemList = new ArrayList<>(Arrays.asList(items_inventory));

        if (!hasRequiredItems(itemList, requiredItems)) {
            loggerINFO("hasNotRequiredItems");
            return null;
        }

        removeRequiredItemsFromInventory(requiredItems, inventory);

        return createCraftedItem(craftingItems);

    }

    /**
     * Creates an array of item names in the inventory considering their quantity.
     * Each item is added to the array as many times as the quantity of the item in the inventory.
     * @param inventory The inventory containing the list of items.
     * @return An array of item names considering their quantity in the inventory.
     */
    private static String[] getItemName(Inventory inventory) {
        List<Item> inventoryItems = inventory.getItems();
        List<String> itemNameList = new ArrayList<>();
        for (Item item : inventoryItems) {
            int quantity = item.getQuantity();
            for (int i = 0; i < quantity; i++) {
                itemNameList.add(item.getName());
            }
        }
        return itemNameList.toArray(new String[0]);
    }

    /**
     * Checks if the inventory has all the required items for crafting.
     * @param itemList The list of items in the inventory.
     * @param requiredItems An array of required item codes.
     * @return {@code true} if the inventory contains all required items, {@code false} otherwise.
     */
    private static boolean hasRequiredItems(List<String> itemList, int[] requiredItems) {
        for (int itemCode : requiredItems) {
            GameItems requiredItem = GameItems.getByCode(itemCode);
            boolean found = false;

            for (int i = 0; i < itemList.size(); i++) {
                assert requiredItem != null;
                if (itemList.get(i).equals(requiredItem.name())) {
                    found = true;
                    itemList.remove(itemList.get(i));
                    break;
                }
            }

            if (!found) return false;
        }
        loggerINFO("hasRequiredItems");
        return true;
    }

    /**
     * Removes the required items from the inventory after crafting.
     * @param requiredItems An array of required item codes.
     * @param inventory The inventory from which items will be removed.
     */
    private static void removeRequiredItemsFromInventory(int[] requiredItems, Inventory inventory) {
        for (int itemCode : requiredItems) {
            String itemName = Objects.requireNonNull(GameItems.getByCode(itemCode)).name();
            Item requiredItem = new Item(itemName, 1);
            inventory.removeItem(requiredItem);
        }
        loggerINFO("itemsRemoved");
    }

    /**
     * Creates the crafted item based on the specified crafting item type.
     * @param craftingItems The type of the crafting item.
     * @return The crafted item.
     */
    protected static Item createCraftedItem(CraftingItems craftingItems) {
        loggerINFO("itemCreated");
        return switch (craftingItems) {
            case SWORD -> new Item(CraftingItems.SWORD.name(), 1);
            case POTION -> new Item(CraftingItems.POTION.name(), 1);
        };
    }

    /**
     * Logs informational messages related to player actions and events.
     * @param info A string representing the specific type of information to log.
     */
    private static void loggerINFO(String info) {
        String message = "";
        switch (info) {
            case "hasRequiredItems" -> message += "All required items are present in the inventory.";
            case "itemCreated" -> message += "Item successfully created.";
            case "itemsRemoved" -> message += "All required items have been removed from inventory.";
            case "hasNotRequiredItems" -> message += "Failed to create item. Required resources not available.";
        }
        LOGGER.log(System.Logger.Level.INFO, message);
    }

    /**
     * Logs error messages related to unexpected or erroneous situations.
     */
    private static void loggerERROR() {
        String message = "Invalid input parameters. Cannot create item.";
        LOGGER.log(System.Logger.Level.ERROR, message);
    }

}
