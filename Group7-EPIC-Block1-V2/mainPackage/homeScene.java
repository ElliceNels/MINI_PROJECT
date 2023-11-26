package mainPackage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class homeScene extends Scene{
	Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
	 Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
	 DropShadow dropShadow = new DropShadow();
	
	Insets offset = new Insets(10,10,10,10);
    Label Intro = new Label("Welcome to the quiz! Please Login or Sign up");
    static Button loginButton = new Button("Login");
    Button signUpButton = new Button("Sign Up");

    public homeScene(Stage primaryStage) {
        super(new VBox(), 440, 250);
        Intro.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        Intro.setTextFill(Color.WHITE);
        Intro.setBackground(transparentBackground);
        Intro.setEffect(dropShadow);
        Intro.setTextAlignment(TextAlignment.CENTER);
        Intro.setAlignment(Pos.CENTER);
        
        loginButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setBackground(orangeBackground);
        loginButton.setEffect(dropShadow);
        loginButton.setTextAlignment(TextAlignment.CENTER);
        loginButton.setAlignment(Pos.CENTER);
        
        signUpButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setBackground(orangeBackground);
        signUpButton.setEffect(dropShadow);
        signUpButton.setTextAlignment(TextAlignment.CENTER);
        signUpButton.setAlignment(Pos.CENTER);
        
        //home layout
        GridPane homeLay = new GridPane();
        signUpButton.setOnAction(e -> {
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            primaryStage.setScene(signupScene.createScene(stage));

        });

        loginButton.setOnAction(e -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            primaryStage.setScene(loginScene.createScene(stage));
        });

        //General layout settings
        homeLay.setStyle("-fx-background-color: #FFD966;");
        homeLay.setPadding(offset);
        homeLay.setVgap(10);
        homeLay.setHgap(5);
        homeLay.setAlignment(Pos.CENTER);
        //Children addition and positioning
        homeLay.setConstraints(Intro, 1, 0);
        homeLay.setConstraints(loginButton, 0, 2);
        homeLay.setConstraints(signUpButton, 2, 2);
        homeLay.getChildren().addAll(Intro, loginButton, signUpButton);

        // Add the button to the scene's layout
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(homeLay);

    }
    public static homeScene createScene(Stage primaryStage) {
        return new homeScene(primaryStage);
    }


}
