/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.save.GameData;
import cz.cvut.fel.pjv.view.*;

import cz.cvut.fel.pjv.view.levels.Level_1;
import cz.cvut.fel.pjv.view.renders.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.GHOST1;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.GHOST2;

public class Start extends Application {



    @Override
    public void start(Stage stage)  {

        Level_1 level1 = new Level_1();
        level1.displayLevel1(stage);

    }

    public static void main(String[] arg) {
        launch();
    }
}
