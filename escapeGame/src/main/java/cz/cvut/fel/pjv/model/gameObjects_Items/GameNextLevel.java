/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.gameObjects_Items;

/**
 * Enumerates the game object representing the transition to the next level.
 */
public enum GameNextLevel {
    NEXT_LEVEL(10, "next_level.png");

    private final int code; // Code representing the next level game object
    private final String imageName; // Image name associated with the next level game object

    /**
     * Constructors a game object representing the transition to the next level with the specified code and image name.
     * @param code The code representing the next level game object.
     * @param imageName The image name associated with the next level game object.
     */
    GameNextLevel(int code, String imageName) {
        this.code = code;
        this.imageName = imageName;
    }

    /**
     * Retrieves the code representing the next level game object.
     * @return  The code representing the next level game object.
     */
    public int getCode() {
        return code;
    }

    /**
     * Retrieves the image name associated with the next level game object.
     * @return The image name associated with the next level game object.
     */
    public String getImageName() {
        return imageName;
    }
}
