/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.model.gameObjects_Items;

/**
 * Enum representing the positions of different ghosts.
 */
public enum GhostPosition {
    GHOST1(4, 2, 7),
    GHOST2(8, 5, 9);

    private final int positionX; // Initial X position
    private final int positionY; // Initial Y position
    private final int finalPositionY; // Final Y position

    /**
     * Constructor for GhostPosition enum.
     * @param positionX The initial X position of the ghost.
     * @param positionY The initial Y position of the ghost.
     * @param finalPositionY The final Y position of the ghost.
     */
    GhostPosition(int positionX, int positionY, int finalPositionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.finalPositionY = finalPositionY;
    }

    /**
     * Returns the initial X position of the ghost.
     * @return The initial X position.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Returns the initial Y position of the ghost.
     * @return The initial Y position.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Returns the final Y position of the ghost.
     * @return The final Y position.
     */
    public int getFinalPositionY() {
        return finalPositionY;
    }
}
