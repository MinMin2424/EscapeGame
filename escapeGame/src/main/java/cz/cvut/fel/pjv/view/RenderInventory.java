/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.Inventory;
import cz.cvut.fel.pjv.model.Item;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Handles the rendering and display of the player's inventory.
 */
public class RenderInventory {

    private Stage stage;
    private final int ROWS = 3; // Number of rows in the inventory grid.
    private final int COLUMNS = 5; // Number of columns in the inventory grid.
    private final int SQUARE_WIDTH = 100; // Width of each inventory item square.
    private final int SQUARE_HEIGHT = 120; // Height of each inventory item square.
    private final int PADDING = 10; // Padding between inventory items.
    private final RenderCratingItems renderCratingItems = new RenderCratingItems();

    /**
     * Constructs a RenderInventory object.
     * @param gameBoard The game board associated with the inventory.
     */
    public RenderInventory(GameBoard gameBoard) {
    }

    /**
     * Renders the player's inventory on the canvas using the provided GraphicsContext.
     * @param graphicsContext The graphics context to render on.
     * @param inventory The player's inventory to be rendered.
     */
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

                    // Object's name
                    double textX = x + 10;
                    double textY = y + PADDING + 10;
                    graphicsContext.fillText(item.getName(), textX, textY);

                    // Object's image
                    double imageX = x + (SQUARE_WIDTH - image.getWidth()) / 2;
                    double imageY = textY + graphicsContext.getFont().getSize();
                    graphicsContext.drawImage(image, imageX, imageY);

                    // Object's quantity
                    double quantityWidth = graphicsContext.getFont().getSize() * ("Quantity: " + item.getQuantity()).length();
                    double quantityX = x + (SQUARE_WIDTH - quantityWidth) / 2 + 25;
                    double quantityY = imageY + image.getHeight() + 20;
                    graphicsContext.fillText("Quantity: " + item.getQuantity(), quantityX, quantityY);
                }
            }
        }
        renderText(graphicsContext);
    }

    /**
     * Renders text below the inventory items.
     * @param graphicsContext The graphics context to render on.
     */
    private void renderText(GraphicsContext graphicsContext) {
        String plusText = "Press + to create a new item";
        int textX = 150;
        int textY = 3 * (120 + 10) + 85;
        graphicsContext.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText(plusText, textX, textY);
    }

    /**
     * Handles the event of pressing a key for crafting.
     * If the key pressed is the plus key, displays the crafting items window.
     * @param event The KeyEvent representing the key press event.
     */
    public void handleCraftingEvent(KeyEvent event, ObjectPlacer objectPlacer) {
        if (event.getCode() == KeyCode.ADD || event.getCode() == KeyCode.PLUS) {
            renderCratingItems.displayCraftingItems(objectPlacer, this);
        }
    }

    /**
     *  Displays the player's inventory in a separate window.
     * @param objectPlacer Provide a brief description of the role of the ObjectPlacer parameter.
     */
    public void displayInventory(ObjectPlacer objectPlacer) {

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("INVENTORY");

        int widthInventory = COLUMNS * (SQUARE_WIDTH + PADDING) + PADDING;
        int heightInventory = ROWS * (SQUARE_HEIGHT + PADDING) + PADDING + 100;

        Canvas canvas = new Canvas(widthInventory, heightInventory);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        render(graphicsContext, objectPlacer.getPlayer().getInventory());

        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        VBox container = new VBox(layout);
        container.setStyle("-fx-background-color: ghostwhite;");
        container.setAlignment(Pos.CENTER);

        Scene scene = new Scene(container);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->  handleCraftingEvent(event, objectPlacer));
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Closes the stage displaying the inventory window
     * Checks if the stage is not null, then closes it.
     */
    public void close() {
        if (stage != null) {
            stage.close();
        }
    }
}
