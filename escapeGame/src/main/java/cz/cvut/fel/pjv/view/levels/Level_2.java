package cz.cvut.fel.pjv.view.levels;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.save.GameData;
import cz.cvut.fel.pjv.view.GhostMovement;
import cz.cvut.fel.pjv.view.ObjectPlacer_Level2;
import cz.cvut.fel.pjv.view.renders.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.*;

public class Level_2 {
    private static final String SAVE_FILE_NAME = "saveGame.json";
    private final GameBoard gameBoard;
    private final ObjectPlacer_Level2 objectPlacerLevel2;
    private final PlayerController playerController;
    private final RenderObject renderObject;
    private final RenderBackground renderBackground;
    private final RenderHealthForPlayer renderHealthForPlayer;
    private final RenderInventory renderInventory;
    private final RenderCratingItems renderCratingItems;
    private GhostMovement ghostMovement3, ghostMovement4;

    public Level_2() {
        gameBoard = new GameBoard();
        objectPlacerLevel2 = new ObjectPlacer_Level2(gameBoard);
        playerController = new PlayerController(objectPlacerLevel2.getPlayer(), gameBoard);
        renderObject = new RenderObject(gameBoard);
        renderBackground = new RenderBackground(gameBoard);
        renderHealthForPlayer = new RenderHealthForPlayer(objectPlacerLevel2.getPlayer());
        renderInventory = new RenderInventory();
        renderCratingItems = new RenderCratingItems();
    }

    public void displayLevel2(Stage stage) {

        Canvas gameCanvas = new Canvas(gameBoard.getSize(), gameBoard.getSize());
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();

        // SRDIČKY
        Canvas heartCanvas = new Canvas(gameBoard.getSize(), 50);
        GraphicsContext heartGraphicsContext = heartCanvas.getGraphicsContext2D();

        Pane pane = new StackPane();
        pane.getChildren().addAll(gameCanvas, heartCanvas);

        Scene scene = new Scene(pane, gameBoard.getSize() + 100, gameBoard.getSize() + 100);
        setupKeyboardEvents(scene, graphicsContext, heartGraphicsContext);

        stage.setTitle("GAME! LEVEL 2!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        renderBackground.render(graphicsContext);
        objectPlacerLevel2.startGame();

        loadGameState(SAVE_FILE_NAME);
        renderHealthForPlayer.render(heartGraphicsContext);
        renderObject.renderObject(graphicsContext, gameBoard.getTileDim());

        ghostMovement3 = new GhostMovement(gameBoard, GHOST3.getPositionX(), GHOST3.getPositionY(), GHOST3.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement3.startMovement(GHOST3);

        ghostMovement4 = new GhostMovement(gameBoard, GHOST4.getPositionX(), GHOST4.getPositionY(), GHOST4.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement4.startMovement(GHOST4);

        if (scene.getWindow() != null) {
            scene.getWindow().setOnCloseRequest(event -> {
                saveGameState(SAVE_FILE_NAME);
                ghostMovement3.stopMovement();
                ghostMovement4.stopMovement();
            });
        }

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
//                    renderInventory.displayInventory(objectPlacerLevel2);
                    break;
                case PLUS:
                case ADD:
//                    renderCratingItems.displayCraftingItems(objectPlacerLevel2, renderInventory);
                    break;
            }

            if (playerController.isSaved()) {
                RenderMessage.usingSwordToSaveYourself();
                playerController.setSaved(false);

            } else if (playerController.isTransition()) {
                RenderMessage.transitionToTheNextLevel();
                playerController.setTransition(false);
            }

            renderBackground.render(graphicsContext);
            renderObject.renderObject(graphicsContext, gameBoard.getTileDim());
            heartGraphicsContext.clearRect(0, 0, gameBoard.getSize(), 50);
            renderHealthForPlayer.render(heartGraphicsContext);

            if (objectPlacerLevel2.getPlayer().getHealth() == 0) {
                RenderMessage.displayGameOver();
            }

        });

    }

    public void saveGameState(String fileName) {

        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = new GameData();
        gameData.gameBoard = gameBoard.getBoard();
        gameData.player = objectPlacerLevel2.getPlayer();

        try {
            objectMapper.writeValue( new File(fileName), gameData);
            System.out.println("Status: Save game successful.");
        } catch (IOException e) {
            System.err.println("Cannot save game: " + e.getMessage());
        }

    }

    public void loadGameState(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GameData gameData = objectMapper.readValue(new File(fileName), GameData.class);
            gameBoard.setBoard(gameData.gameBoard);
            System.out.println("Status: Load game successful.");
        } catch (IOException e) {
            System.err.println("Cannot load game: " + e.getMessage());
        }
    }

}


