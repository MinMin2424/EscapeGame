package cz.cvut.fel.pjv.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RenderGameOver {

    public static void displayGameOver(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("GAME OVER!");

        Image image = new Image("gameOver.png");
        ImageView imageView = new ImageView(image);

        Label gameOverLabel = new Label("You don't have enough health to continue.");
        gameOverLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
        gameOverLabel.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(imageView ,gameOverLabel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
