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

    public homeScene(Stage primaryStage) {
        super(new VBox(), 440, 250);

        Label Intro = new Label("Welcome to the quiz! Please Login or Sign up");
        labelCosmetics(Intro, 13, transparentBackground, dropShadow);

        Button loginButton = new Button("Login");
        buttonCosmetics(loginButton, orangeBackground, dropShadow, 15 );
            loginButton.setOnAction(e -> {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                primaryStage.setScene(loginScene.createScene(stage));
            });

        Button signUpButton = new Button("Sign Up");
        buttonCosmetics(signUpButton, orangeBackground, dropShadow, 15);
            signUpButton.setOnAction(e -> {
                Stage stage = (Stage) signUpButton.getScene().getWindow();
                primaryStage.setScene(signupScene.createScene(stage));
            });

        Button placeholder = new Button();

        //layout settings------------------------------------
        GridPane homeLay = new GridPane();
        homeLay.setStyle("-fx-background-color: #FFD966;");
        homeLay.setPadding(offset);
        homeLay.setVgap(10);
        homeLay.setHgap(5);
        homeLay.setAlignment(Pos.CENTER);

        //Children addition and positioning
        homeLay.setConstraints(Intro, 1, 0);
        homeLay.setConstraints(loginButton, 0, 10);
        homeLay.setConstraints(signUpButton, 2, 10);
        homeLay.setConstraints(placeholder, 1, 100);
        homeLay.getChildren().addAll(Intro, loginButton, signUpButton, placeholder);
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(homeLay);
    }

    public static homeScene createScene(Stage primaryStage) {
        return new homeScene(primaryStage);
    }

    public static void labelCosmetics(Label label, int fontSize, Background background, DropShadow dropShadow){
        label.setFont(Font.font("ADLam Display", FontWeight.NORMAL, fontSize));
        label.setTextFill(Color.WHITE);
        label.setBackground(background);
        label.setEffect(dropShadow);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
    }

    public static void buttonCosmetics(Button button, Background orangeBackground, DropShadow dropShadow, int fontSize){
        button.setFont(Font.font("ADLam Display", FontWeight.NORMAL, fontSize));
        button.setTextFill(Color.WHITE);
        button.setBackground(orangeBackground);
        button.setEffect(dropShadow);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setAlignment(Pos.CENTER);
    }
}