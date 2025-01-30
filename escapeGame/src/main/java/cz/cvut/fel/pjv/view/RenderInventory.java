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

    private final int ROWS = 4;
    private final int COLUMNS = 5;
    private final int SQUARE_WIDTH = 100;
    private final int SQUARE_HEIGHT = 120;
    private final int PADDING = 10;

    public RenderInventory(GameBoard gameBoard) {
    }

    public void render(GraphicsContext graphicsContext, Inventory inventory) {

        Image plusImage = new Image("plus.png");
        double plusX = (graphicsContext.getCanvas().getWidth() - plusImage.getWidth()) / 2;
        double plusY =  PADDING + 5;
        graphicsContext.drawImage(plusImage, plusX, plusY);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {

                int x = col * (SQUARE_WIDTH + PADDING) + PADDING;
                int y = row * (SQUARE_HEIGHT + PADDING) + PADDING + 50;
                graphicsContext.strokeRect(x, y, SQUARE_WIDTH, SQUARE_HEIGHT);


                int index = row * COLUMNS + col;

                if (index < inventory.getItems().size()) {
                    Item item = inventory.getItems().get(index);
                    Image image = new Image(inventory.getImageName(item));

                    // Název předmětu
                    double textX = x + 10;
                    double textY = y + PADDING + 10;
                    graphicsContext.fillText(item.getName(), textX, textY);

                    // Obrázek předmětu
                    double imageX = x + (SQUARE_WIDTH - image.getWidth()) / 2;
                    double imageY = textY + graphicsContext.getFont().getSize();
                    graphicsContext.drawImage(image, imageX, imageY);

                    // Množství předmětu
                    double quantityWidth = graphicsContext.getFont().getSize() * ("Quantity: " + item.getQuantity()).length();
                    double quantityX = x + (SQUARE_WIDTH - quantityWidth) / 2 + 25;
                    double quantityY = imageY + image.getHeight() + 20;
                    graphicsContext.fillText("Quantity: " + item.getQuantity(), quantityX, quantityY);
                }
            }
        }

    }

    public void displayInventory(Inventory inventory) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Inventory");

        int widthInventory = COLUMNS * (SQUARE_WIDTH + PADDING) + PADDING;
        int heightInventory = ROWS * (SQUARE_HEIGHT + PADDING) + PADDING + 50;

        Canvas canvas = new Canvas(widthInventory, heightInventory);
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
