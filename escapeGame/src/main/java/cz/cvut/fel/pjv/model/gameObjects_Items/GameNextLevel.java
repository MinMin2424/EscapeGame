package cz.cvut.fel.pjv.model.gameObjects_Items;

public enum GameNextLevel {
    NEXT_LEVEL(10, "next_level.png");

    private final int code;
    private final String imageName;

    GameNextLevel(int code, String imageName) {
        this.code = code;
        this.imageName = imageName;
    }

    public int getCode() {
        return code;
    }

    public String getImageName() {
        return imageName;
    }
}
