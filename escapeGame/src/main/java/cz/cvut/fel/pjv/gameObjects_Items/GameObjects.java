package cz.cvut.fel.pjv.gameObjects_Items;

public enum GameObjects {
    WALL(2, true, false),
    GHOST(3, true, true),
    FIRE(4, true, true),
    WATER(5, true, true);
    private final int code;
    private final boolean isObstacle;
    private final boolean isDamage;

    GameObjects(int code, boolean isObstacle, boolean isDamage) {
        this.code = code;
        this.isObstacle = isObstacle;
        this.isDamage = isDamage;
    }

    public int getCode() {
        return code;
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

