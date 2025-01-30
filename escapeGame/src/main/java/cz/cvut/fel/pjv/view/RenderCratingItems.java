package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.Start;
import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

/**
 * Handles the rendering and display of crafting items window.
 */
public class RenderCratingItems {

    private Stage stage;

    /**
     * Displays the crafting items window.
     * @param objectPlacer The ObjectPlacer object used to access player's inventory a craft items.
     * @param renderInventory The RenderInventory object used to update and display player's inventory.
     */
    public void displayCraftingItems(ObjectPlacer objectPlacer, RenderInventory renderInventory) {

        if (objectPlacer.getPlayer().getHealth() == 0) {
            RenderGameOver.displayGameOver();
            return;
        }

        renderInventory.close();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("CRAFTING ITEMS");

        int WIDTH = 600;
        int HEIGHT = 400;

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        render(graphicsContext, WIDTH, HEIGHT);

        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        VBox container = new VBox(layout);

        Scene scene = new Scene(container);
        craftingItems(scene, objectPlacer, renderInventory);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Closes the crafting items window.
     */
    private void close() {
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Render the crafting items window.
     * @param graphicsContext The GraphicsContext used to render items on the canvas.
     * @param width The width of the canvas.
     * @param height The height of the canvas.
     */
    private void render(GraphicsContext graphicsContext, int width, int height) {
        renderBackground(graphicsContext, width, height);
        renderTitle(graphicsContext, width);
        renderItems(graphicsContext);
        renderText(graphicsContext);
        renderDetailTitle(graphicsContext);
        renderDetail(graphicsContext);
    }

    /**
     * Handles the crafting of items when keys are pressed.
     * @param scene The Scene object to handle key events.
     * @param objectPlacer The ObjectPlacer object used to access player's inventory and craft items.
     * @param renderInventory The RenderInventory object used to update and display player's inventory.
     */
    private void craftingItems(Scene scene, ObjectPlacer objectPlacer, RenderInventory renderInventory) {
        scene.setOnKeyPressed(event -> {
            switch ((event.getCode())) {
                case S:
                    objectPlacer.getPlayer().craftItem(CraftingItems.SWORD);
                    break;
                case P:
                    objectPlacer.getPlayer().craftItem(CraftingItems.POTION);
                    break;
            }
            renderInventory.displayInventory(objectPlacer);
            this.close();
        });
    }

    /**
     * Renders the images of crafting items on the canvas.
     * @param graphicsContext The GraphicsContext used to render items.
     */
    private void renderItems(GraphicsContext graphicsContext) {

        String swordImageName = CraftingItems.SWORD.getImageName();
        String potionImageName = CraftingItems.POTION.getImageName();

        Image swordImage = new Image(swordImageName);
        Image potionImage = new Image(potionImageName);

        graphicsContext.drawImage(swordImage, 180, 50);
        graphicsContext.drawImage(potionImage, 360, 50);
    }

    /**
     * Renders the background of the crafting items window.
     * @param graphicsContext The GraphicsContext used to render the background.
     * @param width The width of the canvas.
     * @param height The height of the canvas.
     */
    private void renderBackground(GraphicsContext graphicsContext, int width, int height) {
        graphicsContext.setFill(Color.GHOSTWHITE);
        graphicsContext.fillRect(0, 0, width, height);
    }

    /**
     * Renders the title of the crafting items window.
     * @param graphicsContext The GraphicsContext used to render the title.
     * @param width The width of the canvas.
     */
    private void renderTitle(GraphicsContext graphicsContext, int width) {
        String text = "CHOOSE CRAFTING ITEM:";
        graphicsContext.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText(text, (double) width / 2, 30);
    }

    /**
     * Renders the text for selecting crafting items.
     * @param graphicsContext The GraphicsContext used to render the text.
     */
    private void renderText(GraphicsContext graphicsContext) {
        String swordText = "Press S for SWORD";
        String potionText = "Press P for POTION";

        graphicsContext.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.BLACK);

        graphicsContext.fillText(swordText, 200, 150);
        graphicsContext.fillText(potionText, 390, 150);
    }

    /**
     * Renders the title of the crafting items details.
     * @param graphicsContext The GraphicsContext used to render the title.
     */
    private void renderDetailTitle(GraphicsContext graphicsContext) {
        String title = "Description:";
        graphicsContext.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.fillText(title, 100, 200);
    }

    /**
     * Renders the details of crafting items, including their images and descriptions.
     * @param graphicsContext The GraphicsContext used to render the details.
     */
    private void renderDetail(GraphicsContext graphicsContext) {

        //SWORD
        String swordDescription = "SWORD - crafted using 3 ORES";
        Image swordImage = new Image(CraftingItems.SWORD.getImageName());
        Image oreImage = new Image(GameItems.ORE.getImageName());

        graphicsContext.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setTextAlign(TextAlignment.LEFT);

        graphicsContext.drawImage(swordImage, 40, 240, 54, 54);
        graphicsContext.fillText(swordDescription, 140, 240);
        for (int i = 0; i < 3; i++) {
            graphicsContext.drawImage(oreImage, 140 + i * 40, 260, 32, 32);
        }

        // POTION
        String potionDescription = "POTION - crafted using 1 HERB and 1 WATER_ITEM";
        Image potionImage = new Image(CraftingItems.POTION.getImageName());
        Image herbImage = new Image(GameItems.HERB.getImageName());
        Image waterImage = new Image(GameItems.WATER_ITEM.getImageName());

        graphicsContext.drawImage(potionImage, 40, 330, 54, 54);
        graphicsContext.fillText(potionDescription, 140, 340);
        graphicsContext.drawImage(herbImage, 140, 360, 32, 32);
        graphicsContext.drawImage(waterImage, 180, 360, 32, 32);

    }

}
