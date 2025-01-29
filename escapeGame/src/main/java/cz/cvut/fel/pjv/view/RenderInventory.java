package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Inventory;
import cz.cvut.fel.pjv.model.Item;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RenderInventory {

    private final GameBoard gameBoard;

    public RenderInventory(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void render(GraphicsContext graphicsContext, Inventory inventory) {

        int rows = 5;
        int columns = 5;
        int squareSize = 80;
        int padding = 20;

        for (int row = 0; row <= rows; row++) {
            int y = row * (squareSize + padding) + padding;
            graphicsContext.strokeLine(padding, y, padding + columns * (squareSize + padding), y);
        }

        for (int col = 0; col <= columns; col++) {
            int x = col * (squareSize + padding) + padding;
            graphicsContext.strokeLine(x, padding, x, padding + rows * (squareSize + padding));
        }

    }

    public void displayInventory(Inventory inventory) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Inventory");

        Canvas canvas = new Canvas(gameBoard.getSize() - 100, gameBoard.getSize() - 100);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        render(graphicsContext, inventory);

        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        VBox container = new VBox(layout);
        container.setStyle("-fx-background-color: lightgray;");
        container.setAlignment(Pos.CENTER);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
