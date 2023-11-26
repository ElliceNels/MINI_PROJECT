package mainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import javafx.util.Duration;


public class loginScene extends Scene{
	Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    DropShadow dropShadow = new DropShadow();
	Insets offset = new Insets(10,10,10,10);


    public loginScene(Stage primaryStage) {
        super(new VBox(), 450, 250);

        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        confirmButton.setTextFill(Color.WHITE);
        confirmButton.setBackground(orangeBackground);
        confirmButton.setEffect(dropShadow);
        confirmButton.setTextAlignment(TextAlignment.CENTER);

        Button ReturnsignUpButton = new Button("Back to Sign Up");
        ReturnsignUpButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        ReturnsignUpButton.setTextFill(Color.WHITE);
        ReturnsignUpButton.setBackground(orangeBackground);
        ReturnsignUpButton.setEffect(dropShadow);
        ReturnsignUpButton.setTextAlignment(TextAlignment.CENTER);
        ReturnsignUpButton.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField();
        usernameField.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        usernameField.setEffect(dropShadow);
        usernameField.setAlignment(Pos.CENTER);

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        passwordField.setEffect(dropShadow);
        passwordField.setAlignment(Pos.CENTER);
        
        //Login layout
        GridPane LoginLay = new GridPane();
        //General layout settings
        LoginLay.setStyle("-fx-background-color: #FFD966;");
        LoginLay.setPadding(offset);
        LoginLay.setVgap(10);
        LoginLay.setHgap(5);
        LoginLay.setAlignment(Pos.CENTER);
        //Children addition and positioning
        LoginLay.setConstraints(ReturnsignUpButton, 2 , 4);
        ReturnsignUpButton.setOnAction(e -> {
            Stage stage = (Stage) ReturnsignUpButton.getScene().getWindow();
            stage.setScene(signupScene.createScene(stage));
        });

        LoginLay.setConstraints(usernameField, 2, 1);
        usernameField.setPromptText("Username");

        LoginLay.setConstraints(passwordField, 2, 2);
        passwordField.setPromptText("Password");


        LoginLay.setConstraints(confirmButton, 2, 3);
        confirmButton.setOnAction(e -> {
            String user = usernameField.getText(); 		//Changes user input into String to check in db
            String pass = passwordField.getText();

            if (DB_UserInteract.loginCheck(user, pass) == false) { 	//Checks whether user name and password exists
                colorChange(usernameField, passwordField);			//If it doesnt exist then go red
                System.out.print("Username or password does not match. Try again");
            }else {
                System.out.print("Successful login");
                launcher.user_ID = user;
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.setScene(mainmenuScene.createScene(stage));
                stage.setFullScreen(true);
            }
        });

        LoginLay.getChildren().addAll(ReturnsignUpButton, usernameField, passwordField, confirmButton);
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(LoginLay);


    }
    public static loginScene createScene(Stage primaryStage) {
        return new loginScene(primaryStage);
    }

    public static void colorChange(TextField usernameField, PasswordField passwordField) {
        Timeline colorChangeTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> { // Change the text field's background color for 2 seconds
                    usernameField.setStyle("-fx-background-color: red;");
                    passwordField.setStyle("-fx-background-color: red;");

                }),
                new KeyFrame(Duration.seconds(1), e -> {// Revert the color after 3 seconds
                    usernameField.setStyle("");
                    passwordField.setStyle("");
                }));
        colorChangeTimeline.setCycleCount(1); //How many times it plays the sequence
        colorChangeTimeline.playFromStart(); //play

    }

}
