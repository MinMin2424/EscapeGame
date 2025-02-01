/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.gameObjects_Items;

/**
 *  Enumerates crafting items in the game.
 */
public enum CraftingItems {

    SWORD(11,  new int[]{GameItems.ORE.getCode(), GameItems.ORE.getCode(), GameItems.ORE.getCode()}, "sword2.png"),
    POTION(12,  new int[]{GameItems.WATER_ITEM.getCode(), GameItems.HERB.getCode()}, "potion2.png");

    private final int code; // Code representing the crafting item
    private final int[] requiredItems; // Array of required items for crafting
    private final String imageName; // Image name associated with the crafting item

    /**
     * Constructors a crafting item with the specified code, required items, and image name.
     * @param code The code representing the crafting item.
     * @param requiredItems The array of required items for crafting.
     * @param imageName The image name associated with the crafting item.
     */
    CraftingItems(int code, int[] requiredItems, String imageName) {
        this.code = code;
        this.requiredItems = requiredItems;
        this.imageName = imageName;
    }

    /**
     * Retrieves the code representing the crafting item.
     * @return The code representing the crafting item.
     */
    public int getCode() {
        return code;
    }

    /**
     * Retrieves the array of required items for crafting.
     * @return The array of required items for crafting.
     */
    public int[] getRequiredItems() {
        return requiredItems;
    }

    /**
     * Retrieves the image name associated with the crafting item.
     * @return The image name associated with the crafting item.
     */
    public String getImageName() {
        return imageName;
    }
}
