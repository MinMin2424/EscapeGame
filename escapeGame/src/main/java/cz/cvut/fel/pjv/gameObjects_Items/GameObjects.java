package cz.cvut.fel.pjv.gameObjects_Items;

public enum GameObjects {

    WALL(2, true, false, "wall.png"),
    GHOST(3, true, true, "ghost.png"),
    FIRE(4, true, true, "fire.png"),
    WATER(5, true, true, "water_obstacle.png");

    private final int code;
    private final boolean isObstacle;
    private final boolean isDamage;
    private final String imageName;

    GameObjects(int code, boolean isObstacle, boolean isDamage, String imageName) {
        this.code = code;
        this.isObstacle = isObstacle;
        this.isDamage = isDamage;
        this.imageName = imageName;
    }

    public int getCode() {
        return code;
    }

    public String getImageName() {
        return imageName;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public boolean isDamage() {
        return isDamage;
    }

    public static GameObjects getByCode(int code) {
        for (GameObjects object : GameObjects.values()) {
            if (object.getCode() == code) {
                return object;
            }
        }
        return null;
    }
}

