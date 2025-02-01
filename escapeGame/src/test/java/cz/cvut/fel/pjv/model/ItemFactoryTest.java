package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemFactoryTest {

    private Inventory inventory;

    @BeforeEach
    void initData() {
        inventory = new Inventory();
    }

    /**
     * Tests the creation of an item with a null crafting item.
     */
    @Test
    void testCreateItem_NullCraftingItem() {
        Item craftedItem = ItemFactory.createItem(null, inventory);
        Assertions.assertNull(craftedItem);
    }

    /**
     * Tests the creation of an item with a null inventory.
     */
    @Test
    void testCreateItem_NullInventory() {
        Item craftedItem = ItemFactory.createItem(CraftingItems.POTION, inventory);
        Assertions.assertNull(craftedItem);
    }

    /**
     * Tests the successful creation of an item.
     */
    @Test
    void testCreateItem_Success() {
        inventory.addItem(new Item(GameItems.WATER_ITEM.name(), 2));
        inventory.addItem(new Item(GameItems.HERB.name(), 1));

        Item craftedItem = ItemFactory.createItem(CraftingItems.POTION, inventory);
        Assertions.assertNotNull(craftedItem);
        Assertions.assertEquals("POTION", craftedItem.getName());
    }

    /**
     * Tests the creation of an item with insufficient resources.
     */
    @Test
    void testCreateItem_InsufficientResources() {
        inventory.addItem(new Item(GameItems.WATER_ITEM.name(), 2));

        Item craftedItem = ItemFactory.createItem(CraftingItems.POTION, inventory);
        Assertions.assertNull(craftedItem);
    }

    /**
     * Tests the removal of required items from the inventory after crafting.
     */
    @Test
    void testRemoveRequiredItemsFromInventory() {
        inventory.addItem(new Item(GameItems.WATER_ITEM.name(), 2));
        inventory.addItem(new Item(GameItems.HERB.name(), 1));

        ItemFactory.createItem(CraftingItems.POTION, inventory);

        List<Item> itemList = inventory.getItems();
        Assertions.assertEquals(1, itemList.size());
    }

    /**
     * Tests the successful creation of a crafted item.
     */
    @Test
    void testCreateCraftedItem_Success() {
        Item craftedSword = ItemFactory.createCraftedItem(CraftingItems.SWORD);
        Item craftedPotion = ItemFactory.createCraftedItem(CraftingItems.POTION);

        Assertions.assertEquals(CraftingItems.SWORD.name(), craftedSword.getName());
        Assertions.assertEquals(CraftingItems.POTION.name(), craftedPotion.getName());
    }

}
