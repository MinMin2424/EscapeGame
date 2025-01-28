package cz.cvut.fel.pjv.gameObjects_Items;

public enum CraftingItems {

    SWORD(7,  new int[]{GameItems.ORE.getCode(), GameItems.ORE.getCode(), GameItems.ORE.getCode()}),
    POTION(8,  new int[]{GameItems.WATER_ITEM.getCode(), GameItems.HERB.getCode()});

    private final int code;
    private final int[] requiredItems;

    CraftingItems(int code, int[] requiredItems) {
        this.code = code;
        this.requiredItems = requiredItems;
    }

    public int getCode() {
        return code;
    }

    public int[] getRequiredItems() {
        return requiredItems;
    }

}

