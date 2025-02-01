/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.renders;

import cz.cvut.fel.pjv.model.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.model.MessageInfo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Renders messages to the user interface.
 * This class provides methods to display various types of messages to the user.
 */
public class RenderMessage {

    /**
     * Displays a message to the user interface.
     * @param messageInfo The MessageInfo object containing message details.
     */
    public static void displayMessage(MessageInfo messageInfo) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(messageInfo.getTitle());

        Image image = new Image(messageInfo.getImageName());
        ImageView imageView = new ImageView(image);

        Label messageLabel = new Label(messageInfo.getMessage());
        messageLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        messageLabel.setAlignment(Pos.CENTER);

        Label messageLabel2 = new Label(messageInfo.getMessage2());
        messageLabel2.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        messageLabel2.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(imageView ,messageLabel, messageLabel2);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 200);
        scene.setFill(Color.GHOSTWHITE);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Displays a message for successfully crafting an item.
     * @param craftingItems The CraftingItems object representing the crafted item
     */
    public static void displayMessage_CraftingItem_True(CraftingItems craftingItems) {
        String title = "ITEM CREATED!";
        String message = "The item '" + craftingItems.name() + "' was successfully created!";
        String message2 = "The item has been added to the inventory.";
        String imageName = craftingItems.getImageName();

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message for failing to craft an item due to insufficient resources.
     * @param craftingItems The CraftingItems object representing the item attempted to craft.
     */
    public static void displayMessage_CraftingItem_False(CraftingItems craftingItems) {
        String title = "ERROR CREATING ITEM!";
        String message = "Failed to create the item '" + craftingItems.name() + "'!";
        String message2 = "Insufficient resources for crafting the item.";
        String imageName = craftingItems.getImageName();

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message for attempting to craft an unknown type of item.
     */
    public static void displayMessage_CraftingItem_None() {
        String title = "ERROR CREATING ITEM!";
        String message = "Unknown type of item.!";
        String message2 = "Please select an item from the menu.";
        String imageName = "unknown.png";

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message indicating successful usage of the POTION item.
     */
    public static void usingPotion_True() {
        String title = "USING POTION!";
        String message = "You just used POTION to gain 1 life.";
        String message2 = "POTION is removed from inventory.";
        String imageName = CraftingItems.POTION.getImageName();

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message indicating unsuccessful usage of the POTION item.
     */
    public static void usingPotion_False() {
        String title = "USING POTION!";
        String message = "Failed to use POTION.";
        String message2 = "You have no POTION or you have full lives.";
        String imageName = CraftingItems.POTION.getImageName();

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message indicating that the player has used the sword to save themselves.
     */
    public static void usingSwordToSaveYourself() {
        String title = "USING SWORD!";
        String message = "You just used SWORD to save yourself.";
        String message2 = "SWORD is removed from inventory.";
        String imageName = CraftingItems.SWORD.getImageName();

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message informing the player about using water to save themselves.
     */
    public static void usingWaterToSaveYourself() {
        String title = "USING WATER!";
        String message = "You just used WATER to save yourself.";
        String message2 = "WATER_ITEM is removed from inventory.";
        String imageName = GameItems.WATER_ITEM.getImageName();

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a message indicating the transition to the next level.
     */
    public static void transitionToTheNextLevel() {
        String title = "TRANSITION TO THE NEXT LEVEL!";
        String message = "You won the first level. Congratulations!";
        String message2 = "Transition to the second level...";
        String imageName = "level_up.png";

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a victory message.
     */
    public static void displayVictory(){
        String title = "VICTORY!";
        String message = "Congratulations!";
        String message2 = " You have successfully completed the level <3";
        String imageName = "victory.png";

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

    /**
     * Displays a game over message.
     */
    public static void displayGameOver(){
        String title = "GAME OVER!";
        String message = "You don't have enough health to continue.";
        String message2 = "Thanks for playing <3";
        String imageName = "gameOver.png";

        displayMessage(new MessageInfo(title, message, message2, imageName));
    }

}
