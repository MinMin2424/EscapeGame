module cz.cvut.fel.pjv {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens cz.cvut.fel.pjv.model.gameObjects_Items to javafx.fxml;
    exports cz.cvut.fel.pjv.model.gameObjects_Items;

    opens cz.cvut.fel.pjv.model.direction to javafx.fxml;
    exports cz.cvut.fel.pjv.model.direction;

    exports cz.cvut.fel.pjv.model;
    opens cz.cvut.fel.pjv.model to javafx.fxml, com.fasterxml.jackson.databind;

    exports cz.cvut.fel.pjv.view;
    opens cz.cvut.fel.pjv.view to javafx.fxml;

    exports cz.cvut.fel.pjv;
    opens cz.cvut.fel.pjv to javafx.fxml;

    exports cz.cvut.fel.pjv.save;
    exports cz.cvut.fel.pjv.view.renders;
    opens cz.cvut.fel.pjv.view.renders to javafx.fxml;
}