package mainPackage;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class playmodesScene extends Scene {
    //Controls defined
    Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    DropShadow dropShadow = new DropShadow();
    Image CUizLogo = new Image(getClass().getResourceAsStream("CUiz Logo.png"));
    Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    Insets offset = new Insets(10,10,10,10);
    Image CUizPenguin = new Image(getClass().getResourceAsStream("CUizPenguin.png"));
    ImageView CUizPenguinView1 = new ImageView();
    Button incDifficultyButton = new Button("Increasing Difficulty");
    Button incScoreButton = new Button("Increasing Score");
    Button randomPlayButton = new Button("Random Play");
    ImageView CUizView1 = new ImageView();
    Label currentUserLabel1 = new Label("Currently Logged in: " + launcher.user_ID );
    Label chooseModeLabel= new Label("Choose a Game Mode");
    public playmodesScene(Stage primaryStage) {
        super(new VBox(), 520, 360);

        //Cosmetics and Actions
        CUizView1.setFitWidth(100);
        CUizView1.setFitHeight(50);
        CUizView1.setImage(CUizLogo);

        CUizPenguinView1.setFitWidth(75);
        CUizPenguinView1.setFitHeight(75);
        CUizPenguinView1.setImage(CUizPenguin);

        homeScene.buttonCosmetics(incDifficultyButton, transparentBackground, dropShadow, 20);
        buttonAction(incDifficultyButton, primaryStage, 0);

        homeScene.buttonCosmetics(incScoreButton, transparentBackground, dropShadow, 20);
        buttonAction(incScoreButton, primaryStage, 1);

        homeScene.buttonCosmetics(randomPlayButton, transparentBackground, dropShadow, 20  );
        buttonAction(randomPlayButton, primaryStage, 2 );

        homeScene.labelCosmetics(chooseModeLabel, 50, orangeBackground, dropShadow);

        currentUserLabel1.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        currentUserLabel1.setTextFill(Color.WHITE);

        // Add layout to the scene
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(layoutmaker());
    }
    public void buttonAction(Button button, Stage primaryStage, int gamemode){
        button.setOnAction(e -> {
            Stage stage = (Stage) incDifficultyButton.getScene().getWindow();
            gameplayScene.gamemodeInt = gamemode;
            primaryStage.setScene(gameplayScene.createScene(stage));
            primaryStage.setFullScreen(true);
        });
    }
    public GridPane bottomLayout(){
        GridPane BottomPlayModesLay = new GridPane();
        BottomPlayModesLay.setVgap(10);
        BottomPlayModesLay.setHgap(5);
        BottomPlayModesLay.setAlignment(Pos.CENTER);
        BottomPlayModesLay.setConstraints(CUizPenguinView1, 0, 25);
        BottomPlayModesLay.setConstraints(currentUserLabel1, 1, 25);
        BottomPlayModesLay.getChildren().addAll(CUizPenguinView1, currentUserLabel1);

        return BottomPlayModesLay;
    }
    public GridPane centerLayout() {
        GridPane CenterPlayModesLay = new GridPane();
        CenterPlayModesLay.setPadding(offset);
        CenterPlayModesLay.setVgap(10);
        CenterPlayModesLay.setHgap(5);
        CenterPlayModesLay.setAlignment(Pos.CENTER);
        CenterPlayModesLay.setConstraints(incDifficultyButton, 0, 20);
        CenterPlayModesLay.setHalignment(incDifficultyButton, HPos.CENTER);
        CenterPlayModesLay.setConstraints(incScoreButton, 10, 20);
        CenterPlayModesLay.setHalignment(incScoreButton, HPos.CENTER);
        CenterPlayModesLay.setConstraints(randomPlayButton, 5, 20);
        CenterPlayModesLay.setHalignment(randomPlayButton, HPos.CENTER);
        CenterPlayModesLay.getChildren().addAll(incDifficultyButton, incScoreButton, randomPlayButton);

        return CenterPlayModesLay;
    }
    public GridPane topLayout() {
        GridPane TopPlayModesLay = new GridPane();
        TopPlayModesLay.setVgap(10);
        TopPlayModesLay.setHgap(5);
        TopPlayModesLay.setAlignment(Pos.CENTER);
        TopPlayModesLay.setConstraints(CUizView1, 0, 1);
        TopPlayModesLay.setHalignment(CUizView1, HPos.CENTER);
        TopPlayModesLay.setConstraints(chooseModeLabel, 0, 8);
        TopPlayModesLay.getChildren().addAll(CUizView1, chooseModeLabel);

        return TopPlayModesLay;
    }
    public BorderPane layoutmaker(){

        BorderPane PlayModesLay = new BorderPane();
        PlayModesLay.setStyle("-fx-background-color: #FFD966;");
        PlayModesLay.setPadding(offset);
        PlayModesLay.setTop(topLayout());
        PlayModesLay.setBottom(bottomLayout());
        PlayModesLay.setCenter(centerLayout());
        PlayModesLay.setPrefSize(1080, 1000);
        return PlayModesLay;
    }

    public static playmodesScene createScene(Stage primaryStage) {
        return new playmodesScene(primaryStage);
    }

}
