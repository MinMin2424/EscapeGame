package cz.cvut.fel.pjv.gameObjects_Items;

public enum GameItems {

    HERB(6),
    ORE(7),
    WATER_ITEM(8),
    KEY(9);

    private final int code;

    GameItems(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
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

