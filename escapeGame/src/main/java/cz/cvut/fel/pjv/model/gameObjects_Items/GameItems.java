/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.gameObjects_Items;

/**
 * Enumerates game items in the game.
 */
public enum GameItems {

    HERB(6, "herb2.png"),
    ORE(7, "ore2.png"),
    WATER_ITEM(8, "water2.png"),
    KEY(9, "key2.png");

    private final int code; // Code representing the game item
    private final String imageName; // Image name associated with the game item.

    /**
     * Constructors a game item with the specified code and image name.
     * @param code The code representing the game item.
     * @param imageName The image name associated with the game item.
     */
    GameItems(int code, String imageName) {
        this.code = code;
        this.imageName = imageName;
    }

    /**
     * Retrieves the code representing the game item.
     * @return The code representing the game item.
     */
    public int getCode() {
        return code;
    }

    /**
     * Retrieves the image name associated with the game item.
     * @return The image name associated with the game item.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Retrieves the game item based on the provided code.
     * @param code The code of the game item to retrieve.
     * @return The game item corresponding to the provided code, or null if not found.
     */
    public static GameItems getByCode(int code) {
        for (GameItems item : GameItems.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
