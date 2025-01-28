package cz.cvut.fel.pjv;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Start extends Application {

    private final GameBoard gameBoard = new GameBoard();
    private final GamePosition gamePosition = new GamePosition();

    @Override
    public void start(Stage stage)  {
        Canvas gameCanvas = new Canvas(gameBoard.getSize(), gameBoard.getSize());
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();

        render(graphicsContext);

        Canvas heartCanvas = new Canvas(gameBoard.getSize(), 50);
        GraphicsContext heartGraphicsContext = heartCanvas.getGraphicsContext2D();

        renderHealthForPlayer(heartGraphicsContext);

        Pane pane = new StackPane();
        pane.getChildren().addAll(gameCanvas, heartCanvas);

        Scene scene = new Scene(pane, gameBoard.getSize() + 100 , gameBoard.getSize() + 100);
        stage.setTitle("GAME!");
        stage.setScene(scene);
        stage.show();

        StackPane.setAlignment(heartCanvas, Pos.TOP_CENTER);

        gamePosition.startGame();
        gamePosition.renderObjects(graphicsContext, gameBoard.getTileDim());
    }

    private void render(GraphicsContext graphicsContext) {
        renderBackground(graphicsContext);
    }

    private void renderBackground(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GAINSBORO);
        graphicsContext.fillRect(0, 0, gameBoard.getSize(), gameBoard.getSize());
        graphicsContext.setStroke(Color.DARKGRAY);

        for (int i = 0; i <= gameBoard.getSize(); i += gameBoard.getTileDim()) {
            graphicsContext.strokeLine(i, 0, i, gameBoard.getSize());
            graphicsContext.strokeLine(0, i, gameBoard.getSize(), i);
        }
    }

    private void renderHealthForPlayer(GraphicsContext graphicsContext) {
        Image image = new Image("heart.png");
        int playerHealth = gamePosition.player.getHealth();
        int imageSize = 32;
        int padding = 10;

        for (int i = 0; i < playerHealth; i++) {
            int x = i * (imageSize + padding);
            graphicsContext.drawImage(image, x, 15, imageSize, imageSize);
        }
    }

    public static void main(String[] arg) {
        launch();
    }
}

