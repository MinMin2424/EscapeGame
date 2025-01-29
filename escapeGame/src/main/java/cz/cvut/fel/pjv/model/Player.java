package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;

public class Player {
    private int health; // Počet životů hráče
    private final int MAX_HEALTH = 5;
    private int playerX;
    private int playerY;
    private final Inventory inventory; // Inventáře hráče

    public Player(int playerX, int playerY) {
        this.health = MAX_HEALTH; // Počáteční počet životů
        this.playerX = playerX;
        this.playerY = playerY;
        this.inventory = new Inventory(); // Inicializace inventáře
    }

    // Metoda pro získání počtu životů
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public Inventory getInventory() {
        return inventory;
    }

    //Metoda pro zvýšení životů
    public void increaseHealth() {
        for (Item item: inventory.getItems()) {
            if (item.getName().equals(CraftingItems.POTION.name()) && getHealth() < MAX_HEALTH) {
                inventory.removeItem(item);
                setHealth(getHealth() + 1);
                System.out.println("Použil jsi POTION a získal jsi 1 život!");
                return;
            }
        }
        System.out.println("Nemáš žádný POTION nebo máš plné životy!");
    }

    // Metoda pro ztrátu životů
    public void loseHealth(int damage) {
        health -= damage;
    }

    // Metoda pro ztrátu životů při interakci s překážkami
    public void collideWithObstacle(GameObjects obstacleType) {
        loseHealth(1);
        System.out.println("Dotek s " + obstacleType + " způsobil ztrátu životů.");
    }

    // Metoda pro sbírání předmětů/surovin při interakci
    public void collideWithItem(GameItems itemType) {
        collectItem(itemType.name());
    }


    // Metoda pro sbírání předmětů
    private void collectItem(String item) {
        inventory.addItem(new Item(item, 1));
        System.out.println("Předmět " + item + " byl přidán do inventáře.");
    }

    // Metoda pro použití předmětu
    public void useItem(String itemName) {
        for (Item item: inventory.getItems()) {
            if (item.getName().equals(itemName)) {
                inventory.removeItem(item);
                System.out.println("Předmět " + item.getName() + " byl použit.");
                break;
            }
        }
    }

    // Metoda pro vytvoření nového předmětu a přidání jej do inventáře
    public void craftItem(CraftingItems craftingItems) {
        Item newItem = ItemFactory.createItem(craftingItems, inventory);
        if (newItem != null) {
            inventory.addItem(newItem);
            System.out.println("Vytvořen nový předmět: " + newItem.getName());
        } else {
            System.out.println("Neznámý typ předmětu nebo není dostatek surovin k tvorbě předmětu.");
        }
    }

}
