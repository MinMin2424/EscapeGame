package cz.cvut.fel.pjv.model.gameObjects_Items;

public enum CraftingItems {

    SWORD(7,  new int[]{GameItems.ORE.getCode(), GameItems.ORE.getCode(), GameItems.ORE.getCode()}, "sword.png"),
    POTION(8,  new int[]{GameItems.WATER_ITEM.getCode(), GameItems.HERB.getCode()}, "potion.png");

    private final int code;
    private final int[] requiredItems;
    private final String imageName;

    CraftingItems(int code, int[] requiredItems, String imageName) {
        this.code = code;
        this.requiredItems = requiredItems;
        this.imageName = imageName;
    }

    public int getCode() {
        return code;
    }

    public int[] getRequiredItems() {
        return requiredItems;
    }

    public String getImageName() {
        return imageName;
    }
}
