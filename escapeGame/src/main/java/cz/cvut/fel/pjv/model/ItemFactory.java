/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;

import java.util.Objects;

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

        boolean hasRequiredItems = true;
        for (int itemCode: requiredItems) {
            GameItems requiredItem = GameItems.getByCode(itemCode);
            boolean found = false;
            for (Item inventoryItem : inventory.getItems()) {
                assert requiredItem != null;
                if (inventoryItem.getName().equals(requiredItem.name())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                hasRequiredItems = false;
                break;
            }
        }

        if (hasRequiredItems) {
            for (int itemCode: requiredItems) {
                String itemName = Objects.requireNonNull(GameItems.getByCode(itemCode)).name();
                Item requiredItem = new Item(itemName, 1);
                inventory.removeItem(requiredItem);
            }

            return switch (craftingItems) {
                case SWORD -> new Item(CraftingItems.SWORD.name(), 1);
                case POTION -> new Item(CraftingItems.POTION.name(), 1);
            };
        } else {
            return null;
        }
    }
}
