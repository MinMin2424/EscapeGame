package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class InventoryTest {

    Inventory inventory;
    Item mockItem;

    @BeforeEach
    void initData() {
        inventory = new Inventory();
        mockItem = Mockito.mock(Item.class);
    }

    /**
     * Tests adding a new item to the inventory.
     */
    @Test
    void testAddItem_NewItem() {
        when(mockItem.getName()).thenReturn("KEY");
        when(mockItem.getQuantity()).thenReturn(3);
        inventory.addItem(mockItem);
        Assertions.assertTrue(inventory.getItems().contains(mockItem));
    }

    /**
     * Tests adding an existing item to the inventory.
     */
    @Test
    void testAddItem_ExistingItem() {
        when(mockItem.getName()).thenReturn("ORE");
        when(mockItem.getQuantity()).thenReturn(4);
        inventory.addItem(mockItem);

        Item newItem = Mockito.mock(Item.class);
        when(newItem.getName()).thenReturn("ORE");
        when(newItem.getQuantity()).thenReturn(5);
        inventory.addItem(newItem);

        List<Item> listItems = inventory.getItems();
        Assertions.assertEquals(1, listItems.size());
    }

    /**
     * Tests adding a null item to the inventory.
     */
    @Test
    void testAddItem_NullItem() {
        inventory.addItem(null);
        Assertions.assertEquals(0, inventory.getItems().size());
    }

    /**
     * Tests removing an item from the inventory when its quantity is greater than 1.
     */
    @Test
    void testRemoveItem_QuantityGreaterThanOne() {
        Item item = new Item("COIN", 2);
        inventory.addItem(item);
        inventory.removeItem(new Item("COIN", 1));
        int expectedQuantity = 1;
        int actualQuantity = inventory.getItems().getFirst().getQuantity();
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }

    /**
     * Tests removing an item from inventory when it is not present.
     */
    @Test
    void testRemoveItem_ItemNotPresent() {
        Item item1 = new Item("ORE", 1);
        Item item2 = new Item("HERB", 1);
        inventory.addItem(item1);

        inventory.removeItem(item2);
        List<Item> listItems = inventory.getItems();
        Assertions.assertEquals(1, listItems.size());
        Assertions.assertFalse(listItems.contains(item2));
    }

    /**
     * Tests removing an item from inventory when its quantity is 1.
     */
    @Test
    void testRemoveItem_QuantityItemEqualsOne() {
        when(mockItem.getName()).thenReturn("HERB");
        when(mockItem.getQuantity()).thenReturn(1);
        inventory.addItem(mockItem);
        inventory.removeItem(mockItem);
        List<Item> listItems = inventory.getItems();
        Assertions.assertEquals(0, listItems.size());
    }

    /**
     * Tests if the getImageName method returns the correct image name
     * for an item that belongs to the GameItems enum.
     */
    @Test
    void testGetImageName_GameItems() {
        Item item = new Item("KEY", 1);
        String expected = GameItems.KEY.getImageName();
        String actual = inventory.getImageName(item);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests if the getImageName method returns the correct image name
     * for an item that belongs to the CraftingItems enum.
     */
    @Test
    void testGetImageName_CraftingItems() {
        Item item = new Item("SWORD", 1);
        String expected = CraftingItems.SWORD.getImageName();
        String actual = inventory.getImageName(item);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests if the getImageName method returns null
     * for an item that does not belong to any enum.
     */
    @Test
    void testGetImageName_NonExistentItem() {
        Item item = new Item("SHIELD", 1);
        String actualImageName = inventory.getImageName(item);
        Assertions.assertNull(actualImageName);
    }
}
