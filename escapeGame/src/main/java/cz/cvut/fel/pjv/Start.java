package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Player;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
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
    private final PlayerController playerController = new PlayerController(objectPlacer.getPlayer(), gameBoard);
    private final ObjectRender objectRender = new ObjectRender(gameBoard);
    private final RenderBackground renderBackground = new RenderBackground(gameBoard);
    private final RenderHealthForPlayer renderHealthForPlayer = new RenderHealthForPlayer(objectPlacer.getPlayer());
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
        setupKeyboardEvents(scene, graphicsContext, heartGraphicsContext);

        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        renderBackground.render(graphicsContext);
        objectPlacer.startGame();
        renderHealthForPlayer.render(heartGraphicsContext);
        objectRender.renderObject(graphicsContext, gameBoard.getTileDim());
    }

    private void setupKeyboardEvents(Scene scene, GraphicsContext graphicsContext, GraphicsContext heartGraphicsContext) {

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    playerController.move(Direction.UP);
                    break;
                case A:
                    playerController.move(Direction.LEFT);
                    break;
                case S:
                    playerController.move(Direction.DOWN);
                    break;
                case D:
                    playerController.move(Direction.RIGHT);
                    break;
                case I:
                    renderInventory.displayInventory(objectPlacer.getPlayer().getInventory());
                    break;
            }
            renderBackground.render(graphicsContext);
            objectRender.renderObject(graphicsContext, gameBoard.getTileDim());
            renderHealthForPlayer.render(heartGraphicsContext);
        });

    }

    public static void main(String[] arg) {
        launch();
    }
}
