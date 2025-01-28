package cz.cvut.fel.pjv;

public class ItemFactory {

    // Metoda pro vytvoření předmětu na základě zadaného typu
    public static Item createItem(String type) {
        return switch (type) {
            case "SWORD" -> new Item("SWORD", 1);
            case "POTION" -> new Item("POTION", 1);
            default -> null;
        };
    }
}
