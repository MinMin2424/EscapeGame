package cz.cvut.fel.pjv;

public class Item {
    private final String name;
    private int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Metoda pro zjištění názvu předmětu
    public String getName() {
        return name;
    }

    // Metoda pro zjištění množství předmětu
    public int getQuantity() {
        return quantity;
    }

    // Metoda pro nastavení množství předmětu
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

