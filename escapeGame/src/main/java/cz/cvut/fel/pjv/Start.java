/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.view.levels.Level_1;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.PrimitiveIterator;

public class Start extends Application {

    @Override
    public void start(Stage stage)  {

        Level_1 level1 = new Level_1();
        level1.displayLevel(stage);

    }

    public static void main(String[] arg) {
        launch();
    }
}
