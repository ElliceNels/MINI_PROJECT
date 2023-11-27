package mainPackage;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class mainmenuScene extends Scene {

    Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    Insets offset = new Insets(10,10,10,10);
    DropShadow dropShadow = new DropShadow();
    Button playButton = new Button("-Play-");
    Button statsButton = new Button("Play statistics");
    Button questionIOButton = new Button("Input your own questions");
    Button logoutButton = new Button("Log out");
    Label currentUserLabel = new Label("Currently Logged in: " + launcher.user_ID );
    Label trademarkLabel = new Label("Made by Jack Casey and Ellice Nelson\r\n"
            + "“CUiz” is a registered trademark ™\r\n");
    Image CUizLogo = new Image(getClass().getResourceAsStream("CUiz Logo.png"));
    ImageView CUizView = new ImageView();
    Image CUizPenguin = new Image(getClass().getResourceAsStream("CUizPenguin.png"));
    ImageView CUizPenguinView = new ImageView();


    public mainmenuScene(Stage primaryStage) {
        super((new VBox()));

        //Control cosmetics and action
        buttonCosmetics(playButton, 40);
            playButton.setOnAction(e -> {
                Stage stage = (Stage) playButton.getScene().getWindow();
                primaryStage.setScene(playmodesScene.createScene(stage));
                primaryStage.setFullScreen(true);
            });

        buttonCosmetics(statsButton, 20);
            statsButton.setOnAction(e -> {
        	    Stage stage = (Stage) statsButton.getScene().getWindow();
                primaryStage.setScene(statGUI.createScene(stage));
                primaryStage.setFullScreen(true);
            });

        buttonCosmetics(questionIOButton, 20);
            questionIOButton.setOnAction(e -> {
                Stage stage = (Stage) questionIOButton.getScene().getWindow();
                primaryStage.setScene(questionioScene.createScene(stage));
                primaryStage.setFullScreen(true);
            });

        buttonCosmetics(logoutButton, 20);
            logoutButton.setOnAction(e -> {
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                primaryStage.setScene(loginScene.createScene(stage));
            });

        currentUserLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        currentUserLabel.setTextFill(Color.WHITE);

        trademarkLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        trademarkLabel.setTextFill(Color.WHITE);

        CUizPenguinView.setFitWidth(75);
        CUizPenguinView.setFitHeight(75);
        CUizPenguinView.setImage(CUizPenguin);

        CUizView.setFitWidth(440);
        CUizView.setFitHeight(250);
        CUizView.setImage(CUizLogo);

        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(layoutMaker());
    }

    public void buttonCosmetics(Button button, int fontSize){
        button.setFont(Font.font("ADLam Display", FontWeight.NORMAL, fontSize));
        button.setTextFill(Color.WHITE);
        button.setBackground(transparentBackground);
        button.setEffect(dropShadow);
        button.setTextAlignment(TextAlignment.CENTER);
    }

    public Node layoutMaker(){
        GridPane CenterMainQuizLay = new GridPane();
        CenterMainQuizLay.setPadding(offset);
        CenterMainQuizLay.setVgap(10);
        CenterMainQuizLay.setHgap(5);
        CenterMainQuizLay.setAlignment(Pos.CENTER);
        CenterMainQuizLay.setConstraints(playButton, 1, 0);
        CenterMainQuizLay.setHalignment(playButton, HPos.CENTER);
        CenterMainQuizLay.setConstraints(statsButton, 1, 2);
        CenterMainQuizLay.setHalignment(statsButton, HPos.CENTER);
        CenterMainQuizLay.setConstraints(questionIOButton, 1, 3);
        CenterMainQuizLay.setHalignment(questionIOButton, HPos.CENTER);
        CenterMainQuizLay.setConstraints(logoutButton, 1, 4);
        CenterMainQuizLay.setHalignment(logoutButton, HPos.CENTER);

        CenterMainQuizLay.getChildren().addAll(playButton, statsButton, questionIOButton, logoutButton);


        GridPane BottomMainQuizLay = new GridPane();
        BottomMainQuizLay.setVgap(10);
        BottomMainQuizLay.setHgap(5);
        BottomMainQuizLay.setAlignment(Pos.CENTER);
        BottomMainQuizLay.setConstraints(CUizPenguinView, 0, 0);
        BottomMainQuizLay.setConstraints(currentUserLabel, 1, 0);
        BottomMainQuizLay.setConstraints(trademarkLabel, 220, 0);
        BottomMainQuizLay.getChildren().addAll(CUizPenguinView, currentUserLabel,trademarkLabel);


        BorderPane MainQuizLay = new BorderPane();
        MainQuizLay.setStyle("-fx-background-color: #FFD966;");
        MainQuizLay.setPadding(offset);
        MainQuizLay.setTop(CUizView);
        MainQuizLay.setAlignment(CUizView, Pos.CENTER);
        MainQuizLay.setBottom(BottomMainQuizLay);
        MainQuizLay.setCenter(CenterMainQuizLay);

        return MainQuizLay;
    }

    public static mainmenuScene createScene(Stage primaryStage) {
        return new mainmenuScene(primaryStage);
    }

}
