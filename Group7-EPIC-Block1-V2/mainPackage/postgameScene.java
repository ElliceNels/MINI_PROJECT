package mainPackage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class postgameScene extends Scene {

        public postgameScene(Stage primaryStage, int score) {
            //main template
            super(new VBox(), 440, 350);

            //cosmetic aspects
            Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
            Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
            DropShadow dropShadow = new DropShadow();
       	 
            //postGame controls
            Label gameOverLabel = new Label("Game Over");
            applyCosmeticsLabel(gameOverLabel, transparentBackground, dropShadow);

            Label scoreLabel = new Label("Your total score is:");
            applyCosmeticsLabel(gameOverLabel, transparentBackground, dropShadow);

            Label scoreDisplayLabel = new Label("" + score);
            applyCosmeticsLabel(gameOverLabel, transparentBackground, dropShadow);

            Button viewStatsButton = new Button("View mainPackage.Statistics");
            applyCosmeticsButton(viewStatsButton, orangeBackground, dropShadow);
                viewStatsButton.setOnAction(e -> {
                    primaryStage.setScene(statGUI.createScene(primaryStage));
                    primaryStage.setFullScreen(true);
                });

            Button returnToMainButton = new Button("Return to mainPackage.DB_Handler.Main Menu");
            applyCosmeticsButton(returnToMainButton, orangeBackground, dropShadow);
                returnToMainButton.setOnAction(e -> {
                    primaryStage.setScene(mainmenuScene.createScene(primaryStage));
                    primaryStage.setFullScreen(true);
                });

            //scene layout
            VBox PostGameLay = new VBox();
            PostGameLay.setStyle("-fx-background-color: #FFD966;");
            PostGameLay.setAlignment(Pos.CENTER);
            PostGameLay.getChildren().addAll(gameOverLabel, scoreLabel, scoreDisplayLabel, returnToMainButton, viewStatsButton );
            PostGameLay.setSpacing(10);

            // Add the button to the scene's layout
            VBox root = (VBox) this.getRoot();
            root.getChildren().addAll(PostGameLay);
        }

        public static Scene createScene(Stage primarystage, int score) {
            return new postgameScene(primarystage, score);
        }

        public static void applyCosmeticsLabel(Label label, Background transparentBackground, DropShadow dropShadow){
            label.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 24));
            label.setTextFill(Color.WHITE);
            label.setBackground(transparentBackground);
            label.setEffect(dropShadow);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.CENTER);
        }

        public static void applyCosmeticsButton(Button button, Background orangeBackground, DropShadow dropShadow){
            button.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 15));
            button.setTextFill(Color.WHITE);
            button.setBackground(orangeBackground);
            button.setEffect(dropShadow);
            button.setTextAlignment(TextAlignment.CENTER);
            button.setAlignment(Pos.CENTER);
        }


}
