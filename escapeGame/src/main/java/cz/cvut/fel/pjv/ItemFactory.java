package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameItems;


public class ItemFactory {

    // Metoda pro vytvoření předmětu na základě zadaného typu
    public static Item createItem(CraftingItems craftingItems, Inventory inventory) {

        // Získání seznamu potřebných surovin pro vytvoření předmětu
        int[] requiredItems = craftingItems.getRequiredItems();

        // Kontrola, zda jsou v inventáři dostatečné suroviny
        boolean hasRequiredItems = true;
        for (int itemCode: requiredItems) {
            GameItems requiredItem = GameItems.getByCode(itemCode);
            boolean found = false;
            for (Item inventoryItem : inventory.getItems()) {
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

        // Pokud jsou všechny potřebné suroviny v inventáři, vytvoří se předmět
        if (hasRequiredItems) {
            // Odebrání surovin z inventáře
            for (int itemCode: requiredItems) {
                String itemName = GameItems.getByCode(itemCode).name();
                Item requiredItem = new Item(itemName, 1);
                inventory.removeItem(requiredItem);
            }

            // Vytvoření nového předmětu
            return switch (craftingItems) {
                case SWORD -> new Item(CraftingItems.SWORD.name(), 1);
                case POTION -> new Item(CraftingItems.POTION.name(), 1);
                default -> null;
            };
        } else {
            return null;
        }
    }

}
