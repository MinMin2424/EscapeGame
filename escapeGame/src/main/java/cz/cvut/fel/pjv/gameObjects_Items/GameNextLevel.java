package cz.cvut.fel.pjv.gameObjects_Items;

public enum GameNextLevel {
    NEXT_LEVEL(10);

    private final int code;

    GameNextLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

