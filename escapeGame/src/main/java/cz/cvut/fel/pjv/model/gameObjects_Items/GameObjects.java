/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.gameObjects_Items;

/**
 * Enumerates game objects in the game.
 */
public enum GameObjects {
    WALL(2, true, false, "wall.png"),
    GHOST(3, true, true, "ghost2.png"),
    FIRE(4, true, true, "fire.png"),
    WATER(5, true, true, "water_obstacle.png");
    private final int code; // Code representing the game object
    private final boolean isObstacle; // Indicates if the game object is an obstacle
    private final boolean isDamage; // Indicates if the game object causes damage
    private final String imageName; // Image name associated with the game object

    /**
     * Constructors a game object with the specified code, obstacle status, damage status and image name.
     * @param code The code representing the game object.
     * @param isObstacle Indicates if the game object is an obstacle.
     * @param isDamage Indicates if the game object causes damage.
     * @param imageName The image name associated with the game object.
     */
    GameObjects(int code, boolean isObstacle, boolean isDamage, String imageName) {
        this.code = code;
        this.isObstacle = isObstacle;
        this.isDamage = isDamage;
        this.imageName = imageName;
    }

    /**
     * Retrieves the code representing the game object.
     * @return The code representing the game object.
     */
    public int getCode() {
        return code;
    }

    /**
     * Retrieves the image name associated with the game object.
     * @return The image name associated with the game object.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Checks if the game object causes damage.
     * @return True if the game object causes damage, otherwise false.
     */
    public boolean isDamage() {
        return isDamage;
    }

    /**
     * Retrieves the game object based on the provided code.
     * @param code The code of the game object to retrieve.
     * @return The game object corresponding to the provided code, or null if not found.
     */
    public static GameObjects getByCode(int code) {
        for (GameObjects object : GameObjects.values()) {
            if (object.getCode() == code) {
                return object;
            }
        }
        return null;
    }
}
