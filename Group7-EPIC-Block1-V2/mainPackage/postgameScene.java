package mainPackage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class postgameScene extends Scene {
    Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    DropShadow dropShadow = new DropShadow();
    Label gameOverLabel = new Label("Game Over");
    Label scoreLabel = new Label("Your total score is:");
    Button viewStatsButton = new Button("View Statistics");
    Button returnToMainButton = new Button("Return to Main Menu");
    Button placeholder = new Button();
    Button placeholder1 = new Button();
        public postgameScene(Stage primaryStage, int score) {
            //main template
            super(new VBox(), 440, 350);

            //cosmetic aspects
            homeScene.labelCosmetics(gameOverLabel, 40, transparentBackground, dropShadow);

            Label scoreDisplayLabel = new Label("" + score);
            homeScene.labelCosmetics(scoreDisplayLabel, 30, transparentBackground, dropShadow);

            homeScene.labelCosmetics(scoreLabel, 30, transparentBackground, dropShadow);

            homeScene.buttonCosmetics(viewStatsButton, orangeBackground, dropShadow,15);
                viewStatsButton.setOnAction(e -> {
                    primaryStage.setScene(statGUI.createScene(primaryStage));
                    primaryStage.setFullScreen(true);
                });

            homeScene.buttonCosmetics(returnToMainButton, orangeBackground, dropShadow,15);
                returnToMainButton.setOnAction(e -> {
                    primaryStage.setScene(mainmenuScene.createScene(primaryStage));
                    primaryStage.setFullScreen(true);
                });

            // Add the button to the scene's layout
            VBox root = (VBox) this.getRoot();
            root.getChildren().addAll(layoutMaker(scoreDisplayLabel));
        }

        public static Scene createScene(Stage primarystage, int score) {
            return new postgameScene(primarystage, score);
        }

        public VBox layoutMaker(Label scoreDisplayLabel){
            VBox Lay = new VBox();
            Lay.setStyle("-fx-background-color: #FFD966;");
            Lay.setAlignment(Pos.CENTER);
            Lay.getChildren().addAll(gameOverLabel, scoreLabel, scoreDisplayLabel, returnToMainButton, viewStatsButton);
            Lay.setSpacing(20);
            Lay.setPrefSize(1080,1000);

            return Lay;
        }

}
