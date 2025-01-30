package cz.cvut.fel.pjv;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.view.*;

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

public class Start extends Application {

    private final static   String SAVE_FILE_NAME = "saveGame.json";
    private final GameBoard gameBoard = new GameBoard();
    private final ObjectPlacer objectPlacer = new ObjectPlacer(gameBoard);
    private final PlayerController playerController = new PlayerController(objectPlacer.getPlayer(), gameBoard);
    private final RenderObject renderObject = new RenderObject(gameBoard);
    private final RenderBackground renderBackground = new RenderBackground(gameBoard);
    private final RenderHealthForPlayer renderHealthForPlayer = new RenderHealthForPlayer(objectPlacer.getPlayer());
    private final RenderInventory renderInventory = new RenderInventory(gameBoard);
    private GhostMovement ghostMovement, ghostMovement2;

    @Override
    public void start(Stage stage)  {

        Canvas gameCanvas = new Canvas(gameBoard.getSize(), gameBoard.getSize());
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();

        // SRDIČKY
        Canvas heartCanvas = new Canvas(gameBoard.getSize(), 50);
        GraphicsContext heartGraphicsContext = heartCanvas.getGraphicsContext2D();

        Pane pane = new StackPane();
        pane.getChildren().addAll(gameCanvas, heartCanvas);

        Scene scene = new Scene(pane, gameBoard.getSize() + 100, gameBoard.getSize() + 100);
        setupKeyboardEvents(scene, graphicsContext, heartGraphicsContext);

        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        renderBackground.render(graphicsContext);
        objectPlacer.startGame();
        renderHealthForPlayer.render(heartGraphicsContext);
        renderObject.renderObject(graphicsContext, gameBoard.getTileDim());

        ghostMovement = new GhostMovement(gameBoard, 4, 2, 7, graphicsContext, renderBackground, renderObject);
        ghostMovement.startMovement();

        ghostMovement2 = new GhostMovement(gameBoard, 8, 5, 9, graphicsContext, renderBackground, renderObject);
        ghostMovement2.startMovement();

        if (scene.getWindow() != null) {
            scene.getWindow().setOnCloseRequest(event -> {
                saveGameState(SAVE_FILE_NAME);
                ghostMovement.stopMovement();
                ghostMovement2.stopMovement();
            });
        }

//        loadGameState(SAVE_FILE_NAME);

    }

    private void setupKeyboardEvents(Scene scene, GraphicsContext graphicsContext, GraphicsContext heartGraphicsContext) {

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                case UP:
                    playerController.move(Direction.UP);
                    break;
                case A:
                case LEFT:
                    playerController.move(Direction.LEFT);
                    break;
                case S:
                case DOWN:
                    playerController.move(Direction.DOWN);
                    break;
                case D:
                case RIGHT:
                    playerController.move(Direction.RIGHT);
                    break;
                case I:
                    renderInventory.displayInventory(objectPlacer.getPlayer().getInventory());
                    break;
            }
            renderBackground.render(graphicsContext);
            renderObject.renderObject(graphicsContext, gameBoard.getTileDim());
            renderHealthForPlayer.render(heartGraphicsContext);
        });

    }

    public void saveGameState(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = new GameData();
        gameData.gameBoard = gameBoard.getBoard();
        gameData.player = objectPlacer.getPlayer();
        try {
            objectMapper.writeValue(new File(fileName), gameData);
        } catch (IOException e) {
            System.err.println("Cannot save game: " + e.getMessage());
        }
    }

    // TODO LOAD GAME STATE
//    public boolean loadGameState(String fileName) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            GameData gameData = objectMapper.readValue(new File(fileName), GameData.class);
//            gameBoard.setBoard(gameData.gameBoard);
//            objectPlacer.setPlayer(gameData.player);
//            return true;
//        } catch (IOException e) {
//            System.err.println("Cannot load game: " + e.getMessage());
//            return false;
//        }
//    }

    public static void main(String[] arg) {
        launch();
    }
}
