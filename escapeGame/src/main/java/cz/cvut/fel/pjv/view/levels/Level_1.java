/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.levels;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.save.GameData;
import cz.cvut.fel.pjv.view.GhostMovement;
import cz.cvut.fel.pjv.view.ObjectPlacer;
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

import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.GHOST1;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.GHOST2;

public class Level_1 {
    private static final String SAVE_FILE_NAME = "saveGame.json";
    private final GameBoard gameBoard;
    private final ObjectPlacer objectPlacer;
    private final PlayerController playerController;
    private final RenderObject renderObject;
    private final RenderBackground renderBackground;
    private final RenderHealthForPlayer renderHealthForPlayer;
    private final RenderInventory renderInventory;
    private final RenderCratingItems renderCratingItems;
    private GhostMovement ghostMovement1, ghostMovement2;

    public Level_1() {
        gameBoard = new GameBoard();
        objectPlacer = new ObjectPlacer(gameBoard);
        playerController = new PlayerController(objectPlacer.getPlayer(), gameBoard);
        renderObject = new RenderObject(gameBoard);
        renderBackground = new RenderBackground(gameBoard);
        renderHealthForPlayer = new RenderHealthForPlayer(objectPlacer.getPlayer());
        renderInventory = new RenderInventory();
        renderCratingItems = new RenderCratingItems();
    }



    public void displayLevel1(Stage stage) {

        Canvas gameCanvas = new Canvas(gameBoard.getSize(), gameBoard.getSize());
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();

        // SRDIČKY
        Canvas heartCanvas = new Canvas(gameBoard.getSize(), 50);
        GraphicsContext heartGraphicsContext = heartCanvas.getGraphicsContext2D();

        Pane pane = new StackPane();
        pane.getChildren().addAll(gameCanvas, heartCanvas);

        Scene scene = new Scene(pane, gameBoard.getSize() + 100, gameBoard.getSize() + 100);
        setupKeyboardEvents(stage, scene, graphicsContext, heartGraphicsContext);

        stage.setTitle("GAME! LEVEL 1!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        renderBackground.render(graphicsContext);
        objectPlacer.startGame();

        loadGameState(SAVE_FILE_NAME);
        renderHealthForPlayer.render(heartGraphicsContext);
        renderObject.renderObject(graphicsContext, gameBoard.getTileDim());

        ghostMovement1 = new GhostMovement(gameBoard, GHOST1.getPositionX(), GHOST1.getPositionY(), GHOST1.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement1.startMovement(GHOST1);

        ghostMovement2 = new GhostMovement(gameBoard, GHOST2.getPositionX(), GHOST2.getPositionY(), GHOST2.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement2.startMovement(GHOST2);

        if (scene.getWindow() != null) {
            scene.getWindow().setOnCloseRequest(event -> {
                saveGameState(SAVE_FILE_NAME);
                ghostMovement1.stopMovement();
                ghostMovement2.stopMovement();
            });
        }

    }

    private void setupKeyboardEvents(Stage stage, Scene scene, GraphicsContext graphicsContext, GraphicsContext heartGraphicsContext) {

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
                    renderInventory.displayInventory(objectPlacer);
                    break;
                case PLUS:
                case ADD:
                    renderCratingItems.displayCraftingItems(objectPlacer, renderInventory);
                    break;
            }

            if (playerController.isSaved()) {
                RenderMessage.usingSwordToSaveYourself();
                playerController.setSaved(false);

            } else if (playerController.isTransition()) {
                RenderMessage.transitionToTheNextLevel();
                ghostMovement1.stopMovement();
                ghostMovement2.stopMovement();
                Level_2 level2 = new Level_2();
                level2.displayLevel2(stage);
                playerController.setTransition(false);
            }

            renderBackground.render(graphicsContext);
            renderObject.renderObject(graphicsContext, gameBoard.getTileDim());
            heartGraphicsContext.clearRect(0, 0, gameBoard.getSize(), 50);
            renderHealthForPlayer.render(heartGraphicsContext);

            if (objectPlacer.getPlayer().getHealth() == 0) {
                RenderMessage.displayGameOver();
            }

        });

    }

    public void saveGameState(String fileName) {

        ObjectMapper objectMapper = new ObjectMapper();
        GameData gameData = new GameData();
        gameData.gameBoard = gameBoard.getBoard();
        gameData.player = objectPlacer.getPlayer();

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
