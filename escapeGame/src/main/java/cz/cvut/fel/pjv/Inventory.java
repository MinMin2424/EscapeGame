package cz.cvut.fel.pjv;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Metoda pro přidání předmětu do inventáře
    public void addItem(Item item) {
        boolean found = false;
        for (Item i: items) {
            if (i.getName().equals(item.getName())) {
                i.setQuantity(i.getQuantity() + item.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            items.add(item);// Přidání předmětu do inventáře
        }
    }

    // Metoda pro odebrání předmětu z inventáře
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

    // Metoda k ověření, zda je předmět obsažen v inventáři
    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    // Metoda pro získání seznamu předmětů v inventáři
    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item: items) {
            sb.append(item.getName()).append(": ").append(item.getQuantity()).append("\n");
        }
        return sb.toString();
    }
}

