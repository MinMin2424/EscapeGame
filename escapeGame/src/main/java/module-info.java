module cz.cvut.fel.pjv {
    requires javafx.controls;
    requires javafx.fxml;

    opens cz.cvut.fel.pjv.model.gameObjects_Items to javafx.fxml;
    exports cz.cvut.fel.pjv.model.gameObjects_Items;

    opens cz.cvut.fel.pjv.model.direction to javafx.fxml;
    exports cz.cvut.fel.pjv.model.direction;

    exports cz.cvut.fel.pjv.model;
    opens cz.cvut.fel.pjv.model to javafx.fxml;

    exports cz.cvut.fel.pjv.view;
    opens cz.cvut.fel.pjv.view to javafx.fxml;

    exports cz.cvut.fel.pjv;
    opens cz.cvut.fel.pjv to javafx.fxml;
}