package cz.cvut.fel.pjv.model.gameObjects_Items;

public enum GhostPosition {

    GHOST1(4, 2, 2, 7),
    GHOST2(8, 5, 5,  9);

    private int positionX; // Initial X position
    private int positionY; // Initial Y position
    private int startPositionY; // Start Y position
    private int finalPositionY; // Final Y position

    /**
     * Constructor for GhostPosition enum.
     * @param positionX The initial X position of the ghost.
     * @param positionY The initial Y position of the ghost.
     * @param finalPositionY The final Y position of the ghost.
     */
    GhostPosition(int positionX, int positionY, int startPositionY, int finalPositionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.startPositionY = startPositionY;
        this.finalPositionY = finalPositionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public void setStartPositionY(int startPositionY) {
        this.startPositionY = startPositionY;
    }

    public int getFinalPositionY() {
        return finalPositionY;
    }

    public void setFinalPositionY(int finalPositionY) {
        this.finalPositionY = finalPositionY;
    }
}
