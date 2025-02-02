package cz.cvut.fel.pjv.model.processTesting;

import cz.cvut.fel.pjv.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems.POTION;
import static cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems.SWORD;

public class ItemFactoryProcessTest {

    // 1. process test
    // When player has all required items in the inventory to craft new item.
    @Test
    void testCraftItem_playerHasRequiredItems() {

        Item item1 = new Item("HERB", 1);
        Item item2 = new Item("WATER_ITEM", 1);

        // Add items to inventory
        Player player = new Player();
        player.getInventory().addItem(item1);
        player.getInventory().addItem(item2);

        // Craft new item
        player.craftItem(POTION);

        // Testing
        // If POTION is in the inventory
        List<Item> list = player.getInventory().getItems();
        Assertions.assertEquals(POTION.name(), list.getFirst().getName());

        // If required items are removed and new item is added
        Assertions.assertEquals(1, list.size());
        Assertions.assertFalse(list.contains(item1));
        Assertions.assertFalse(list.contains(item2));

    }

    // 2. process test
    // When player has no required item to craft new item.
    @Test
    void testCraftItem_playerHasNoRequiredItem() {
        Item item1 = new Item("HERB", 1);
        Item item2 = new Item("WATER_ITEM", 1);

        // Add items to inventory
        Player player = new Player();
        player.getInventory().addItem(item1);
        player.getInventory().addItem(item2);

        // Craft new item
        player.craftItem(SWORD);

        // Testing
        // if craftItem() returns null
        Assertions.assertFalse(player.craftItem(SWORD));

        // If SWORD is not in the inventory
        List<Item> list = player.getInventory().getItems();
        Assertions.assertNotEquals(SWORD.name(), list.getLast().getName());

        // if items in the inventory are not removed
        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.contains(item1));
        Assertions.assertTrue(list.contains(item2));
    }
}
