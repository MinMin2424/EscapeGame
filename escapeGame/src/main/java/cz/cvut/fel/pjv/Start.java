package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.view.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start extends Application {

    private final GameBoard gameBoard = new GameBoard();
    private final ObjectPlacer objectPlacer = new ObjectPlacer(gameBoard);
    private final ObjectRender objectRender = new ObjectRender(gameBoard);
    private final RenderBackground renderBackground = new RenderBackground(gameBoard);
    private final RenderHealthForPlayer renderHealthForPlayer = new RenderHealthForPlayer(objectPlacer);
    private final RenderInventory renderInventory = new RenderInventory(gameBoard);

    @Override
    public void start(Stage stage)  {

        Canvas gameCanvas = new Canvas(gameBoard.getSize(), gameBoard.getSize());
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();

        // SRDIČKY
        Canvas heartCanvas = new Canvas(gameBoard.getSize(), 50);
        GraphicsContext heartGraphicsContext = heartCanvas.getGraphicsContext2D();

        Pane pane = new StackPane();
        pane.getChildren().addAll(gameCanvas, heartCanvas);

        Scene scene = new Scene(pane, gameBoard.getSize() + 100 , gameBoard.getSize() + 100);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.I) {
                // Zobrazení inventář
                renderInventory.displayInventory(objectPlacer.getPlayer().getInventory());
            }
        });

        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        renderBackground.render(graphicsContext);
        objectPlacer.startGame();
        renderHealthForPlayer.render(heartGraphicsContext);
        objectRender.renderObject(graphicsContext, gameBoard.getTileDim());
    }

    public static void main(String[] arg) {
        launch();
    }
}
