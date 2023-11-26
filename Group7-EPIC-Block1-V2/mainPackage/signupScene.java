package mainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;


public class signupScene extends Scene{
	Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
	 Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
	 DropShadow dropShadow = new DropShadow();
	 
	Insets offset = new Insets(10,10,10,10);

    Button ReturnloginButton = new Button("Back to Login Page");
    Button createAccountButton = new Button("Create Account");
    TextField newUsernameField = new TextField();
    PasswordField newPasswordField = new PasswordField();
    PasswordField confirmPasswordField = new PasswordField();
    Popup existingUserPopup = new Popup() ;
    Label existingUserPLabel = new Label("Something has gone wrong, most likely the username already exists. Try again. ");

    public signupScene(Stage primaryStage) {
        super(new VBox(), 450, 250);

        ReturnloginButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        ReturnloginButton.setTextFill(Color.WHITE);
        ReturnloginButton.setBackground(orangeBackground);
        ReturnloginButton.setEffect(dropShadow);
        ReturnloginButton.setTextAlignment(TextAlignment.CENTER);
        
        createAccountButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        createAccountButton.setTextFill(Color.WHITE);
        createAccountButton.setBackground(orangeBackground);
        createAccountButton.setEffect(dropShadow);
        createAccountButton.setTextAlignment(TextAlignment.CENTER);
        createAccountButton.setAlignment(Pos.CENTER);

        newUsernameField.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        newUsernameField.setEffect(dropShadow);
        newUsernameField.setAlignment(Pos.CENTER);
        
        newPasswordField.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        newPasswordField.setEffect(dropShadow);
        newPasswordField.setAlignment(Pos.CENTER);
        
        confirmPasswordField.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        confirmPasswordField.setEffect(dropShadow);
        confirmPasswordField.setAlignment(Pos.CENTER);
        
        GridPane SignUpLay = new GridPane();
        //General layout settings
        SignUpLay.setStyle("-fx-background-color: #FFD966;");
        SignUpLay.setPadding(offset);
        SignUpLay.setVgap(10);
        SignUpLay.setHgap(5);
        SignUpLay.setAlignment(Pos.CENTER);
        //Children addition and positioning
        SignUpLay.setConstraints(ReturnloginButton, 3, 5);
        ReturnloginButton.setOnAction(e ->
        {
            Stage stage = (Stage) ReturnloginButton.getScene().getWindow();
            stage.setScene(loginScene.createScene(stage));
        });

        existingUserPLabel.setStyle("-fx-background-color:#D5D5D5; -fx-font-size:10;");
        existingUserPopup.getContent().add(existingUserPLabel);

        SignUpLay.setConstraints(createAccountButton, 3, 4);
        createAccountButton.setOnAction(e -> {
            String user = newUsernameField.getText(); 		//Changes user input into String to check in db
            String pass = newPasswordField.getText();
            String confirmPass = confirmPasswordField.getText();

            if (Objects.equals(confirmPass, pass)) { //tests whether the passwords  are equal
                // password check

                if (passwordCheck(pass) && DB_UserInteract.insert(user, pass)){ // new user name and password stored if it doesn't exist, and password is valid
                    System.out.println("New user created successfully");
                    Stage stage = (Stage) createAccountButton.getScene().getWindow();
                    stage.setScene(homeScene.createScene(stage));
                }
                else {
                    System.out.println("Something has gone wrong, most likely the username already exists. Try again. "); //user exists and was not stored again
                    failPopUp(existingUserPopup,primaryStage);
                }
            }else {
                System.out.println("Passwords do not match.. ");
                colorChange(confirmPasswordField, newPasswordField);

            }


            //window.setScene(Fail);
        });
        SignUpLay.setConstraints(newUsernameField, 3, 1);
        newUsernameField.setPromptText("New username");

        SignUpLay.setConstraints(newPasswordField, 3, 2);
        newPasswordField.setPromptText("New password");

        SignUpLay.setConstraints(confirmPasswordField, 3, 3);
        confirmPasswordField.setPromptText("Confirm password");



        SignUpLay.getChildren().addAll(ReturnloginButton, createAccountButton, newUsernameField, newPasswordField, confirmPasswordField);
        // Add the button to the scene's layout
        VBox root = (VBox) this.getRoot();
        root.getChildren().add(SignUpLay);


    }
    public static signupScene createScene(Stage primaryStage) {
        return new signupScene(primaryStage);
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
    public static void failPopUp(Popup existingUserPopup, Stage window) {
        Timeline popupTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> {
                    existingUserPopup.show(window);
                }),
                new KeyFrame(Duration.seconds(3), e -> {
                    existingUserPopup.hide();
                }));
        popupTimeline.setCycleCount(1); //How many times it plays the sequence
        popupTimeline.playFromStart();
    }

    public static boolean passwordCheck(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean length = password.length() >= 8;
        return hasUppercase && hasLowercase && hasNumber && length;
    }
}
