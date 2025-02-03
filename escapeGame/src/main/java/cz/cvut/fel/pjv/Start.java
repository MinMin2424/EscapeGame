/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.gameData.GameStateManager;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.placers.ObjectPlacer_Level1;
import cz.cvut.fel.pjv.view.levels.LevelBase;
import cz.cvut.fel.pjv.view.levels.Level_1;
import cz.cvut.fel.pjv.view.levels.Level_2;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.PrimitiveIterator;

public class Start extends Application {

    private static final String SAVE_FILE_NAME = "saveGame.json";

    @Override
    public void start(Stage stage)  {


        File saveFile = new File(SAVE_FILE_NAME);

        if (!saveFile.exists() || saveFile.length() == 0) {
            Level_1 level1 = new Level_1();
            level1.displayLevel(stage);

        } else {
            int savedLevel = GameStateManager.getSavedLevelFromFile(SAVE_FILE_NAME);

            if (savedLevel == 1) {
                Level_1 level1 = new Level_1();
                level1.displayLevel(stage);

            } else if (savedLevel == 2) {
                Level_2 level2 = new Level_2();
                level2.displayLevel(stage);

            }
        }

    }

    private boolean isLevel(int level) {
        return GameStateManager.getSavedLevelFromFile(SAVE_FILE_NAME) == level;
    }

    public static void main(String[] arg) {
        launch();
    }
}
