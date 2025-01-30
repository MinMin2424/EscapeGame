package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RenderCratingItems {

    public void displayCraftingItems() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("CRAFTING ITEMS");

        int WIDTH = 400;
        int HEIGHT = 200;

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        render(graphicsContext, WIDTH, HEIGHT);

        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        VBox container = new VBox(layout);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

    }

    private void render(GraphicsContext graphicsContext, int width, int height) {
        renderBackground(graphicsContext, width, height);
        renderTitle(graphicsContext, width);
        renderItems(graphicsContext);
        renderText(graphicsContext);
    }

    private void renderItems(GraphicsContext graphicsContext) {

        String swordImageName = CraftingItems.SWORD.getImageName();
        String potionImageName = CraftingItems.POTION.getImageName();

        Image swordImage = new Image(swordImageName);
        Image potionImage = new Image(potionImageName);

        graphicsContext.drawImage(swordImage, 80, 50);
        graphicsContext.drawImage(potionImage, 250, 50);
    }

    private void renderBackground(GraphicsContext graphicsContext, int width, int height) {
        graphicsContext.setFill(Color.GHOSTWHITE);
        graphicsContext.fillRect(0, 0, width, height);
    }

    private void renderTitle(GraphicsContext graphicsContext, int width) {
        String text = "CHOOSE CRAFTING ITEM:";
        graphicsContext.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText(text, (double) width / 2, 30);
    }

    private void renderText(GraphicsContext graphicsContext) {
        String swordText = "Press S for SWORD";
        String potionText = "Press P for POTION";

        graphicsContext.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.BLACK);

        graphicsContext.fillText(swordText, 100, 150);
        graphicsContext.fillText(potionText, 280, 150);
    }

}
