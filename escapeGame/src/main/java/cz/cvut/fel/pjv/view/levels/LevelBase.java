/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.levels;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.PlayerController;
import cz.cvut.fel.pjv.model.direction.Direction;
import cz.cvut.fel.pjv.gameData.GameStateManager;
import cz.cvut.fel.pjv.view.GhostMovement;
import cz.cvut.fel.pjv.model.placers.ObjectPlacerBase;
import cz.cvut.fel.pjv.model.placers.ObjectPlacer_Level1;
import cz.cvut.fel.pjv.model.placers.ObjectPlacer_Level2;
import cz.cvut.fel.pjv.view.renders.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.GHOST1;
import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.GHOST2;

/**
 * Represents the base class for all levels in the game.
 */
public abstract class LevelBase {
    protected static final String SAVE_FILE_NAME = "saveGame.json";
    protected final GameBoard gameBoard;
    protected final ObjectPlacerBase objectPlacer;
    protected final PlayerController playerController;
    protected final RenderObject renderObject;
    protected final RenderBackground renderBackground;
    protected final RenderHealthForPlayer renderHealthForPlayer;
    protected final RenderInventory renderInventory;
    protected final RenderCratingItems renderCratingItems;
    protected GhostMovement ghostMovement1, ghostMovement2;

    /**
     * Constructs a LevelBase object with the specified object placer.
     * Initializes various components needed for rendering and player interaction.
     * @param objectPlacer The object placer for placing objects on the game board.
     */
    public LevelBase(ObjectPlacerBase objectPlacer) {
        this.gameBoard = objectPlacer.getGameBoard();
        this.objectPlacer = objectPlacer;
        this.playerController = objectPlacer.getPlayerController();
        this.renderObject = new RenderObject(objectPlacer.getGameBoard());
        this.renderBackground = new RenderBackground(objectPlacer.getGameBoard());
        this.renderHealthForPlayer = new RenderHealthForPlayer(objectPlacer.getPlayer());
        this.renderInventory = new RenderInventory();
        this.renderCratingItems = new RenderCratingItems();
    }

    /**
     * Displays the level on the stage.
     * @param stage The JavaFX stage where the level will be displayed.
     */
    public void displayLevel(Stage stage) {
        Canvas gameCanvas = new Canvas(gameBoard.getSize(), gameBoard.getSize());
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();

        Canvas heartCanvas = new Canvas(gameBoard.getSize(), 50);
        GraphicsContext heartGraphicsContext = heartCanvas.getGraphicsContext2D();

        Pane pane = new StackPane();
        pane.getChildren().addAll(gameCanvas, heartCanvas);

        Scene scene = new Scene(pane, gameBoard.getSize() + 100, gameBoard.getSize() + 100);
        setupKeyboardEvents(stage, scene, graphicsContext, heartGraphicsContext);

        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        renderBackground.render(graphicsContext);
        objectPlacer.startGame();

        int savedLevel = GameStateManager.getSavedLevelFromFile(SAVE_FILE_NAME);
        int currentLevel = getLevel(objectPlacer);
        if (savedLevel == currentLevel || savedLevel == 2) {
            GameStateManager.loadGameState(SAVE_FILE_NAME, gameBoard);
        }

        renderHealthForPlayer.render(heartGraphicsContext);
        renderObject.renderObject(graphicsContext, gameBoard.getTileDim());

        setupGhostMovements(graphicsContext);

        if (scene.getWindow() != null) {
            scene.getWindow().setOnCloseRequest(event -> {
                int level = getLevel(objectPlacer);
                GameStateManager.saveGameState(SAVE_FILE_NAME, gameBoard, objectPlacer, level);
                ghostMovement1.stopMovement();
                ghostMovement2.stopMovement();
            });
        }
    }

    /**
     * Sets up the keyboard events for player interaction.
     * @param stage The JavaFX stage where the level is displayed.
     * @param scene The JavaFX scene associated with the stage.
     * @param graphicsContext The graphics context for rendering.
     * @param heartGraphicsContext The graphics context for rendering health.
     */
    private void setupKeyboardEvents(Stage stage, Scene scene, GraphicsContext graphicsContext, GraphicsContext heartGraphicsContext) {

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W, UP -> playerController.move(Direction.UP);
                case A, LEFT -> playerController.move(Direction.LEFT);
                case S, DOWN -> playerController.move(Direction.DOWN);
                case D, RIGHT -> playerController.move(Direction.RIGHT);
                case I -> renderInventory.displayInventory(objectPlacer);
                case PLUS, ADD -> renderCratingItems.displayCraftingItems(objectPlacer, renderInventory);
            }

            displayMessage(playerController, stage);

            renderAgain(graphicsContext, heartGraphicsContext);

            if (objectPlacer.getPlayer().getHealth() == 0) RenderMessage.displayGameOver();

        });

    }

    /**
     * Renders the game elements again on the screen, including background, objects, and player's health.
     * @param graphicsContext The graphics context for rendering game elements.
     * @param heartGraphicsContext The graphics context for rendering player's health.
     */
    private void renderAgain(GraphicsContext graphicsContext, GraphicsContext heartGraphicsContext) {
        renderBackground.render(graphicsContext);
        renderObject.renderObject(graphicsContext, gameBoard.getTileDim());
        heartGraphicsContext.clearRect(0, 0, gameBoard.getSize(), 50);
        renderHealthForPlayer.render(heartGraphicsContext);
    }

    /**
     * Displays the appropriate message based on player actions.
     * @param playerController The player controller managing player actions.
     * @param stage The JavaFX stage where the level is displayed.
     */
    private void displayMessage(PlayerController playerController, Stage stage) {
        if (playerController.isSaved()) {
            RenderMessage.usingSwordToSaveYourself();
            playerController.setSaved(false);

        } else if (playerController.isTransition() && getLevel(objectPlacer) == 1) {
            RenderMessage.transitionToTheNextLevel();
            ghostMovement1.stopMovement();
            ghostMovement2.stopMovement();
            Level_2 level2 = new Level_2();
            level2.displayLevel(stage);
            playerController.setTransition(false);

        } else if (playerController.transition && getLevel(objectPlacer) == 2) {
            RenderMessage.displayVictory();
            playerController.setVictory(true);

        } else if (playerController.isRemoveFire()) {
            RenderMessage.usingWaterToSaveYourself();
            playerController.setRemoveFire(false);
        }
    }

    /**
     * Sets up the ghost movements for the level.
     * @param graphicsContext The graphics context for rendering.
     */
    private void setupGhostMovements(GraphicsContext graphicsContext) {
        ghostMovement1 = new GhostMovement(gameBoard, GHOST1.getPositionX(), GHOST1.getPositionY(), GHOST1.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement1.startMovement(GHOST1);

        ghostMovement2 = new GhostMovement(gameBoard, GHOST2.getPositionX(), GHOST2.getPositionY(), GHOST2.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement2.startMovement(GHOST2);
    }

    /**
     * Gets the level based on the provided ObjectPlacerBase instance.
     * If the ObjectPlacerBase instance is an instance of ObjectPlacer_Level2.
     * the level is set to 2, otherwise it defaults to 1.
     * @param objectPlacer The ObjectPlacerBase instance to determine the level from.
     * @return The level of the ObjectPlacerBase instance (1 if ObjectPlacer_Level1, 2 if ObjectPlacer_Level2).
     */
    private static int getLevel(ObjectPlacerBase objectPlacer) {
        int level = 1;

        if (objectPlacer instanceof ObjectPlacer_Level2) level = 2;
        return level;
    }
}
