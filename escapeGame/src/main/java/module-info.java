module cz.cvut.fel.pjv {
    requires javafx.controls;
    requires javafx.fxml;

    opens cz.cvut.fel.pjv to javafx.fxml;
    exports cz.cvut.fel.pjv;

    opens cz.cvut.fel.pjv.gameObjects_Items to javafx.fxml;
    exports cz.cvut.fel.pjv.gameObjects_Items;

    opens cz.cvut.fel.pjv.direction to javafx.fxml;
    exports cz.cvut.fel.pjv.direction;
}
