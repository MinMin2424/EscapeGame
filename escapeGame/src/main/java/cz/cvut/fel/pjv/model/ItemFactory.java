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
        int[] requiredItems = craftingItems.getRequiredItems();
        String[] items_inventory = getItemName(inventory);

        List<String> itemList = new ArrayList<>(Arrays.asList(items_inventory));

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

            if (!found) {
                return null;
            }
        }

        for (int itemCode : requiredItems) {
            String itemName = Objects.requireNonNull(GameItems.getByCode(itemCode)).name();
            Item requiredItem = new Item(itemName, 1);
            inventory.removeItem(requiredItem);
        }

        return switch (craftingItems) {
            case SWORD -> new Item(CraftingItems.SWORD.name(), 1);
            case POTION -> new Item(CraftingItems.POTION.name(), 1);
        };

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

}
