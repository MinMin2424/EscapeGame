package cz.cvut.fel.pjv.gameObjects_Items;

public enum GameItems {

    HERB(6, "herb.png"),
    ORE(7, "ore.png"),
    WATER_ITEM(8, "water_item.png"),
    KEY(9, "key.png");

    private final int code;
    private final String imageName;

    GameItems(int code, String imageName) {
        this.code = code;
        this.imageName = imageName;
    }

    public int getCode() {
        return code;
    }

    public String getImageName() {
        return imageName;
    }

    public static GameItems getByCode(int code) {
        for (GameItems item : GameItems.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}

